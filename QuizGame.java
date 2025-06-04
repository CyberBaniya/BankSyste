import java.util.Scanner;

class Question {
    String question;
    String[] options;
    char correctOption;

    public Question(String question, String[] options, char correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean askQuestion(Scanner sc) {
        System.out.println("\n" + question);
        char optionLabel = 'A';
        for (String option : options) {
            System.out.println(optionLabel + ". " + option);
            optionLabel++;
        }

        System.out.print(" Your answer (A/B/C/D): ");
        char userAnswer = Character.toUpperCase(sc.next().charAt(0));
        return userAnswer == correctOption;
    }
}

public class QuizGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Question[] questions = {
            new Question("What is the capital of India?",
                    new String[]{"Delhi", "Mumbai", "Chennai", "Kolkata"}, 'A'),

            new Question("Which company developed Java?",
                    new String[]{"Oracle", "Sun Microsystems", "IBM", "Google"}, 'B'),

            new Question("Which data structure uses FIFO?",
                    new String[]{"Stack", "Queue", "Array", "Tree"}, 'B'),

            new Question("Who is the founder of Facebook?",
                    new String[]{"Elon Musk", "Bill Gates", "Mark Zuckerberg", "Steve Jobs"}, 'C'),

            new Question("Which keyword is used to inherit a class in Java?",
                    new String[]{"this", "super", "extends", "implements"}, 'C')
        };

        int score = 0;
        System.out.println("🎮 Welcome to the Quiz Game!");
        System.out.println("You will be asked " + questions.length + " questions.");

        for (Question q : questions) {
            boolean isCorrect = q.askQuestion(sc);
            if (isCorrect) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println(" Wrong Answer.");
            }
        }

        System.out.println("\n🏁 Quiz Finished!");
        System.out.println("Your Score: " + score + "/" + questions.length);

        if (score == questions.length) {
            System.out.println("Excellent! You got all answers correct!");
        } else if (score >= questions.length / 2) {
            System.out.println(" Good job!");
        } else {
            System.out.println(" Better luck next time!");
        }

        sc.close();
    }
}
