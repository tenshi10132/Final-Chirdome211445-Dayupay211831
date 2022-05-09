import javax.swing.*;
import java.awt.*;
import java.util.*;


public class GameCanvas extends JComponent {
    public ArrayList<Rectangle> tRects, bRects, dRects, sRects, mRects, rRects;
    Graphics2D g2d;

    public GameCanvas(){
        //TypingTest
            tRects = new ArrayList<Rectangle>();
            //String To Match
                tRects.add(new Rectangle(0, 500, 100, 100, Color.RED, "Task"));
                tRects.add(new Rectangle(500, 0, 100, 100, Color.RED, "Task"));
            
        //ButtonSmash
            bRects = new ArrayList<Rectangle>();
            // //Counter
            //     bRects.add(new Rectangle(, text));
            
        //DragAndDrop
            dRects = new ArrayList<Rectangle>();
            // //Boxes
            //     dRects.add(new Rectangle());
            // //Counter
            //     dRects.add(new Rectangle(, text));
        
            
        //Stirring
            sRects = new ArrayList<Rectangle>();
            // //Quadrants
            //     sRects.add(new Rectangle());
            //     sRects.add(new Rectangle());
            //     sRects.add(new Rectangle());
            //     sRects.add(new Rectangle());
            // //Counter
            //     sRects.add(new Rectangle(, text));
        
        //Maze    
            mRects = new ArrayList<Rectangle>();
            // //Start
            //     mRects.add(new Rectangle());
            // //End
            //     mRects.add(new Rectangle());
            // //Walls
            //     mRects.add(new Rectangle());
            //     mRects.add(new Rectangle());
            //     mRects.add(new Rectangle());
            //     mRects.add(new Rectangle());
            //     mRects.add(new Rectangle());
            //     mRects.add(new Rectangle());
            //     mRects.add(new Rectangle());
                   
        //Runner
            rRects = new ArrayList<Rectangle>();
            // //Player
            //     rRects.add(new Rectangle());
            // //End
            //     rRects.add(new Rectangle());
            // //Obstacles
            //     rRects.add(new Rectangle());
            //     rRects.add(new Rectangle());
            //     rRects.add(new Rectangle());
            //     rRects.add(new Rectangle());
            //     rRects.add(new Rectangle());
            //     rRects.add(new Rectangle());               
    }

    @Override
    protected void paintComponent(Graphics g){
        g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        //Draw Objects
            for(Rectangle r : tRects){
                r.draw(g2d);
            }
            for(Rectangle r : bRects){
                r.draw(g2d);
            }
            for(Rectangle r : dRects){
                r.draw(g2d);
            }
            for(Rectangle r : sRects){
                r.draw(g2d);
            }
            for(Rectangle r : mRects){
                r.draw(g2d);
            }
            for(Rectangle r : rRects){
                r.draw(g2d);
            }
            
    } 

    public ArrayList<Rectangle> getRects(String task){
        if(task.equals("t")){
            return tRects;
        }else if(task.equals("b")){
            return bRects;
        }else if(task.equals("d")){
            return dRects;
        }else if(task.equals("s")){
            return sRects;
        }else if(task.equals("m")){
            return mRects;
        }else if(task.equals("r")){
            return rRects;
        }else{
            return null;
        }
    }
}
