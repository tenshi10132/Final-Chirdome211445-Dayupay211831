import java.util.*;

public class DragAndDrop implements Task{
    public Rectangle r;
    public ArrayList<Rectangle> d = new ArrayList<Rectangle>();
    public boolean b[] = new boolean[6];
    public int counter, holder, status;
    public GameFrame gf;
    public boolean running, holding;

    public DragAndDrop(GameFrame gf){
        this.gf = gf;

        for(int i=0;i<6;i++){
            d.add(gf.getGC().getTask(2).get(i+1));
            b[i] = false;
        }
        r = gf.getGC().getTask(2).get(8);
        
        counter = 0;
        status = 2;
        running = false;
    }

    @Override
    public void start(){
        status = 0;
        running = true;
        gf.getGC().returnTask(2);

        System.out.println("DragAndDrop Started");
        
        while(counter<6&&running){
            System.out.print("");
            for(int i=0;i<6;i++){
                if(d.get(i).isColliding(gf.getMouseX(),gf.getMouseY())&&gf.mouseIsPressed()){
                    if(!holding){
                        d.get(i).setPosition(gf.getMouseX()-(d.get(i).getWidth()/2),
                        gf.getMouseY()-(d.get(i).getHeight()/2));
                    }
                    gf.getGC().repaint();
                }
                if(d.get(i).getX()<300){
                    b[i] = true;
                }else{
                    b[i] = false;
                }
            }
            holder = 0;
            for(Rectangle d : d){
                if(d.isColliding(gf.getMouseX(),gf.getMouseY())){
                    holder++;
                }
            }
            holding = (holder>1);
            
            counter = 0;
            for(boolean b : b){
                if(b){
                    counter++;
                }
            }
            r.setText(""+counter);
        }

        System.out.println("DragAndDrop Finished");

        if(running){
            gf.getGC().returnTask(6);
            status++;
        }else{
            gf.getGC().returnTask(7);
            status--;
        }
        running = false;
    }

    @Override
    public void stop(){
        status = 2;
        running = false;
        gf.getGC().hideTask(2);
    }

    @Override
    public int getStatus(){
        return status;
    }
}
