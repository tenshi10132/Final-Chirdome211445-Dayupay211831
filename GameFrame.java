import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame{
    private JFrame frame;
    private JLabel header;
    private JPanel footer;
    private JButton button;
    private JTextArea text;
    public GameCanvas gc;
    public boolean mousePressed;
    public int mouseX = 0;
    public int mouseY = 0;
    public int counter = 0;
    public String returnString;



    public GameFrame() {
        frame = new JFrame();
        header = new JLabel("Header");
        footer = new JPanel(new GridLayout(1,2));
        button = new JButton("Button");
        text = new JTextArea("Footer");
        gc = new GameCanvas();
        gc.hideTask();
    }

    //SetUpGameFrame
    public void setUpGUI(){
        frame.setTitle("Mission Marathon : Complete the Quest (Don't be the Last to Pass the Task)");
        frame.getContentPane().add(gc, BorderLayout.CENTER);
        gc.setPreferredSize(new Dimension(600,600));
        frame.getContentPane().add(header, BorderLayout.NORTH);
        frame.getContentPane().add(footer, BorderLayout.SOUTH);
        footer.add(text);
        footer.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setFocusable(true);
        frame.setVisible(true);
    }
    public void setUpKeyListener(){
        KeyListener kl = new KeyListener(){
            public void keyPressed(KeyEvent ke){
                int keyCode = ke.getKeyCode();
                switch (keyCode){
                    case KeyEvent.VK_SPACE :
                        counter++;
                        System.out.println("Counter = " + counter);
                        break;
                    case KeyEvent.VK_LEFT :
                        gc.getTask(5).get(8).moveX(-10);
                        gc.repaint();
                        System.out.println("Left Pressed");
                        break;
                    case KeyEvent.VK_RIGHT :
                        gc.getTask(5).get(8).moveX(10);
                        gc.repaint();
                        System.out.println("Right Pressed");
                        break;
                }}
            public void keyTyped(KeyEvent ke){}
            public void keyReleased(KeyEvent ke){}
        };
        frame.addKeyListener(kl);
    }
    public void setUpMouseListener(){
        MouseListener ml = new MouseListener(){
            public void mousePressed(MouseEvent me){
                mousePressed = true;
                mouseX = me.getX();
                mouseY = me.getY();
            }
            public void mouseReleased(MouseEvent me){
                mousePressed = false;
            }
            public void mouseEntered(MouseEvent me){}
            public void mouseExited(MouseEvent me){}
            public void mouseClicked(MouseEvent me){}
        };
        frame.addMouseListener(ml);
    }
    public void setUpButtonListener(){
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                returnString = text.getText();
            }
        };
        button.addActionListener(al);
    }

    //Accessor Methods
    public GameCanvas getGC(){
        return gc;
    }    
    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }
    public int getCounter(){
        return counter;
    }
    public String getReturnString(){
        return returnString;
    }

    //Mutator Methods
    public void resetCounter(){
        counter = 0;
    }

    public static void main(String[] args){
        GameFrame gf = new GameFrame();
        gf.setUpGUI();
        gf.setUpKeyListener();
        gf.setUpMouseListener();
        gf.setUpButtonListener();

        //Reveal Runner Task
        gf.getGC().returnTask(5);
    }
}