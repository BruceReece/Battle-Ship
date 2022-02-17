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
 * @author Galina and Bruce
 */
public class BattleShipGame {

    private int turns;
    private Winner winner;
    private final Player player, computer;
    private String answer;
    private Scanner cin;
    private Ship current;
    private int num, sizeShip, rowCoordinate, colCoordinate, findX, findY,
            index, checkSide1, checkSide2, checkSide3, checkSide4, bestMove;
    private Orientation direct;
    private char symbol;
    private boolean stillHit;

    public BattleShipGame() {

        player = new Player();
        computer = new Player();
        winner = winner.Computer;
        turns = 0;
        computer.setName("Computer");
        cin = new Scanner(System.in);
        findX = findY = 0;
        stillHit = false;
        index = 1;
        bestMove = 0;

    }

    public void instructions() {

        System.out.println("\nWelcome to the Battle Ship game. You will be"
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

    public void Game() {
        String name;
        instructions();
        answer = cin.nextLine();

        while (answer.equalsIgnoreCase("y")) {

            if (turns == 0) {
                System.out.println("Please enter your name > ");
                name = cin.nextLine();
                player.setName(name);
                player.arrange();
                computer.arrange();
                System.out.println("this is the biard\n" + computer.board);
                System.out.println(computer.board.compBoard());

            }

            playGame();
            currentGameStatus();

        }

    }

    public void playGame() {
        int col, row;
        System.out.println("Please enter a row to attack > ");

        row = cin.nextInt();
        while (row != 1 && row != 2 && row != 3 && row != 4 && row != 5 && row != 6
                && row != 7 && row != 8 && row != 9 && row != 10) {
            System.out.println("Please enter a row between 1 and 10 > ");
            row = cin.nextInt();
        }
        System.out.println("Please enter a column to attack > ");

        col = cin.nextInt();

        while (col != 1 && col != 2 && col != 3 && col != 4 && col != 5 && col != 6
                && col != 7 && col != 8 && col != 9 && col != 10) {
            System.out.println("Please enter a row between 1 and 10 > ");
            col = cin.nextInt();
        }

        symbol = computer.board.getSymbol(row, col);
        if (computer.board.checkForHit(row, col) == false) {
            System.out.println("You missed!");
            player.setMiss(player.getMiss() + 1);

        } else {
            System.out.println("You hit a ship!");
            whichShip(symbol, computer);
            player.setHit(player.getHit() + 1);
            if (current.sunkOrNot() == true) {
                computer.board.sinkShip(rowCoordinate, colCoordinate, sizeShip, direct);
                System.out.println("You sank a " + current.getShipsName());
            }
        }
        System.out.println(computer.board.compBoard());

        if (stillHit == false) {
            compPos();
        } else {
            tryMoves(findX, findY);
        }
        System.out.println(player.board);

    }

    public void currentGameStatus() {
        Scanner cin2 = new Scanner(System.in);
        turns++;

        if (player.getHit() == 17 || computer.getHit() == 17) {
            report();
        } else {
            System.out.println("Currently player has " + player.getMiss()
                    + " misses and " + player.getHit() + " hits.\nComputer"
                    + " has " + computer.getMiss() + " misses "
                    + " and " + computer.getHit() + " hits."
                    + "\nWould you like to keep playing ? "
                    + "Press Y for yes and any other key"
                    + " to exit > ");
            answer = cin2.nextLine();
            if (!answer.equalsIgnoreCase("y")) {
                report();
            }
        }
    }

    public void report() {
        turns = 0;

        System.out.println("\nGAME OVER!"
                + "\nPlayer has " + player.getMiss() + " misses "
                + "and "
                + player.getHit() + " hits.\nThe computer has "
                + computer.getMiss() + " misses and " + computer.getHit()
                + " hits.");
        if (player.getHit() == 17) {
            winner = winner.Player;
            System.out.println("\nThe winner of the game is " + player.getName());

        } else {
            winner = winner.Computer;
            System.out.println("\nThe winner of the game is " + winner);
            System.out.println("This is what the computer board looked like: ");
            System.out.println("");
            System.out.println(computer.board);

        }
        System.out.println("\nWould you like to play another game ? Press y for yes and"
                + " any ther key to exit > ");
        answer = cin.nextLine();

    }

    public void compPos() {
        System.out.println("Now it's the computer's turn > ");
        Random r = new Random();
        Random c = new Random();
        int row = r.nextInt(10) + 1;
        int column = c.nextInt(10) + 1;
        findX = row;
        findY = column;
        System.out.println("The random choice for row is > " + row);
        System.out.println("The random choice for column is > " + column);
        symbol = player.board.getSymbol(row, column);
        if (player.board.checkForHit(row, column) == false) {
            System.out.println("The computer missed!");
            computer.setMiss(computer.getMiss() + 1);
        } else {
            stillHit = true;
            System.out.println("The computer hit a ship!");
            whichShip(symbol, player);
            computer.setHit(computer.getHit() + 1);
            if (current.sunkOrNot() == true) {
                player.board.sinkShip(rowCoordinate, colCoordinate, sizeShip, direct);

            }
        }
    }

    public void whichShip(char c, Player p) {
        if (c == 'A') {
            num = 4;

        } else if (c == 'C') {
            num = 3;

        } else if (c == 'F') {
            num = 2;

        } else if (c == 'B') {
            num = 1;
        } else {
            num = 0;

        }
        current = p.ships[num];
        sizeShip = current.getSize();
        current.hitCount++;
        direct = current.getDirection();
        rowCoordinate = current.getXCoordinate();
        colCoordinate = current.getYCoordinate();

    }

    public void tryMoves(int x, int y) {
        char whatsThere;
        switch (bestMove) {

            case 0:
                checkSide1 = x - index;
                if (checkSide1 < 1 || checkSide1 > 10) {
                    bestMove = 2;
                    index = 1;
                    tryMoves(x, y);

                } else {
                    whatsThere = player.board.getSymbol(checkSide1, y);
                    if (whatsThere == 'S' || whatsThere == 'M') {
                        bestMove = 2;
                        index = 1;
                        tryMoves(x, y);

                    }

                    if (player.board.checkForHit(checkSide1, y) == true) {
                        symbol = whatsThere;
                        whichShip(symbol, player);
                        index++;
                        computer.setHit(computer.getHit() + 1);
                        if (current.sunkOrNot() == true) {
                            stillHit = false;
                            player.board.sinkShip(rowCoordinate, colCoordinate,
                                    sizeShip, direct);
                            System.out.println("The computer sank a " + current.getShipsName());
                        }
                    } else {
                        bestMove = 2;
                        index = 1;
                    }
                }
                break;

            case 1:
                checkSide2 = y - index;
                if (checkSide2 < 1 || checkSide2 > 10) {
                    bestMove = 0;
                    stillHit = false;
                    index = 1;
                } else {
                    whatsThere = player.board.getSymbol(x, checkSide2);
                    if (whatsThere == 'S' || whatsThere == 'M' || whatsThere == 'H') {
                        bestMove = 0;
                        stillHit = false;
                        index = 1;
                    }

                    if (player.board.checkForHit(x, checkSide2) == true) {
                        symbol = whatsThere;
                        whichShip(symbol, player);
                        index++;
                        computer.setHit(computer.getHit() + 1);
                        if (current.sunkOrNot() == true) {
                            stillHit = false;
                            player.board.sinkShip(rowCoordinate, colCoordinate,
                                    sizeShip, direct);
                            System.out.println("The computer sank a " + current.getShipsName());

                        }
                    } else {
                        bestMove = 0;
                        index = 1;
                        stillHit = false;
                    }
                }
                break;

            case 2:
                checkSide3 = x + index;
                if (checkSide3 < 1 || checkSide3 > 10) {
                    bestMove = 3;
                    index = 1;
                    tryMoves(x, y);
                } else {
                    whatsThere = player.board.getSymbol(checkSide3, y);
                    if (whatsThere == 'S' || whatsThere == 'M') {
                        bestMove = 3;
                        tryMoves(x, y);
                        index = 1;
                    }

                    if (player.board.checkForHit(checkSide3, y) == true) {
                        symbol = whatsThere;
                        whichShip(symbol, player);
                        index++;
                        computer.setHit(computer.getHit() + 1);
                        if (current.sunkOrNot() == true) {
                            stillHit = false;
                            player.board.sinkShip(rowCoordinate,
                                    colCoordinate, sizeShip, direct);
                            System.out.println("The computer sank a " + current.getShipsName());
                        }
                    } else {
                        bestMove = 3;
                        index = 1;
                    }
                }
                break;
            case 3:
                checkSide4 = y + index;
                if (checkSide4 < 1 || checkSide4 > 10) {
                    bestMove = 1;
                    index = 1;
                    tryMoves(x, y);
                } else {
                    whatsThere = player.board.getSymbol(x, checkSide4);
                    if (whatsThere == 'S' || whatsThere == 'M') {
                        bestMove = 1;
                        index = 1;
                        tryMoves(x, y);
                    }

                    if (player.board.checkForHit(x, checkSide4) == true) {
                        symbol = whatsThere;
                        whichShip(symbol, player);
                        index++;
                        computer.setHit(computer.getHit() + 1);
                        if (current.sunkOrNot() == true) {
                            stillHit = false;
                            player.board.sinkShip(rowCoordinate,
                                    colCoordinate, sizeShip, direct);
                            System.out.println("The computer sank a " + current.getShipsName());
                        }
                    } else {
                        bestMove = 1;
                        index = 1;
                    }
                }
                break;
        }
    }

}
