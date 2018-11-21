package myPackage.Items;

import java.awt.Color;
import java.awt.Graphics;

public class Apples {

    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;
    
    

    
    //----------------------------------------------------------------------------------------------------------------------

    
    //constructor for SnakeBody
    public Apples(int xCoordinate, int yCoordinate, int blockSize)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = blockSize;
        this.height = blockSize;
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------

    
    
    
    public void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xCoordinate * width, yCoordinate * height, width, height);
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