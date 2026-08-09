package Application;

import java.util.Scanner;

public class Hangman {

    private static final int MAX_TRIES = 10;
    private final RandomWord word = new RandomWord();
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private int triesRemaining = MAX_TRIES;
    private char lastGuess;
    private String guessedLetters = "";

    public void run() {

        do {
            displayWord();

            if (getUserInput()) {
                checkUserInput();
            }

        } while (running);
    }

    private void checkUserInput() {

        boolean isCorrect = word.addGuess(lastGuess);

        if (isCorrect) {

            if (word.isComplete()) {
                System.out.println("You have won!");
                System.out.println("The word was: " + word.getChosenWord());
                running = false;
            }

        } else {

            triesRemaining--;

            if (triesRemaining == 0) {
                System.out.println("You have lost!");
                System.out.println("The word was: " + word.getChosenWord());
                running = false;
            }
        }
    }

    private boolean getUserInput() {

        System.out.print("Enter your guess: ");
        String guess = scanner.nextLine().trim();

        // Empty input
        if (guess.length() == 0) {
            System.out.println("Please enter a single character.");
            loseTry();
            return false;
        }

        // More than one character
        if (guess.length() > 1) {
            System.out.println("Please enter a single character.");
            loseTry();
            return false;
        }

        lastGuess = Character.toLowerCase(guess.charAt(0));

        // Not an alphabet
        if (!Character.isLetter(lastGuess)) {
            System.out.println("Please enter an alphabetical character.");
            loseTry();
            return false;
        }

        // Already guessed
        if (guessedLetters.indexOf(lastGuess) != -1) {
            System.out.println("You already guessed '" + lastGuess + "'. Please try a different letter.");
            loseTry();
            return false;
        }

        // Save the guess
        guessedLetters += lastGuess;

        return true;
    }

    private void loseTry() {

        triesRemaining--;

        if (triesRemaining == 0) {
            System.out.println("You have lost!");
            System.out.println("The word was: " + word.getChosenWord());
            running = false;
        }
    }

    void displayWord() {
        System.out.println("\nTries remaining: " + triesRemaining);
        drawHangman();
        System.out.println("Guessed letters: " + guessedLetters);
        System.out.println(word);
    }

    private void drawHangman() {

        switch (triesRemaining) {

            case 10:
                System.out.println("""
                         +---+
                         |   |
                             |
                             |
                             |
                             |
                        =========
                        """);
                break;

            case 9:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                             |
                             |
                             |
                        =========
                        """);
                break;

            case 8:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                         |   |
                             |
                             |
                        =========
                        """);
                break;

            case 7:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                        /|   |
                             |
                             |
                        =========
                        """);
                break;

            case 6:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                        /|\\  |
                             |
                             |
                        =========
                        """);
                break;

            case 5:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                        /|\\  |
                        /    |
                             |
                        =========
                        """);
                break;

            case 4:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                        /|\\  |
                        / \\  |
                             |
                        =========
                        """);
                break;

            case 3:
                System.out.println("""
                         +---+
                         |   |
                        \\O   |
                        /|\\  |
                        / \\  |
                             |
                        =========
                        """);
                break;

            case 2:
                System.out.println("""
                         +---+
                         |   |
                        \\O/  |
                        /|\\  |
                        / \\  |
                             |
                        =========
                        """);
                break;

            case 1:
                System.out.println("""
                         +---+
                         |   |
                        \\O/  |
                        /|\\  |
                        / \\  |
                        DEAD |
                        =========
                        """);
                break;

            case 0:
                System.out.println("""
                         +---+
                         |   |
                        \\O/  |
                        /|\\  |
                        / \\  |
                        DEAD |
                        =========
                        """);
                break;
        }
    }

    public void close() {
        scanner.close();
    }
}