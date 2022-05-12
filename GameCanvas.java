import javax.swing.*;
import java.awt.*;
import java.util.*;


public class GameCanvas extends JComponent {
    private ArrayList<Rectangles> typingTest, buttonSmash,dragAndDrop, stirring, maze,
    runner, winScreen, looseScreen;
  
    public GameCanvas(){
      typingTest = new ArrayList<Rectangles>();
      typingTest.add(new Rectangles(0,200,600,40,Color.DARK_GRAY,
      "Type the sentence below as fast as you can."));
      typingTest.add(new Rectangles(0,280,600,40,Color.GRAY,
      "There is no way you can type this unmanageable, problematical, unaccomodating, troublesome,"));
      typingTest.add(new Rectangles(0,320,600,40,Color.GRAY,
      "perplexing, formidable, uncooperative, intransigent description of a sentence in under 3 seconds."));
  
      buttonSmash = new ArrayList<Rectangles>();
      buttonSmash.add(new Rectangles(100,280,400,35,Color.RED,
      "PRESS THE SPACEBAR 50 TIMES AS FAST AS YOU CAN!"));
  
      dragAndDrop = new ArrayList<Rectangles>();
      dragAndDrop.add(new Rectangles(0,0,300,600,Color.GREEN));
      dragAndDrop.add(new Rectangles(301,0,300,600,Color.LIGHT_GRAY));
      dragAndDrop.add(new Rectangles(150,0,300,30,Color.GRAY,
      "Drag and drop everything to the Green Side."));
      dragAndDrop.add(new Rectangles(350,200,80,85,Color.PINK));
      dragAndDrop.add(new Rectangles(478,500,50,50,Color.MAGENTA));
      dragAndDrop.add(new Rectangles(400,350,75,60,Color.ORANGE));
      dragAndDrop.add(new Rectangles(380,470,75,75,Color.CYAN));
      dragAndDrop.add(new Rectangles(500,45,40,40,Color.BLUE));
      dragAndDrop.add(new Rectangles(510,150,60,80,Color.RED));
  
      stirring = new ArrayList<Rectangles>();
      stirring.add(new Rectangles(0,0,300,300,Color.DARK_GRAY));
      stirring.add(new Rectangles(300,0,300,300,Color.LIGHT_GRAY));
      stirring.add(new Rectangles(0,300,300,300,Color.LIGHT_GRAY));
      stirring.add(new Rectangles(300,300,300,300,Color.DARK_GRAY));
      stirring.add(new Rectangles(150,0,300,30,Color.GRAY,
      "Do 30 revolutions across the squares as fast as you can!"));
  
      maze = new ArrayList<Rectangles>();
      maze.add(new Rectangles (0,0,60,60,Color.BLUE, "START"));
      maze.add(new Rectangles (60,0,240,60,Color.BLACK));
      maze.add(new Rectangles (60,0,60,180,Color.BLACK));
      maze.add(new Rectangles (180,60,180,60,Color.BLACK));
      maze.add(new Rectangles (0,240,240,60,Color.BLACK));
      maze.add(new Rectangles (180,180,60,60,Color.BLACK));
      maze.add(new Rectangles (0,300,60,300,Color.BLACK));
      maze.add(new Rectangles (60,480,60,120,Color.BLACK));
      maze.add(new Rectangles (300,120,60,300,Color.BLACK));
      maze.add(new Rectangles (120,360,180,60,Color.BLACK));
      maze.add(new Rectangles (180,420,60,60,Color.BLACK));
      maze.add(new Rectangles (180,540,360,60,Color.BLACK));
      maze.add(new Rectangles (300,480,180,60,Color.BLACK));
      maze.add(new Rectangles (420,0,180,60,Color.BLACK));
      maze.add(new Rectangles (540,60,60,180,Color.BLACK));
      maze.add(new Rectangles (420,120,60,360,Color.BLACK));
      maze.add(new Rectangles (480,300,60,60,Color.BLACK));
      maze.add(new Rectangles (540,420,60,60,Color.BLACK));
      maze.add(new Rectangles (540,540,60,60,Color.RED, "FINISH"));
  
      runner = new ArrayList<Rectangles>();
      runner.add(new Rectangles(0,0,600,60,Color.RED, "FINISH LINE"));
      runner.add(new Rectangles(0,60,180,60,Color.BLACK));
      runner.add(new Rectangles(300,120,300,60,Color.BLACK));
      runner.add(new Rectangles(0,240,300,60,Color.BLACK));
      runner.add(new Rectangles(420,300,180,60,Color.BLACK));
      runner.add(new Rectangles(0,420,360,60,Color.BLACK));
      runner.add(new Rectangles(480,480,120,60,Color.BLACK));
      runner.add(new Rectangles(120,540,120,60,Color.GREEN, "YOU"));
  
      winScreen = new ArrayList<Rectangles>();
      winScreen.add(new Rectangles(200,250,200,50,Color.GREEN,
      "CONGRATULATIONS! YOU WIN!"));
      winScreen.add(new Rectangles(200,300,200,50,Color.GREEN,
      "Plus one point!"));
  
      looseScreen = new ArrayList<Rectangles>();
      looseScreen.add(new Rectangles(200,250,200,50,Color.RED,
      "TOO BAD! YOU LOSE!"));
      looseScreen.add(new Rectangles(200,300,200,50,Color.RED,
      "No new points added"));
  
  
    }
  
  
    @Override
    protected void paintComponent(Graphics g){
      for (Rectangles r : typingTest){
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        if (r.getText() != null){
          int width = g.getFontMetrics().stringWidth(r.getText());
          g.setColor(Color.WHITE);
          g.drawString(r.getText(),r.getX()+((r.getWidth()-width)/2),r.getY()+(r.getHeight()/2));
        }
      }
  
      for (Rectangles r : buttonSmash){
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        if (r.getText() != null){
          int width = g.getFontMetrics().stringWidth(r.getText());
          g.setColor(Color.WHITE);
          g.drawString(r.getText(),r.getX()+((r.getWidth()-width)/2),r.getY()+(r.getHeight()/2));
        }
      }
      for (Rectangles r : dragAndDrop){
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        if (r.getText() != null){
          int width = g.getFontMetrics().stringWidth(r.getText());
          g.setColor(Color.WHITE);
          g.drawString(r.getText(),r.getX()+((r.getWidth()-width)/2),r.getY()+(r.getHeight()/2));
        }
      }
      for (Rectangles r : stirring){
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        if (r.getText() != null){
          int width = g.getFontMetrics().stringWidth(r.getText());
          g.setColor(Color.WHITE);
          g.drawString(r.getText(),r.getX()+((r.getWidth()-width)/2),r.getY()+(r.getHeight()/2));
        }
      }
      for (Rectangles r : maze){
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        if (r.getText() != null){
          int width = g.getFontMetrics().stringWidth(r.getText());
          g.setColor(Color.WHITE);
          g.drawString(r.getText(),r.getX()+((r.getWidth()-width)/2),r.getY()+(r.getHeight()/2));
        }
      }
      for (Rectangles r : runner){
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        if (r.getText() != null){
          int width = g.getFontMetrics().stringWidth(r.getText());
          g.setColor(Color.WHITE);
          g.drawString(r.getText(),r.getX()+((r.getWidth()-width)/2),r.getY()+(r.getHeight()/2));
        }
      }
  
      for (Rectangles r : winScreen){
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        if (r.getText() != null){
          int width = g.getFontMetrics().stringWidth(r.getText());
          g.setColor(Color.WHITE);
          g.drawString(r.getText(),r.getX()+((r.getWidth()-width)/2),r.getY()+(r.getHeight()/2));
        }
      }
      for (Rectangles r : looseScreen){
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        if (r.getText() != null){
          int width = g.getFontMetrics().stringWidth(r.getText());
          g.setColor(Color.WHITE);
          g.drawString(r.getText(),r.getX()+((r.getWidth()-width)/2),r.getY()+(r.getHeight()/2));
        }
      }
    }

    public ArrayList<Rectangles> getRects(String task){
        if(task.equals("t")){
            return typingTest;
        }else if(task.equals("b")){
            return buttonSmash;
        }else if(task.equals("d")){
            return dragAndDrop;
        }else if(task.equals("s")){
            return stirring;
        }else if(task.equals("m")){
            return maze;
        }else if(task.equals("r")){
            return runner;
        }else{
            return null;
        }
    }
}
