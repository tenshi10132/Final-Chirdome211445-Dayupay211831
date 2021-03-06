public class ButtonSmash implements Task {
    public Rectangle r;
    public int counter, status;
    public GameFrame gf;
    public boolean running;

    public ButtonSmash(GameFrame gf){
        this.gf = gf;

        r = gf.getGC().getTask(1).get(1);

        gf.resetCounter();
        counter = gf.getCounter();
        status = 2;
        running = false;
    }

    @Override
    public void begin(){
        counter = 0;
        gf.resetCounter();
        status = 0;
        running = true;
        gf.getGC().returnTask(1);

        System.out.println("ButtonSmash Started");
        while(counter<50&&running){
            counter = gf.getCounter();
            r.setText(""+counter);
            gf.getGC().repaint();
        }

        System.out.println("ButtonSmash Finished");
        if(running){
            status++;
        }else{
            status--;
        }
        gf.getGC().hideTask(1);
        running = false;
        gf.resetCounter();
    }

    @Override
    public void stop(){
        status = 2;
        running = false;
        gf.getGC().hideTask(1);
        gf.resetCounter();
    }

    @Override
    public int getStatus(){
        return status;
    }
}
