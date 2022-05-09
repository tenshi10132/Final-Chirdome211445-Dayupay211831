import javax.swing.*;
import java.awt.*;

public class GameFrame{
    private JFrame frame;
    private JLabel header;
    private JPanel footer;
    private JButton button;
    private JTextArea text;
    private int width, height;
    public GameCanvas gc;


    public GameFrame() {
      frame = new JFrame();
      header = new JLabel("Header");
      footer = new JPanel(new GridLayout(1,2));
      button = new JButton("Button");
      text = new JTextArea("Footer");
      width = 600;
      height = 600;
      gc = new GameCanvas();
    }

    //Set Up GUI
        public void setUpGUI(){
            frame.setTitle("Mission Marathon : Complete the Quest (Don't be the Last to Pass the Task)");
            frame.getContentPane().add(gc, BorderLayout.CENTER);
            gc.setPreferredSize(new Dimension(width,height));
            frame.getContentPane().add(header, BorderLayout.NORTH);
            frame.getContentPane().add(footer, BorderLayout.SOUTH);
            footer.add(text);
            footer.add(button);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    
    public static void main(String[] args){
      GameFrame gf = new GameFrame();
      gf.setUpGUI();
    }
}