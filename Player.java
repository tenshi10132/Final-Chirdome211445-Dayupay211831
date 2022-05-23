import java.util.*;

public class Player{

  private GameFrame gf;
  private ArrayList<Task> t;
  private int playerNum, points;
  private boolean playing;

  public Player(int i){
    playerNum = i;
    gf = new GameFrame(playerNum);
    gf.setUpGUI();
    gf.setUpKeyListener();
    gf.setUpMouseListener();
    gf.setUpButtonListener();

    t = new ArrayList<Task>();
    t.add(new TypingTest(gf));
    t.add(new ButtonSmash(gf));
    t.add(new DragAndDrop(gf));
    t.add(new Stirring(gf));
    t.add(new Maze(gf));
    t.add(new Runner(gf));

    playing = false;
  }

  public void stopGame(){
    playing = false;
  }

  public void playGame(){
      Thread play = new PlayGame();
      play.start();
  }

  private class PlayGame extends Thread{
    
    public PlayGame(){
        playing = true;
    }

    @Override
    public void run(){
      Collections.shuffle(t);
      int num = 0;
      while (playing){
        System.out.println("Game is playing");
        if (num <= 5){
          t.get(num).begin();
          if(t.get(num).getStatus()==1){
            points++;
            gf.setMyPoints(points);
            System.out.println("One point added: " + points);
          }
          t.get(num).stop();
          num++;
          continue;
        }else{
          num = 0;
          continue;
        }
      }
    }
  }

  public GameFrame getGF(){
    return gf;
  }
  public ArrayList<Task> getTaskArrayList(){
      return t;
  }
  public int getPoints(){
    return points;
  }
}

