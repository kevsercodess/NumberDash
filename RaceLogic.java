// RaceLogic handles the main game mechanics
// It generates math questions and controls player and CPU movement
package com.mycompany.numberdash;

import java.util.Random;
import java.util.Scanner;

/**
 * RaceLogic class - handles race mechanics, question generation, and track display.
 * Correct answer = player moves 3 steps forward.
 * Wrong answer   = CPU moves 2 steps forward, player resets to start.
 *
 * Role: Core Logic Lead
 * Branch: feature/race-logic
 * @author Student
 * @version V1.1
 */
public class RaceLogic {

    private static final int FINISH_LINE = 20;
    private static final int PLAYER_STEPS = 3;
    private static final int CPU_STEPS = 2;

    private Scanner scanner;
    private Random random;

    private int playerPosition;
    private int cpuPosition;
    private int correctAnswers;

    public RaceLogic() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.playerPosition = 0;
        this.cpuPosition = 0;
        this.correctAnswers = 0;
    }

    /**
     * Starts and runs the race until someone reaches the finish line.
     * @return number of correct answers (player score)
     */
    public int startRace() {
        System.out.println("\n============================");
        System.out.println("       RACE STARTING!       ");
        System.out.println("============================\n");

        int round = 1;

        while (playerPosition < FINISH_LINE && cpuPosition < FINISH_LINE) {
            System.out.println("--- Round " + round + " ---");
            displayTrack();

            // Generate a random math question
            int[] question = generateQuestion();
            int num1 = question[0];
            int num2 = question[1];
            int operator = question[2]; // 0 = addition, 1 = subtraction
            int correctAnswer = question[3];

            // Display the question
            String operatorSymbol = (operator == 0) ? "+" : "-";
            System.out.println("Question: What is " + num1 + " " + operatorSymbol + " " + num2 + "?");
            System.out.print("Your answer: ");

            String input = scanner.nextLine().trim();

            // Validate and check the answer
            try {
                int userAnswer = Integer.parseInt(input);
                if (userAnswer == correctAnswer) {
                    System.out.println("Correct! You move " + PLAYER_STEPS + " steps forward!\n");
                    playerPosition += PLAYER_STEPS;
                    if (playerPosition > FINISH_LINE) playerPosition = FINISH_LINE;
                    correctAnswers++;
                } else {
                    System.out.println("Wrong! The correct answer was " + correctAnswer + ".");
                    System.out.println("CPU moves " + CPU_STEPS + " steps and you go back to start!\n");
                    cpuPosition += CPU_STEPS;
                    if (cpuPosition > FINISH_LINE) cpuPosition = FINISH_LINE;
                    playerPosition = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number. CPU gets a free move!\n");
                cpuPosition += CPU_STEPS;
                if (cpuPosition > FINISH_LINE) cpuPosition = FINISH_LINE;
            }

            round++;
        }

        // Final track display
        displayTrack();

        // Announce winner
        if (playerPosition >= FINISH_LINE) {
            System.out.println("YOU WIN! Congratulations! Great math skills!");
        } else {
            System.out.println("CPU wins this time! Keep practising and try again!");
        }

        System.out.println();
        return correctAnswers;
    }

    /**
     * Generates a random addition or subtraction question suitable for ages 6-10.
     * Numbers are kept between 1 and 20. Subtraction always produces a positive result.
     * @return int array: [num1, num2, operator, correctAnswer]
     */
    private int[] generateQuestion() {
        int num1, num2, operator, answer;
        operator = random.nextInt(2); // 0 = add, 1 = subtract

        if (operator == 0) {
            // Addition: both numbers 1-10
            num1 = random.nextInt(10) + 1;
            num2 = random.nextInt(10) + 1;
            answer = num1 + num2;
        } else {
            // Subtraction: ensure result is always positive
            num1 = random.nextInt(10) + 10; // 10-19
            num2 = random.nextInt(10) + 1;  // 1-10
            answer = num1 - num2;
        }

        return new int[]{num1, num2, operator, answer};
    }

    /**
     * Displays the race track showing both player and CPU positions.
     */
    private void displayTrack() {
        System.out.println("============================");
        System.out.print("[YOU] ");
        printBar(playerPosition);
        System.out.println("  (" + playerPosition + "/" + FINISH_LINE + ")");

        System.out.print("[CPU] ");
        printBar(cpuPosition);
        System.out.println("  (" + cpuPosition + "/" + FINISH_LINE + ")");
        System.out.println("============================\n");
    }

    /**
     * Prints a visual progress bar for a given position.
     * @param position current position (0 to FINISH_LINE)
     */
    private void printBar(int position) {
        int barLength = 20;
        int filled = (position * barLength) / FINISH_LINE;

        System.out.print("|");
        for (int i = 0; i < barLength; i++) {
            if (i == filled && position < FINISH_LINE) {
                System.out.print(">");
            } else if (i < filled) {
                System.out.print("=");
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("|");
    }

  feat: implement race logic and math question system
}
