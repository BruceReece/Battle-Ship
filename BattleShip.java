/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Scanner;

/**
 *
 * @author Galina
 */
public class BattleShip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String resetGame;

        do {
            BattleShipGame game = new BattleShipGame();
            game.Game();
            resetGame = cin.nextLine();
        } while (resetGame.equalsIgnoreCase("y"));
        System.out.println("Bye! Have a nice day!");

    }

}
