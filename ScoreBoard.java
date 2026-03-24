// ScoreBoard class manages the final score display
// It updates and shows the player's performance after the race

package com.mycompany.numberdash;

/**
 * ScoreBoard class - tracks, updates, and displays the player's score and high score.
 *
 * Role: Results Lead
 * Branch: feature/scoreboard
 * @author Student
 * @version V1.1
 */
public class ScoreBoard {

    // Static high score shared across all instances during the session
    private static int highScore = 0;
    private static int gamesPlayed = 0;

    /**
     * Updates the high score if beaten, then displays the results.
     * @param score number of correct answers in this race
     */
    public void updateAndDisplay(int score) {
        gamesPlayed++;

        System.out.println("==============================");
        System.out.println("         RACE RESULTS         ");
        System.out.println("==============================");
        System.out.println("Correct answers : " + score);
        System.out.println("Games played    : " + gamesPlayed);
        System.out.println("Rating          : " + getRating(score));

        if (score > highScore) {
            highScore = score;
            System.out.println("NEW HIGH SCORE! Well done!");
        } else {
            System.out.println("High score      : " + highScore);
        }

        System.out.println("==============================\n");
    }

    /**
     * Displays the current high score.
     */
    public void showHighScore() {
        System.out.println("\n--- High Score ---");
        if (highScore == 0) {
            System.out.println("No scores yet! Start a race to set your first high score.");
        } else {
            System.out.println("High score  : " + highScore + " correct answers");
            System.out.println("Games played: " + gamesPlayed);
        }
        System.out.println();
    }

    /**
     * Returns a star rating based on the number of correct answers.
     * @param score number of correct answers
     * @return rating string
     */
    public String getRating(int score) {
        if (score >= 10) return "*** Math Champion!";
        if (score >= 7)  return "**  Great Work!";
        if (score >= 4)  return "*   Keep Practising!";
        return               "    Try Again!";
    }

  feat: add scoreboard and high score tracking
}
