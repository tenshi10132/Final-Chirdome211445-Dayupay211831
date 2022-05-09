import java.awt.*;
import java.awt.geom.*;

public class Rectangle {
    public int x, y, width, height, textSize;
    public Color color;
    public String text = "";

    //Constructors
    public Rectangle(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }    
    public Rectangle(int x, int y, int width, int height, Color color, String text){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.text = text;
        textSize = height;
    }

    //Draw
    public void draw(Graphics2D g2d){ 
        //Rectangle     
        g2d.setColor(color);
        g2d.fillRect(x,y,width,height);

        //Text
        if(!text.equals("")){
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Serif", Font.PLAIN, textSize));
            g2d.drawString(text, x, (int)(y+(0.75*textSize)));
        }
    }

    //Accessor Methods
    public int getX(){
        return x;
    }    
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public String getText(){
        return text;
    }

    //Mutator Methods
    public void moveX(int speed){
        x += speed;
    }
    public void moveY(int speed){
        y += speed;
    }    
    public void setX(int x){
        this.x = x;
    }    
    public void setY(int y){
        this.y = y;
    }
    public void setText(String text){
        this.text=text;
    }

    //Condition Methods
    public boolean isColliding(Rectangle r){
        if(x+width>=r.getX() && x<=r.getX()+r.getWidth() && 
            y+height>=r.getY() && y<=r.getY()+r.getHeight()){
            return true;
        }else{
            return false;
        }       
    }
    public boolean isColliding(int mouseX, int mouseY){
        if(mouseX>x && mouseX<x+width && 
            mouseY>y && mouseY<y+height){
            return true;
        }else{
            return false;
        }
    }
}
