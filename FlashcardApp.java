package github;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

class Flashcard {
    private String question;
    private String answer;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Question: " + question + "\nAnswer: " + answer;
    }
}

class FlashcardSet {
    private ArrayList<Flashcard> flashcards;

    public FlashcardSet() {
        this.flashcards = new ArrayList<>();
    }

    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    public Flashcard getFlashcard(int index) {
        return flashcards.get(index);
    }

    public int size() {
        return flashcards.size();
    }

    public void shuffle() {
        Collections.shuffle(flashcards);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Flashcard flashcard : flashcards) {
            sb.append(flashcard.toString()).append("\n");
        }
        return sb.toString();
    }
}

public class FlashcardApp {
    private static FlashcardSet flashcardSet = new FlashcardSet();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Main Menu:");
            System.out.println("1. Create Flashcards");
            System.out.println("2. View Flashcards");
            System.out.println("3. Play");
            System.out.println("4. Debug: Create Flashcards Automatically");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createFlashcards();
                    break;
                case 2:
                    viewFlashcards();
                    break;
                case 3:
                    playFlashcards();
                    break;
                case 4:
                    debugCreateFlashcards();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createFlashcards() {
        for (int i = 0; i < 10; i++) {
            System.out.println("What question goes on this flashcard?");
            String question = scanner.nextLine();
            System.out.println("What is the answer to this flashcard?");
            String answer = scanner.nextLine();
            flashcardSet.addFlashcard(new Flashcard(question, answer));
        }
        System.out.println("Flashcards created successfully!");
    }

    private static void viewFlashcards() {
        System.out.println("Viewing Flashcards:");
        System.out.println(flashcardSet);
    }

    private static void playFlashcards() {
        flashcardSet.shuffle();
        ArrayList<Flashcard> flashcards = flashcardSet.getFlashcards();
        boolean[] shown = new boolean[flashcards.size()];
        Random random = new Random();

        for (int i = 0; i < flashcards.size(); i++) {
            int index;
            do {
                index = random.nextInt(flashcards.size());
            } while (shown[index]);
            shown[index] = true;

            Flashcard flashcard = flashcards.get(index);
            System.out.println("Question: " + flashcard.getQuestion());
            System.out.println("Type 'flip' to see the answer or 'next' to move on.");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("flip")) {
                System.out.println("Answer: " + flashcard.getAnswer());
            }

            System.out.println("Type 'next' to continue.");
            scanner.nextLine(); // Wait for user to type 'next'
        }
        System.out.println("End of flashcards.");
    }

    private static void debugCreateFlashcards() {
        for (int i = 1; i <= 10; i++) {
            flashcardSet.addFlashcard(new Flashcard("Question " + i, "Answer " + i));
        }
        System.out.println("Debug flashcards created successfully!");
    }
}