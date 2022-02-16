/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author Galina and Bruce
 */
public class Ship 
{
    
    ShipsName name;
    Orientation direction;
    int size, hitCount, coordinateX, coordinateY;
    boolean sunk;
    char symbol;
    
    public Ship()
    {
        name = ShipsName.DESTROYER;
        direction = Orientation.horizontal;
        size = hitCount= coordinateX = coordinateY = 0;
        sunk = false;
        symbol = '^';
    }
    
    public Ship(ShipsName n, int s, char sym)
    {
        name = n;
        size = s;
        symbol = sym;
        //direction = o;
    }
    
    public void setDirection(Orientation direc)
    {
        direction = direc;
    }
    
    public Orientation getDirection()
    {
        return direction;
    }
    public void setShipsName(ShipsName n)
    {
        name = n;
    }
    
    public ShipsName getShipsName()
    {
        return name;
    }
    
     public void setSize(int s)
    {
        size = s;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public void setSunk(boolean sink)
    {
        sunk = sink;
    }
    
    public boolean getSunk()
    {
        return sunk;
    }
    
    public void setSymbol(char sym)
    {
        symbol = sym;
    }
    
    public char getSymbol()
    {
        return symbol;
    }
    
    public boolean sunkOrNot()
    {
        if (hitCount == size)
        {
            sunk = true;
        }
        else
        {
            sunk = false;
        }
            return sunk;
    }
    
    public void setCoordinates(Orientation ori, int x, int y)
    {
        direction = ori;
        coordinateX = x;
        coordinateY = y;
    }
    
    public int getXCoordinate()
    {
        return coordinateX;
    }
    
    public int getYCoordinate()
    {
        return coordinateY;
    }
    
    public String toString()
    {
        String out ="";
        out += "Name of the ship: " + name +"\nSize: " + size +"\nOrientation: "
                + direction;
        return out;
    }
            
    
}
