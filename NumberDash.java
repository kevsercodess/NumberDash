package com.mycompany.numberdash;

import java.util.Scanner;

/**
 * Main class - entry point for Number Dash.
 * Handles the welcome screen, main menu, and application loop.
 *
 * Role: UX Lead (Menu and User Interface)
 * Branch: feature/menu
 * @author Student
 * @version V1.1
 */
public class NumberDash {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Welcome screen
        System.out.println("=================================");
        System.out.println("     Welcome to NUMBER DASH!     ");
        System.out.println("=================================");
        System.out.println("Answer math questions to speed");
        System.out.println("up your car and beat the CPU!");
        System.out.println("Get ready to race with your math skills!");
        System.out.println("=================================\n");

        while (running) {
            // Main menu
            System.out.println("--- Main Menu ---");
            System.out.println("1. Start Race");
            System.out.println("2. How to Play");
            System.out.println("3. View High Score");
            System.out.println("4. Quit");
            System.out.print("Choose an option (1-4): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    RaceLogic race = new RaceLogic();
                    int finalScore = race.startRace();
                    ScoreBoard board = new ScoreBoard();
                    board.updateAndDisplay(finalScore);
                    break;
                case "2":
                    showHowToPlay();
                    break;
                case "3":
                    ScoreBoard sb = new ScoreBoard();
                    sb.showHighScore();
                    break;
                case "4":
                    System.out.println("\nThanks for playing Number Dash! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 4.\n");
            }
        }

        scanner.close();
    }

    /**
     * Displays the how-to-play instructions.
     */
    private static void showHowToPlay() {
        System.out.println("\n--- How to Play ---");
        System.out.println("* Answer math questions to move your car forward.");
        System.out.println("* Correct answer  --> You move 3 steps forward!");
        System.out.println("* Wrong answer    --> CPU moves 2 steps, you go back to start!");
        System.out.println("* First to reach 20 steps wins the race!");
        System.out.println("* Your score is how many questions you answered correctly.\n");
    }
}
