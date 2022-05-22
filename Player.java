
import java.util.*;

public class Player{

  private GameFrame gf;
  private ArrayList<Task> t;
  private int playerNum;
  private boolean playing = false;

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

  }

  public void startGame(){
    playing = true;
  }

  public void stopGame(){
    playing = false;
  }

  public void playGame(){
    Collections.shuffle(t);
    int num = 0;
    while (playing){
      if (num <= 5){
        t.get(num).begin();
        t.get(num).stop();
        num++;
        continue;
      
      }
      if (num>5){
        num = 0;
        t.get(num).begin();
        t.get(num).stop();
        num++;
        continue;
      }
    }
  }

  public GameFrame getGF(){
    return gf;
  }

  // @Override
  // public void run(){
  //   try{
  //     playing = true;
  //     playGame();
  //     for(int i=120; i>0; i--){
  //       gameTime-=1;
  //       System.out.println("Seconds remaining: "+ gameTime);
  //       Thread.sleep(1000);
  //     }
  //       if(gameTime==0){
  //         playing = false;
  //       }

  //   } catch(InterruptedException ex){
  //     System.out.println("Timer and thread error");
  //   }
    
  }

