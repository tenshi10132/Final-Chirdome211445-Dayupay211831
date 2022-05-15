import java.awt.*;

public class Rectangle {
    public int x, y, width, height, textSize, origX, origY;
    public Color color;
    public String text = "";

    //Constructors
    public Rectangle(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        origX = x;
        origY = y;
    }    
    public Rectangle(int x, int y, int width, int height, Color color, String text){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.text = text;
        textSize = height;
        origX = x;
        origY = y;
    }

    //Draw
    public void draw(Graphics g){ 
        //Rectangle     
        g.setColor(color);
        g.fillRect(x,y,width,height);

        //Text
        if(!text.equals("")){
            g.setColor(Color.WHITE);
            g.drawString(text, x+(width-g.getFontMetrics().stringWidth(text))/2, y+(height/2));
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
    public void setText(String text){
        this.text = text;
    }
    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void returnPosition(){
        x = origX;
        y = origY;
    }
    public void hidePosition(){
        x = -width;
        y = -height;
    }

    //Condition Methods
    public boolean isColliding(Rectangle r){
        return(x+width>=r.getX() && x<=r.getX()+r.getWidth() && 
            y+height>=r.getY() && y<=r.getY()+r.getHeight());
    }
    public boolean isColliding(int mouseX, int mouseY){
        return(mouseX>=x && mouseX<=(x+width) && 
            mouseY>=y && mouseY<=(y+height));
    }
}
