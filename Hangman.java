import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = {"java", "python", "hangman", "programming", "algorithm"};
        String word = words[(int) (Math.random() * words.length)];
        List<Character> guessedLetters = new ArrayList<>();
        int maxGuesses = 6;
        int wrongGuesses = 0;
        boolean hintUsed = false;

        while (true) {
            printWordState(word, guessedLetters);
            if (wrongGuesses >= maxGuesses) {
                System.out.println("You've been hanged! The word was: " + word);
                break;
            }
            if (isWordGuessed(word, guessedLetters)) {
                System.out.println("Congratulations! You've guessed the word: " + word);
                break;
            }
            System.out.print("Enter a letter (or type 'hint' for a hint): ");
            String input = scanner.next().toLowerCase();
            
            if (input.equals("hint")) {
                if (!hintUsed) {
                    char hint = getHint(word, guessedLetters);
                    System.out.println("Hint: Try the letter '" + hint + "'");
                    hintUsed = true;
                } else {
                    System.out.println("You've already used your hint!");
                }
                continue;
            }
            if(input.isEmpty()||(!Character.isLetter(input.charAt(0)))){
                System.out.println("Invalid input");
            }
            else{
            char guess = input.charAt(0);
            if (guessedLetters.contains(guess)) {
                System.out.println("You've already guessed that letter.");
                continue;
            }
            guessedLetters.add(guess);
            if (word.indexOf(guess) == -1) {
                wrongGuesses++;
                System.out.println("Wrong guess! You have " + (maxGuesses - wrongGuesses) + " guesses left.");
            }
            }
        }
        scanner.close();
    }

    private static void printWordState(String word, List<Character> guessedLetters) {
        for (char c : word.toCharArray()) {
            if (guessedLetters.contains(c)) {
                System.out.print(c + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private static boolean isWordGuessed(String word, List<Character> guessedLetters) {
        for (char c : word.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private static char getHint(String word, List<Character> guessedLetters) {
        List<Character> remainingLetters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                remainingLetters.add(c);
            }
        }
        Random random = new Random();
        return remainingLetters.get(random.nextInt(remainingLetters.size()));
    }
}
