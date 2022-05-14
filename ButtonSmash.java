public class ButtonSmash implements Task {
    public int counter;
    public Rectangle r;
    public GameFrame gf;
    public boolean running;

    public ButtonSmash(GameFrame gf){
        r = gf.getGC().getTask(1).get(1);
        gf.resetCounter();
        counter = gf.getCounter();
        this.gf = gf;
        running = false;
    }

    @Override
    public void start(){
        running = true;
        gf.getGC().returnTask(1);

        while(counter<50&&running){
            counter = gf.getCounter();
            r.setText(""+counter);
            gf.getGC().repaint();
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
        gf.getGC().hideTask(1);
    }

    @Override
    public boolean isRunning(){
        return running;
    }
}
