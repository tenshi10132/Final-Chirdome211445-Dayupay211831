import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class Runner implements Task{
    public Rectangle you, finish;
    public ArrayList<Rectangle> o = new ArrayList<Rectangle>();
    public int status;
    public GameFrame gf;
    public boolean running, finished;

    public Runner(GameFrame gf){
        this.gf = gf;

        finish = gf.getGC().getTask(5).get(0);
        for(int i=1;i<10;i++){
            o.add(gf.getGC().getTask(5).get(i));
        }
        you = gf.getGC().getTask(5).get(10);

        status = 2;
        running = false;
        finished = false;
    }

    @Override
    public void begin(){
        Timer animationTimer = new Timer(50, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                you.moveY(-1);
                gf.getGC().repaint();
            }
        });

        status = 0;
        gf.getGC().returnTask(5);
        System.out.println("Runner Waiting");
        while(you.getX()==120){
            System.out.print("");
        }
        running = true;
        finished = false;

        System.out.println("Runner Started");
        while(!finished&&running){
            animationTimer.start();
            for(Rectangle o : o){
                if(o.isColliding(you)){
                    running = false;
                }
            }
            if(finish.isColliding(you)){
                finished = true;
            }
        }
        animationTimer.stop();

        System.out.println("Runner Finished");
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
        gf.getGC().hideTask(5);
    }

    @Override
    public int getStatus(){
        return status;
    }
}
