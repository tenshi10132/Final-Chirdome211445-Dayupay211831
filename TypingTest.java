public class TypingTest implements Task {
    public String stringToMatch, returnString;
    public GameFrame gf;
    public boolean running;

    public TypingTest(GameFrame gf){
        stringToMatch = gf.getGC().getTask(0).get(0).getText()
            +gf.getGC().getTask(0).get(1).getText();
        returnString = " ";
        this.gf = gf;
        running = false;
    }

    @Override
    public void start(){
        running = true;
        gf.getGC().returnTask(0);
        while(running&&returnString.equals(" ")){
            returnString = gf.getReturnString();
        }
        if(returnString.equals(stringToMatch)&&running){
            gf.getGC().returnTask(6);
        }else{
            gf.getGC().returnTask(7);
        }
    }

    @Override
    public void stop(){
        running = false;
        gf.getGC().hideTask(0);
    }

    @Override
    public boolean isRunning(){
        return running;
    }
}
