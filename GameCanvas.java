import javax.swing.*;
import java.awt.*;
import java.util.*;


public class GameCanvas extends JComponent {
    public ArrayList<Rectangle> typingTest, buttonSmash, dragAndDrop, stirring, maze, runner, winScreen, loseScreen, tieScreen;
    public ArrayList<ArrayList<Rectangle>> tasks = new ArrayList<ArrayList<Rectangle>>();
    Graphics2D g2d;

    public GameCanvas(){
        typingTest = new ArrayList<Rectangle>();
        //Sentence [0-1]
            typingTest.add(new Rectangle(0,280,600,40,Color.GRAY,
                "There is no way you can type this unmanageable "));
            typingTest.add(new Rectangle(0,320,600,40,Color.GRAY,
                "description of a sentence in under 3 seconds."));
        //Instructions [2]
            typingTest.add(new Rectangle(0,200,600,40,Color.DARK_GRAY,
                "Type the sentence below as fast as you can."));
        
        buttonSmash = new ArrayList<Rectangle>();
        //Instructions [0]
            buttonSmash.add(new Rectangle(100,265,400,35,Color.RED,
                "PRESS THE SPACEBAR 50 TIMES AS FAST AS YOU CAN!"));
        //Counter [1]
            buttonSmash.add(new Rectangle(280,300,40,40,Color.RED,"0"));
        
        dragAndDrop = new ArrayList<Rectangle>();
        //Background [0]
            dragAndDrop.add(new Rectangle(0,0,300,600,Color.GREEN));
        //Objects [1-6]
            dragAndDrop.add(new Rectangle(350,200,80,85,Color.PINK));
            dragAndDrop.add(new Rectangle(478,500,50,50,Color.MAGENTA));
            dragAndDrop.add(new Rectangle(400,350,75,60,Color.ORANGE));
            dragAndDrop.add(new Rectangle(380,470,75,75,Color.CYAN));
            dragAndDrop.add(new Rectangle(500,45,40,40,Color.BLUE));
            dragAndDrop.add(new Rectangle(510,150,60,80,Color.RED));
        //Instructions [7]
            dragAndDrop.add(new Rectangle(150,0,300,30,Color.GRAY,
                "Drag and drop everything to the Green Side."));
        //Counter [8]
            dragAndDrop.add(new Rectangle(280,30,40,40,Color.RED,"0"));
        
        stirring = new ArrayList<Rectangle>();
        //Background [0-3]
            stirring.add(new Rectangle(0,0,300,300,Color.LIGHT_GRAY));
            stirring.add(new Rectangle(0,300,300,300,Color.LIGHT_GRAY));
            stirring.add(new Rectangle(300,300,300,300,Color.LIGHT_GRAY));
            stirring.add(new Rectangle(300,0,300,300,Color.LIGHT_GRAY));
        //Instructions [4]
            stirring.add(new Rectangle(140,0,320,30,Color.GRAY,
                "Do 30 revolutions around the red square as fast as you can!"));
        //Counter [5]
            stirring.add(new Rectangle(250,250,100,100,Color.RED, "0"));
        
        maze = new ArrayList<Rectangle>();
        //Objects [0-18]
            maze.add(new Rectangle(1,1,60,60,Color.BLUE, "START"));
            maze.add(new Rectangle(60,0,240,60,Color.BLACK));
            maze.add(new Rectangle(60,0,60,180,Color.BLACK));
            maze.add(new Rectangle(180,60,180,60,Color.BLACK));
            maze.add(new Rectangle(0,240,240,60,Color.BLACK));
            maze.add(new Rectangle(180,180,60,60,Color.BLACK));
            maze.add(new Rectangle(0,300,60,300,Color.BLACK));
            maze.add(new Rectangle(60,480,60,120,Color.BLACK));
            maze.add(new Rectangle(300,120,60,300,Color.BLACK));
            maze.add(new Rectangle(120,360,180,60,Color.BLACK));
            maze.add(new Rectangle(180,420,60,60,Color.BLACK));
            maze.add(new Rectangle(180,540,360,60,Color.BLACK));
            maze.add(new Rectangle(300,480,180,60,Color.BLACK));
            maze.add(new Rectangle(420,0,180,60,Color.BLACK));
            maze.add(new Rectangle(540,60,60,180,Color.BLACK));
            maze.add(new Rectangle(420,120,60,360,Color.BLACK));
            maze.add(new Rectangle(480,300,60,60,Color.BLACK));
            maze.add(new Rectangle(540,420,60,60,Color.BLACK));
            maze.add(new Rectangle(540,540,60,60,Color.RED, "FINISH"));
        
        runner = new ArrayList<Rectangle>();
        //Objects [0-10]
            runner.add(new Rectangle(0,0,600,50,Color.RED, "FINISH LINE"));
            runner.add(new Rectangle(100,100,500,30,Color.BLACK));
            runner.add(new Rectangle(0,200,300,30,Color.BLACK));
            runner.add(new Rectangle(400,200,200,30,Color.BLACK));
            runner.add(new Rectangle(200,300,400,30,Color.BLACK));
            runner.add(new Rectangle(0,300,100,30,Color.BLACK));
            runner.add(new Rectangle(0,400,400,30,Color.BLACK));
            runner.add(new Rectangle(500,400,100,30,Color.BLACK));
            runner.add(new Rectangle(300,500,300,30,Color.BLACK));
            runner.add(new Rectangle(0,500,200,30,Color.BLACK));
            runner.add(new Rectangle(120,570,30,30,Color.GREEN, "YOU"));
        
        winScreen = new ArrayList<Rectangle>();
            winScreen.add(new Rectangle(200,250,200,100,Color.GREEN,
                "CONGRATULATIONS! YOU WIN!"));
        
        loseScreen = new ArrayList<Rectangle>();
            loseScreen.add(new Rectangle(200,250,200,100,Color.RED,
                "TOO BAD! YOU LOSE!"));

        tieScreen = new ArrayList<Rectangle>();
            tieScreen.add(new Rectangle(200,250,200,100,Color.BLUE,
                "DAS CRAZY! ISSA TIE!"));

        tasks.add(typingTest);
        tasks.add(buttonSmash);
        tasks.add(dragAndDrop);
        tasks.add(stirring);
        tasks.add(maze);
        tasks.add(runner);
        tasks.add(winScreen);
        tasks.add(loseScreen);
        tasks.add(tieScreen);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        //Draw Objects
            for(ArrayList<Rectangle> t : tasks){
                for(Rectangle r : t){
                    r.draw(g);
                }
            }
    } 

    public ArrayList<Rectangle> getTask(int i){
        return tasks.get(i);
    }

    public void returnTask(int i){
        hideTask();
        for(Rectangle r : getTask(i)){
            r.returnPosition();
            repaint();
        }
    }

    public void hideTask(int i){
        for(Rectangle r: getTask(i)){
            r.hidePosition();
            repaint();
        }
    }

    public void hideTask(){
        for(ArrayList<Rectangle> t : tasks){
            for(Rectangle r : t){
                r.hidePosition();
                repaint();
            }
        }
    }
}