package myPackage.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import myPackage.Items.Apples;
import myPackage.Items.SnakeBody;

//Panel class created from 'JPanel class' (JPanel imported)
public class Panel extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 800, HEIGHT = 800;        //dimensions of panel
    private Thread thread;        //timer that's enabled in start method
    private boolean running = false;    //for when game starts/stops
    
    private SnakeBody snakePart;    //creates snake object
    private ArrayList<SnakeBody> snakeArr;    //creates snake array with type of 'SnakeBody' as the type, it'll hold all the SnakePart Objects
    
    private Apples apple;    //creates apple object from Apples class
    private ArrayList<Apples> appleArr;    //creates appleArr as array with 'Apples' class as the type that'll store all the apple objects
    
    private Random r;
    
    private int xCoordinate = 20;    //starting x for head
    private int yCoordinate = 5;    //starting y for head
    private int size = 3;    //snake size (how many 'heads' it has
    
    private boolean right = true;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    
    private int updateCounter = 0;
    
    private Key key; //make key object from key class    
    
    
    
    //----------------------------------------------------------------------------------------------------------------------    
    
    
    //constructor for Panel class
    public Panel() {
        setFocusable(true);        //gives Panel ability to be focused / listen to key events
        key = new Key();        //creates new Key object, similar to thread
        addKeyListener(key);    //now key will listen for keys... and when it detects one it'll refer to the key object
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));        //(Dimension imported)
        
        snakeArr = new ArrayList<SnakeBody>(); //declaring array in constructors
        appleArr = new ArrayList<>();    //declaring array in constructors
        r = new Random();
    
        start(); //starts the game right away
    }

    
    //----------------------------------------------------------------------------------------------------------------------
    
    
    
    
    //for updating stuff every thread loop
    public void update() {
        
        //when array size is nothing, it adds it's first snake part object automatically and will draw it once current update loop is finished
        if(snakeArr.size() == 0) {
            snakePart = new SnakeBody(xCoordinate, yCoordinate, 10);    //create new snake part object
            snakeArr.add(snakePart);    //adds first object to snake array
        }
        
        //when apple array size is nothing, it'll create and add the first random apple to the array
        if(appleArr.size() == 0) {
            int xCoordinate = r.nextInt(79);
            int yCoordinate = r.nextInt(79);
            
            apple = new Apples(xCoordinate, yCoordinate, 10);
            appleArr.add(apple);
        }
        
        //when snake lands apple, apple will disapear and size of snake increases
        for(int i = 0; i < appleArr.size(); i++) {
            if(xCoordinate == appleArr.get(i).getxCoordinate() && yCoordinate == appleArr.get(i).getyCoordinate()) {
                size++;
                appleArr.remove(i);
                i--;
            }
        }
        
        //hit own body
        for(int i = 0; i < snakeArr.size(); i++) {
            if(xCoordinate == snakeArr.get(i).getxCoordinate() && yCoordinate == snakeArr.get(i).getyCoordinate()) {
                if(i != snakeArr.size()-1) {
                    stop();
                }
            }
        }
        
        if(xCoordinate < 0 || xCoordinate > 79 || yCoordinate < 0 || yCoordinate > 79) {
            stop();
        }
        
        updateCounter++; //we do this to waste the computers time, it will wait 250000 occurences of this loop with nothing happening until the next block gets added
        if(updateCounter > 250000){
            if(right)xCoordinate++;    //moves to right by 10 (since xCoordinate and yCoordinate get multiplied by 10 when drawing square)
            if(left) xCoordinate--;    //moves to left by 10
            if(up)yCoordinate--; //moves up by 10
            if(down) yCoordinate++;    //moves down by 10
            
            updateCounter = 0;
            snakePart = new SnakeBody(xCoordinate, yCoordinate, 10);    //create new snake part object
            snakeArr.add(snakePart);    //adds new snake part to the array that has a new location (ex. now the snake has 2 blocks -> runs 250k times w/ nothing happening -> now it has 3 blocks)
            
            //need to make sure that array stays within current size (until it eats the apple of course)
            if(snakeArr.size() > size) {
                snakeArr.remove(0);    //removes the first block when the new one is added (so it stays within the size & looks like it is moving)
            }
        }
    }
    
    //to draw on panel
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);    //clears all heads every thread loop
        
        //background color
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        //draw all snake parts currently in the array
        for(int i = 0; i < snakeArr.size(); i++) {
            snakeArr.get(i).drawSnakePart(g);
        }
        
        for(int i = 0; i < appleArr.size(); i++) {
            appleArr.get(i).drawApple(g);
        }
    }
        
    //to start game
    public void start() {
        running = true;
        thread = new Thread(this, "Game Loop");    // 'this' refers to the class
        thread.start(); //essentially runs the 'run' method
    }
    
    //what happens when game stops
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    //this is the thread that'll keep looping...
    public void run() {
        while(running) {
            update();
            repaint();    //refers to the 'paint' method
        }
    }        

    //key movements
    private class Key implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_RIGHT && !left) {
                up = false;
                down = false;
                right = true;
            }
            if (key == KeyEvent.VK_LEFT && !right) {
                up = false;
                down = false;
                left = true;
            }
            if (key == KeyEvent.VK_UP && !down) {
                left = false;
                right = false;
                up = true;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                left = false;
                down = true;
            }        
        }
    
        @Override
        public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub
            
        }
        @Override
        public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub    
        }

    }


}