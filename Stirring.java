public class Stirring implements Task {
    public Rectangle q1, q2, q3, q4, r;
    public int momentX, momentY, counter, status;
    public GameFrame gf;
    public boolean running;

    public Stirring(GameFrame gf){
        this.gf = gf;

        q1 = gf.getGC().getTask(3).get(0);
        q2 = gf.getGC().getTask(3).get(1);
        q3 = gf.getGC().getTask(3).get(2);
        q4 = gf.getGC().getTask(3).get(3);
        r = gf.getGC().getTask(3).get(5);
        momentX = gf.getMouseX();
        momentY = gf.getMouseY();

        counter = 0;
        status = 2;
        running = false;
    }

    @Override
    public void begin(){
        counter = 0;
        status = 0;
        running = true;
        gf.getGC().returnTask(3);

        System.out.println("Stirring Started");
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

        System.out.println("Stirring Finished");

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
        gf.getGC().hideTask(3);
    }

    @Override
    public int getStatus(){
        return status;
    }
}
