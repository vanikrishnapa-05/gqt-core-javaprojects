package Ecommerce;

import javax.swing.*;
import java.awt.*;

public class KBCwingGame extends JFrame {

    private int prize = 0;
    private int currentQuestion = 0;

    private boolean audiencePollUsed = false;
    private boolean fiftyFiftyUsed = false;

    static class Question {
        String questionText;
        String[] options;
        char correctAnswer;
        String audiencePoll;
        String fiftyFifty;

        Question(String q, String[] opts, char correct, String poll, String fifty) {
            questionText = q;
            options = opts;
            correctAnswer = correct;
            audiencePoll = poll;
            fiftyFifty = fifty;
        }
    }

    private Question[] questions = {
            new Question("Which country hosted the 2024 Summer Olympics?",
                    new String[]{"Japan", "France", "USA", "China"},
                    'B', "[A] 15% [B] 70% [C] 10% [D] 5%", "[B] France and [C] USA"),

            new Question("What is the largest planet in our solar system?",
                    new String[]{"Earth", "Jupiter", "Saturn", "Neptune"},
                    'B', "[A] 10% [B] 80% [C] 5% [D] 5%", "[B] Jupiter and [C] Saturn"),

            new Question("Who is known as the Father of Computers?",
                    new String[]{"Charles Babbage", "Alan Turing", "Bill Gates", "Steve Jobs"},
                    'A', "[A] 65% [B] 25% [C] 5% [D] 5%", "[A] Charles Babbage and [B] Alan Turing"),

            new Question("Which gas do plants absorb during photosynthesis?",
                    new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"},
                    'B', "[A] 5% [B] 85% [C] 5% [D] 5%", "[B] Carbon Dioxide and [C] Nitrogen"),

            new Question("Which is the national animal of India?",
                    new String[]{"Lion", "Tiger", "Elephant", "Leopard"},
                    'B', "[A] 10% [B] 70% [C] 15% [D] 5%", "[B] Tiger and [A] Lion"),

            new Question("What is the capital of Australia?",
                    new String[]{"Sydney", "Melbourne", "Canberra", "Perth"},
                    'C', "[A] 15% [B] 25% [C] 50% [D] 10%", "[C] Canberra and [B] Melbourne"),

            new Question("Who discovered gravity?",
                    new String[]{"Albert Einstein", "Isaac Newton", "Galileo", "Nikola Tesla"},
                    'B', "[A] 10% [B] 70% [C] 15% [D] 5%", "[B] Newton and [C] Galileo"),

            new Question("Which planet is called the Red Planet?",
                    new String[]{"Earth", "Mars", "Jupiter", "Venus"},
                    'B', "[A] 5% [B] 85% [C] 5% [D] 5%", "[B] Mars and [C] Jupiter"),

            new Question("What is the smallest prime number?",
                    new String[]{"0", "1", "2", "3"},
                    'C', "[A] 5% [B] 10% [C] 75% [D] 10%", "[C] 2 and [D] 3"),

            new Question("Which is the fastest land animal?",
                    new String[]{"Lion", "Horse", "Cheetah", "Tiger"},
                    'C', "[A] 5% [B] 10% [C] 80% [D] 5%", "[C] Cheetah and [A] Lion")
    };

    private int[] prizeMoney = {5000, 10000, 20000, 40000, 80000,
            160000, 320000, 640000, 1250000, 2500000};

    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JButton audiencePollBtn, fiftyFiftyBtn, quitBtn;
    private JLabel[] prizeLabels;

    public KBCwingGame() {

        setTitle("KBC Quiz Game");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionButtons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            int index = i;
            optionButtons[i].addActionListener(e -> checkAnswer((char) ('A' + index)));
            optionsPanel.add(optionButtons[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        JPanel lifelinePanel = new JPanel();
        audiencePollBtn = new JButton("Audience Poll");
        fiftyFiftyBtn = new JButton("50/50");
        quitBtn = new JButton("Quit");

        audiencePollBtn.addActionListener(e -> useAudiencePoll());
        fiftyFiftyBtn.addActionListener(e -> useFiftyFifty());
        quitBtn.addActionListener(e -> quitGame());

        lifelinePanel.add(audiencePollBtn);
        lifelinePanel.add(fiftyFiftyBtn);
        lifelinePanel.add(quitBtn);

        add(lifelinePanel, BorderLayout.SOUTH);

        JPanel prizePanel = new JPanel(new GridLayout(prizeMoney.length, 1));
        prizeLabels = new JLabel[prizeMoney.length];

        for (int i = 0; i < prizeMoney.length; i++) {
            prizeLabels[i] = new JLabel("₹" + prizeMoney[i]);
            prizePanel.add(prizeLabels[i]);
        }

        add(prizePanel, BorderLayout.EAST);

        loadQuestion();
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            Question q = questions[currentQuestion];
            questionLabel.setText(q.questionText);

            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText("[" + (char) ('A' + i) + "] " + q.options[i]);
                optionButtons[i].setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Congratulations! You won ₹" + prize);
            System.exit(0);
        }
    }

    private void checkAnswer(char answer) {
        Question q = questions[currentQuestion];

        if (answer == q.correctAnswer) {
            prize = prizeMoney[currentQuestion];
            JOptionPane.showMessageDialog(this, "Correct! You won ₹" + prize);
            currentQuestion++;
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Wrong! Correct answer: " + q.correctAnswer +
                            "\nYou leave with ₹" + prize);
            System.exit(0);
        }
    }

    private void useAudiencePoll() {
        if (!audiencePollUsed) {
            JOptionPane.showMessageDialog(this,
                    "Audience Poll: " + questions[currentQuestion].audiencePoll);
            audiencePollUsed = true;
            audiencePollBtn.setEnabled(false);
        }
    }

    private void useFiftyFifty() {
        if (!fiftyFiftyUsed) {
            fiftyFiftyUsed = true;
            fiftyFiftyBtn.setEnabled(false);

            int disabled = 0;
            for (int i = 0; i < 4; i++) {
                if ((char) ('A' + i) != questions[currentQuestion].correctAnswer && disabled < 2) {
                    optionButtons[i].setEnabled(false);
                    disabled++;
                }
            }
        }
    }

    private void quitGame() {
        JOptionPane.showMessageDialog(this,
                "You quit with ₹" + prize);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new KBCwingGame().setVisible(true));
    }
}