package myPackage;

import java.awt.GridLayout;
import javax.swing.JFrame;
import myPackage.Graphics.Panel;

//Frame class created from 'JFrame class' (JFrame imported)
public class Frame extends JFrame {

    //constructor for Frame class
    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //closes game on exit button
        setTitle("Snake Game Project");
        setResizable(false);
        
        init();
    }

    
    //----------------------------------------------------------------------------------------------------------------------

    

    
    //start game
    public void init() {
        setLayout(new GridLayout(1,1,0,0)); // the grid of the game(GridLayout imported)                                 
        
        Panel s = new Panel(); ////Panel Object created from 'JPanel class' (Panel class imported from Panel.java)
        
        add(s);    //adding Panel object to the Frame object
        pack(); //meaning if we set panel_size = x, frame_size = x too
        setLocationRelativeTo(null);    //puts application in center of computer screen instead of top-left
        setVisible(true);
    }
    
    //main method
    public static void main(String[] args) {
        new Frame(); //frame Object
    }
}