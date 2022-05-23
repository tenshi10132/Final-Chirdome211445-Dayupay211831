public class TypingTest implements Task {
    public String stringToMatch, returnString;
    public int status;
    public GameFrame gf;
    public boolean running;

    public TypingTest(GameFrame gf){
        this.gf = gf;

        stringToMatch = gf.getGC().getTask(0).get(0).getText()
            +gf.getGC().getTask(0).get(1).getText();
        returnString = " ";
        status = 2;
        running = false;
    }

    @Override
    public void begin(){
        returnString = " ";
        status = 0;
        running = true;

        System.out.println("TypingTest Started");
        gf.setTextFocusable(true);
        gf.getGC().returnTask(0);
        while(running&&returnString.equals(" ")){
            returnString = gf.getReturnString();
        }

        System.out.println("TypingTest Finished");
        if(returnString.equals(stringToMatch)&&running){
            status++;
        }else{
            status--;
        }
        gf.getGC().hideTask(0);
        running = false;
        gf.setTextFocusable(false);
        gf.resetText();
    }

    @Override
    public void stop(){
        status = 2;
        running = false;
        gf.getGC().hideTask(0);
        gf.setTextFocusable(false);
        gf.resetText();
    }

    @Override
    public int getStatus(){
        return status;
    }
}
