public class Countdown extends Thread{

    private int gameTime = 120;   
       
    @Override
    public void run(){
        try{
            for(int i=120; i>0; i--){               
                gameTime--;
                System.out.println("Seconds remaining: "+ gameTime);
                Thread.sleep(1000);
            }


        } catch(InterruptedException ex){
            System.out.println("Timer and thread error");
        }
    
  }

  public int getTime(){
      return gameTime;
  }    
}
