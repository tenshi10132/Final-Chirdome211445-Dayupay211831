import java.net.*;
import java.io.*;


public class GameServer {

  private ServerSocket ss;
  private int numPlayers, maxPlayers, p1Score, p2Score, timerCount;
  private String ready1 = " ";
  private String ready2 = " ";
  private Socket p1Socket;
  private Socket p2Socket;
  private ReadFromClient p1ReadRunnable;
  private ReadFromClient p2ReadRunnable;
  private WriteToClient p1WriteRunnable;
  private WriteToClient p2WriteRunnable;


  public GameServer(){
    numPlayers = 0;
    maxPlayers = 2;
    System.out.println("Server Online");

    try{
      ss = new ServerSocket(47000);

    }catch(IOException ex){
      System.out.println("Server Error");
    }
  }

  public void acceptConnections(){
    try{
      System.out.println("On standby for connections");

      while(numPlayers<maxPlayers){
        Socket s = ss.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        numPlayers++;     
        
        out.writeInt(numPlayers);
        System.out.println("Player #"+numPlayers+" has connected.");

        ReadFromClient rfc = new ReadFromClient(numPlayers, in);
          WriteToClient wtc = new WriteToClient(numPlayers, out);

          if(numPlayers == 1){
            p1Socket = s;
            p1ReadRunnable = rfc;
            p1WriteRunnable = wtc;
          }else{
            p2Socket = s;
            p2ReadRunnable = rfc;
            p2WriteRunnable = wtc;
            p1WriteRunnable.sendStartMsg();
            p2WriteRunnable.sendStartMsg();
            // Thread readThread1 = new Thread(p1ReadRunnable);
            // Thread readThread2 = new Thread(p2ReadRunnable);
            // readThread1.start();
            // readThread2.start();
            // Thread writeThread1 = new Thread(p1WriteRunnable);
            // Thread writeThread2 = new Thread(p2WriteRunnable);
            // writeThread1.start();
            // writeThread2.start();
          }
      }

      System.out.println("Maximum amount of players reached.");
      
    }catch(IOException ex){
      System.out.println("Connection Error");
    }
  }
  

  private class ReadFromClient implements Runnable{
        
    private int playerId;
    private DataInputStream dataIn;

    public ReadFromClient(int pid, DataInputStream in){
      playerId = pid;
      dataIn = in;
      System.out.println("RFC" + playerId + " Runnable created");
    }
    
    public void run(){
      try{
        while(true){
          if(playerId == 1){
            p1Score = dataIn.readInt();
          }else{
            p2Score = dataIn.readInt();
          }
        }
      }catch(IOException ex){
        System.out.println("IOException from RFC run()");
      }
    }

    public void getReady(){
      try{
        if(playerId == 1){
          ready1 = dataIn.readUTF();
        }else{
          ready2 = dataIn.readUTF();
        }
        
      }catch(IOException ex){
        System.out.println("IOException from getReady()");
      }
    }
  }
    
  private class WriteToClient implements Runnable{
      
    private int playerId;
    private DataOutputStream dataOut;

    public WriteToClient(int pid, DataOutputStream out){
      playerId = pid;
      dataOut = out;
      System.out.println("WTC" + playerId + " Runnable created");
    }
    
    public void run(){
      try{
        while(true){
          if(playerId == 1){
            dataOut.writeInt(p2Score);
          }else{
            dataOut.writeInt(p1Score);
          }
          dataOut.writeInt(timerCount);
          dataOut.flush();

          try{
              Thread.sleep(15);
          }catch(InterruptedException ex){
              System.out.println("InterruptedExcpetion from WTS run()");
          }
        }
      }catch(IOException ex){
        System.out.println("IOException from WTC run()");
      }
    }

    public void sendStartMsg(){
      try{
        if(ready1.equals(ready2)){
          dataOut.writeUTF("We now have 2 players. Go!");
        }
      }catch(IOException ex){
        System.out.println("IOException from sendStartMsg()");
      }
    }
  }




  public static void main(String[] args) {
    GameServer gv = new GameServer();
    gv.acceptConnections();
  }
}