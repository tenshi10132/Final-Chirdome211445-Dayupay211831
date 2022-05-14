public class Stirring implements Task {
    public int momentX, momentY, counter;
    public Rectangle q1, q2, q3, q4, r;
    public GameFrame gf;
    public boolean running;

    public Stirring(GameFrame gf){
        q1 = gf.getGC().getTask(3).get(0);
        q2 = gf.getGC().getTask(3).get(1);
        q3 = gf.getGC().getTask(3).get(2);
        q4 = gf.getGC().getTask(3).get(3);
        r = gf.getGC().getTask(3).get(5);
        momentX = gf.getMouseX();
        momentY = gf.getMouseY();
        counter = 0;
        this.gf = gf;
        running = false;
    }

    @Override
    public void start(){
        running = true;
        gf.getGC().returnTask(3);

        while(counter<30&&running){
            r.setText(""+counter);
            gf.getGC().repaint();

            if(q1.isColliding(momentX, momentY)){
                if(q2.isColliding(gf.getMouseX(),gf.getMouseY())){
                    momentX = gf.getMouseX();
                    momentY = gf.getMouseY();
                }
            }else if(q2.isColliding(momentX, momentY)){
                if(q3.isColliding(gf.getMouseX(),gf.getMouseY())){
                    momentX = gf.getMouseX();
                    momentY = gf.getMouseY();
                }
            }else if(q3.isColliding(momentX, momentY)){
                if(q4.isColliding(gf.getMouseX(),gf.getMouseY())){
                    momentX = gf.getMouseX();
                    momentY = gf.getMouseY();
                }
            }else{
                if(q1.isColliding(gf.getMouseX(),gf.getMouseY())){
                    momentX = gf.getMouseX();
                    momentY = gf.getMouseY();
                    counter++;
                }
            }
        }

        if(running){
            gf.getGC().returnTask(6);
        }else{
            gf.getGC().returnTask(7);
        }
    }

    @Override
    public void stop(){
        running = false;
        gf.getGC().hideTask(3);
    }

    @Override
    public boolean isRunning(){
        return running;
    }
}
