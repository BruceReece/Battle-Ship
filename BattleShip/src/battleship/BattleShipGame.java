/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Galina
 */
public class BattleShipGame 

{
     int hits, misses,turns,findX,findY;
     Winner winner;
     Player player, computer;
     String answer;
     Scanner cin;
     
         
     
     
     
    public BattleShipGame()
    {
       
       
       player = new Player();
       computer = new Player();
       winner = winner.Computer;
       turns =findX=findY= 0;
       computer.setName("Computer");
       cin = new Scanner(System.in);
     
    }
 
    public void instructions()
    {
       
        System.out.println("Welcome to the Battle Ship game. You will be"
                + "\nplaying against the computer. You can either manually"
                + "\nplace your ships on the board, or have them be placed"
                + "\nrandomly. You will be taking turns, you can pick where"
                + "\nto shoot by selecting one of the squares on the grid"
                + "\nby identifying a row and a column. If you hit a ship"
                + "\nof the opponent an H will be displayed. In order to sink"
                + "\na ship you need to score a hit on each of the squares"
                + "\noccupied by the ship. When this happens, the squares will"
                + "\be marked with an S. Your goal is to sink all the ships"
                + "\nof the computer and you win the game! Good luck!"
                + "\n\nPress Y if you want to play, and any other key to exit > ");
    }
    
    public void Game()
    {
        String name;
        
        
        do 
        {
        instructions();
        answer = cin.nextLine();
        while (answer.equalsIgnoreCase("y"))
        {
            
            if(turns == 0)
            {
                System.out.println("Please enter your name > ");
                name = cin.nextLine();
                player.setName(name);
                player.arrange(); 
                computer.arrange();
                System.out.println(computer.board.compBoard());
                
                
            }
            
            playGame();
            reportGameStatus();
            
            
        }
        //report();
    }while (answer.equalsIgnoreCase("y"));
  }
    
    public void playGame()
    {
        if(player.board.stillHitt() == false)
        {
        int col, row;
        System.out.println("Please enter a row to attack > ");
        row = cin.nextInt();
        System.out.println("Please enter a column to attack > ");
        col = cin.nextInt();
       // player.board.checkForHit(row, col);
//        if(computer.board.checkForHit(row, col) == false)
//        {
//            System.out.println("You missed!");
//            player.setMiss(player.getMiss()+1);
//        }
//        else
//        {
//            System.out.println("You hit a ship!");
//            player.setHit(player.getHit()+1);
//        }
        //System.out.println(player.board.compBoard());
        
        
        //compPos();
            player.board.checkForHit(row, col);
            findX = row;
            findY = col;
        }
        else
        {
          player.board.tryMoves(findX, findY);
          
          if (player.board.getSymbol(findX,findY) == 'H')
          {
              computer.setHit(player.board.getHitCount());
          }
          else
              computer.setMiss(player.board.getMissCount());
        }
       System.out.println(player.board);
    }
    
    
    public void reportGameStatus()
            
    {
        Scanner cin2 = new Scanner(System.in);
        turns++;
        
         if (player.getHit() == 17 || computer.getHit() == 17)
            {
                report();
            }         
         else 
         {
        System.out.println("Currently player has " + player.getMiss()
                + " misses and " + player.getHit() + " hits.\nComputer"
                        + " has " + computer.getMiss() + " misses "
                                + " and " + computer.getHit() + " hits."
                                    + "\nWould you like to keep playing ? "
                                    + "Press Y for yes and any other key"
                                    + " to exit > ");
        answer = cin2.nextLine();
        if (!answer.equalsIgnoreCase("y"))
        {
            report();
        }
         }
    }
    public void report()
    {
        turns = 0;
        Scanner cin2 = new Scanner(System.in);
        
        if (player.getHit() == 17)
        {
            winner = winner.Player;
            System.out.println("Player has: " + player.getMiss() + " and "
                + player.getHit() + " hits. The computer has " + 
                computer.getMiss() + " misses and " + computer.getHit() 
        + " hits. The winner of the game is " + player.getName()
            +"\nWould you like to play another game ? Press y for yes and"
                    + " any ther key t exit > ");
        }
        else 
        {
            winner = winner.Computer;
            System.out.println("Player has: " + player.getMiss() + " and "
                + player.getHit() + " hits. The computer has " + 
                computer.getMiss() + " misses and " + computer.getHit() 
        + " hits.\nThe winner of the game is " + winner
            + "\n\nWould you like to play another game ? Press y for yes and"
                    + " any ther key to exit > ");
            
        }
        answer = cin2.nextLine();
        
    }
    
    
    public void compPos()
    {
        System.out.println("Now it's the computer's turn > ");
        Random r = new Random();
        Random c = new Random();
        int row = r.nextInt(10) + 1;
        int column = c.nextInt(10) + 1;
        
        findX = row;
        findY = column;
        System.out.println("The random choice for row is > " +row);
        System.out.println("The randm choice for column is > " +column);
        
        player.board.checkForHit(row, column);
        
        
        if(player.board.checkForHit(row, column) == false)
        {
            System.out.println("The computer missed!");
            computer.setMiss(player.board.getHitCount());
        }
        else
        {
            System.out.println("The computer hit a ship!");
            computer.setHit(player.board.getMissCount());
        }
        //System.out.println(player.board);
    }
    
    
    public void sinkShip(int x, int y)
    {
        Ship temp = new Ship();
        
        if (computer.board.getSymbol(x, y) == 'A')
        {
            //computer.
        }
    }
}
