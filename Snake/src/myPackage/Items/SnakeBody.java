package myPackage.Items;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeBody {
    
    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;

    
    
    
    //----------------------------------------------------------------------------------------------------------------------

    
    //constructor for SnakeBody
    public SnakeBody(int xCoordinate, int yCoordinate, int blockSize) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        width = blockSize;
        height = blockSize;
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------

    
    
    
    public void update() {
        
    }
    

    //draws a snake part
    public void drawSnakePart(Graphics g) {
        //square
        g.setColor(Color.BLACK);
        g.fillRect(xCoordinate * width, yCoordinate * height, width, height);
        //border
        g.setColor(Color.YELLOW);
        g.fillRect(xCoordinate * width-1, yCoordinate * height-1, width-1, height-1);
    }
    
    
    public int getxCoordinate() {
        return xCoordinate;
    }
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public int getyCoordinate() {
        return yCoordinate;
    }
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}