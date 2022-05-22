import java.util.*;

public class Maze implements Task{
    public Rectangle start, finish;
    public ArrayList<Rectangle> w = new ArrayList<Rectangle>();
    public int status;
    public GameFrame gf;
    public boolean running, finished;

    public Maze(GameFrame gf){
        this.gf = gf;

        start = gf.getGC().getTask(4).get(0);
        for(int i=1;i<18;i++){
            w.add(gf.getGC().getTask(4).get(i));
        }
        finish = gf.getGC().getTask(4).get(18);

        status = 2;
        running = false;
        finished = false;
    }

    @Override
    public void begin(){
        status = 0;
        running = false;
        finished = false;
        gf.getGC().hideTask();
        start.returnPosition();
        System.out.println("Maze Waiting");

        while(!running){
            System.out.print("");
            if(start.isColliding(gf.getMouseX(),gf.getMouseY())){
                running = true;
                System.out.println("Maze Started");
                gf.getGC().returnTask(4);
            }
        }

        while(!finished&&running){
            System.out.print("");
            for(Rectangle w : w){
                if(w.isColliding(gf.getMouseX(),gf.getMouseY())){
                    running = false;
                }
            }
            if(finish.isColliding(gf.getMouseX(),gf.getMouseY())){
                finished = true;
            }
        }

        System.out.println("Maze Finished");
        if(finished){
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
        gf.getGC().hideTask(4);
    }

    @Override
    public int getStatus(){
        return status;
    }
}
