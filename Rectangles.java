import java.awt.Color;

public class Rectangles {

  private int x;
  private int origX;
  private int y;
  private int origY;
  private int w;
  private int h;
  private Color color;
  private String text;

  public Rectangles(int x,int y,int w,int h, Color color){
    this.x = x;
    origX = x;
    this.y = y;
    origY = y;
    this.w = w;
    this.h = h;
    this.color = color;
  }

  public Rectangles(int x,int y,int w,int h, Color color, String text){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.color = color;
    this.text = text;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public int getWidth(){
    return w;
  }

  public int getHeight(){
    return h;
  }

  public String getText(){
    return text;
  }

  public Color getColor(){
    return color;
  }

  public void moveX(int speed){
    x += speed;
  }

  public void moveY(int speed){
    y += speed;
  }

//to take out of frame
  public void setPosition(){
    x = 0 - w;
    y = 0 - h;
  }

//pit back into frame
  public void backToPositon(){
    x = origX;
    y = origY;
  }

  public boolean isColliding(Rectangles r2){
    return !(this.x + this.w <= r2.getX() || this.x >= r2.getX() + r2.getWidth()||
    this.y + this.h <= r2.getY() || this.y >= r2.getY() + r2.getHeight());
  }

  // public boolean cursorIsColliding(Rectangles r2){
  // for cursor info (up to you how you wanna code this, but this was how it started in my head)
  //   int mouseX = MouseInfo.getPointerInfo().getLocation().getX();
  //   int mouseY = MouseInfo.getPointerInfo().getLocation().getY();
  //   return !
  // }

}

