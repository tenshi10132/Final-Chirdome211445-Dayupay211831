import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame{
    private JFrame frame;
    private JLabel playerId, timer, myPoints, enemyPoints;
    private JPanel header, footer;
    private JButton button;
    private JTextArea text;
    public GameCanvas gc;
    public boolean mousePressed = false;
    public int mouseX = 0;
    public int mouseY = 0;
    public int counter = 0;
    public String returnString = " ";

    public GameFrame(int playerNum) {
        frame = new JFrame();
        header = new JPanel(new GridLayout(10, 1));
        playerId = new JLabel("Player #" + playerNum + "                  .");
        timer = new JLabel("2:00");
        myPoints = new JLabel("My Points: " + 0);
        enemyPoints = new JLabel("Enemy Points: " + 0);
        footer = new JPanel(new GridLayout(2,1));
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
        frame.getContentPane().add(header, BorderLayout.EAST);
        header.add(playerId);
        header.add(timer);
        header.add(myPoints);
        header.add(enemyPoints);
        frame.getContentPane().add(footer, BorderLayout.SOUTH);
        footer.add(text);
        footer.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        text.setFocusable(true);
        button.setFocusable(false);
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
                        break;
                    case KeyEvent.VK_LEFT :
                        gc.getTask(5).get(10).moveX(-10);
                        gc.repaint();
                        break;
                    case KeyEvent.VK_RIGHT :
                        gc.getTask(5).get(10).moveX(10);
                        gc.repaint();
                        break;
                }}
            public void keyTyped(KeyEvent ke){}
            public void keyReleased(KeyEvent ke){}
        };
        frame.addKeyListener(kl);
    }
    public void setUpMouseListener(){
        MouseMotionListener ml = new MouseMotionListener(){
            public void mouseDragged(MouseEvent me){
                mousePressed = true;
                mouseX = me.getX();
                mouseY = me.getY();
            }
            public void mouseMoved(MouseEvent me){
                mousePressed = false;
                mouseX = me.getX();
                mouseY = me.getY();
            }
        };
        frame.getContentPane().addMouseMotionListener(ml);
    }
    public void setUpButtonListener(){
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                returnString = text.getText();
                System.out.println("Button Pressed");
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
    public boolean mouseIsPressed(){
        return mousePressed;
    }
    public int getCounter(){
        return counter;
    }
    public String getReturnString(){
        return returnString;
    }

    //Mutator Methods
    public void setTextFocusable(boolean b){
        text.setFocusable(b);
    }
    public void resetCounter(){
        counter = 0;
    }
    public void resetText(){
        text.setText("");
    }
    // public void setHeader(String string){
    //     header.setText(string);
    // }

    // public static void main(String[] args){
    //     GameFrame gf = new GameFrame();
    //     gf.setUpGUI();
    //     gf.setUpKeyListener();
    //     gf.setUpMouseListener();
    //     gf.setUpButtonListener();
    //
    //     ArrayList<Task> t = new ArrayList<Task>();
    //     t.add(new TypingTest(gf));
    //     t.add(new ButtonSmash(gf));
    //     t.add(new DragAndDrop(gf));
    //     t.add(new Stirring(gf));
    //     t.add(new Maze(gf));
    //     t.add(new Runner(gf));
    //
    //     Scanner sc = new Scanner(System.in);
    //     int input;
    //     do{
    //         input = Integer.parseInt(sc.next());
    //         if(input>5||input<0){
    //             break;
    //         }
    //
    //         t.get(input).begin();
    //         t.get(input).stop();
    //     }while(true);

}
