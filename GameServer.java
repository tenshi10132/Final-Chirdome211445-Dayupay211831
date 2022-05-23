import java.net.*;
import java.io.*;


public class GameServer {

  private ServerSocket ss;
  private int numPlayers, maxPlayers, p1Points, p2Points;
  private Socket p1Socket, p2Socket;
  private Countdown cd;
  private ReadFromClient p1ReadRunnable, p2ReadRunnable;
  private WriteToClient p1WriteRunnable, p2WriteRunnable;


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
            Thread readThread1 = new Thread(p1ReadRunnable);
            Thread readThread2 = new Thread(p2ReadRunnable);
            readThread1.start();
            readThread2.start();
            Thread writeThread1 = new Thread(p1WriteRunnable);
            Thread writeThread2 = new Thread(p2WriteRunnable);
            writeThread1.start();
            writeThread2.start();
            cd = new Countdown();
            cd.start();
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
            p1Points = dataIn.readInt();
          }else{
            p2Points = dataIn.readInt();
          }
        }
      }catch(IOException ex){
        System.out.println("IOException from RFC run()");
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
            dataOut.writeInt(p2Points);
          }else{
            dataOut.writeInt(p1Points);
          }
          dataOut.writeInt(cd.getTime());
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
        dataOut.writeUTF("We now have 2 players. Go!");
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