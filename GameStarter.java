import java.net.*;
import java.io.*;
import java.util.*;

public class GameStarter{

  private Player client;
  private int playerId, timerCount;
  private int myPoints = 0;
  private int enemyPoints = 0;
  private ReadFromServer rfsRunnable;
  private WriteToServer wtsRunnable;


  public GameStarter(){
    System.out.println("Please type in server");
    Scanner sc = new Scanner(System.in);
    String server = sc.next();

    try{
      Socket s = new Socket(server, 47000);
      DataInputStream in = new DataInputStream(s.getInputStream());
      DataOutputStream out = new DataOutputStream(s.getOutputStream());
      playerId = in.readInt();
      client = new Player(playerId);
      if(playerId==1){
        System.out.println("Waiting for Player #2");
      }
      rfsRunnable = new ReadFromServer(in);
      wtsRunnable = new WriteToServer(out);      
      rfsRunnable.waitForStartMsg();
    }catch(IOException ex){
      System.out.println("Server Error");
    }
  } 

  private class ReadFromServer implements Runnable{
    private DataInputStream dataIn;

    public ReadFromServer(DataInputStream in){
      dataIn = in;
      System.out.println("RFS Runnable created");
    }

    public void run(){
      try{
        while(true){
          enemyPoints = dataIn.readInt();
          client.getGF().setEnemyPoints(enemyPoints);
          timerCount = dataIn.readInt();
          client.getGF().setTimer(timerCount);
          if(timerCount == 0){
              client.stopGame();
              for(Task t : client.getTaskArrayList()){
                  t.stop();
              }
              if(myPoints>enemyPoints){
                client.getGF().getGC().returnTask(6);
              }else if(myPoints<enemyPoints){
                client.getGF().getGC().returnTask(7);
              }else{
                client.getGF().getGC().returnTask(8);
              }
          }
        }
      }catch(IOException ex){
        System.out.println("IOException from RFS run()");
      }
    }

    public void waitForStartMsg(){
      try{
        String startMsg = dataIn.readUTF();
        Thread readThread = new Thread(rfsRunnable);
        Thread writeThread = new Thread(wtsRunnable);
        readThread.start();
        writeThread.start();
        client.playGame();
      }catch(IOException ex){
        System.out.println("IOException from waitForStartMsg()");
      }
    }
  }


  private class WriteToServer implements Runnable{
    private DataOutputStream dataOut;

    public WriteToServer(DataOutputStream out){
      dataOut = out;
      System.out.println("WTS Runnable created");
    }

    public void run(){
      try{
        while(true){
          myPoints = client.getPoints();
          dataOut.writeInt(myPoints);
          dataOut.flush();
          try{
            Thread.sleep(25);
          }catch(InterruptedException ex){
            System.out.println("InterruptedExcpetion from WTS run()");
          }
        }
      }catch(IOException ex){
        System.out.println("IOException from WTS run()");
      }
    }
  }


  public static void main(String[] args) {
    GameStarter gs = new GameStarter();
  }
}
