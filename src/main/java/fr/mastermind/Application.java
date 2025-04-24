package fr.mastermind;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    static boolean codeDefined = false;
    static List<String> colorSequence = new ArrayList<>();
    static List<String> colorTry = new ArrayList<>();
    static int nbTry = 12;

    public static void main(String[] args) {
        int screenWidth = 1000;
        int screenHeight = 500;

        JFrame frame = new JFrame();

        frame.setSize(screenWidth, screenHeight);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 6, 10, 10));

        for (int i = 0; i < 6; i++) {
            panel.add(new JLabel());
        }

        JButton rouge = new JButton();
        rouge.setBackground(Color.RED);

        JButton vert = new JButton();
        vert.setBackground(Color.GREEN);


        JButton bleu = new JButton();
        bleu.setBackground(Color.BLUE);


        JButton jaune = new JButton();
        jaune.setBackground(Color.YELLOW);

        panel.add(new JLabel());
        panel.add(rouge);
        panel.add(vert);
        panel.add(bleu);
        panel.add(jaune);
        panel.add(new JLabel());

        for (int i = 0; i < 6; i++) {
            panel.add(new JLabel());
        }


        System.out.println("Entrez votre code à 4 chiffres que vous voulez faire deviner:");
        rouge.addActionListener(e -> handleColorClick("rouge"));
        vert.addActionListener(e -> handleColorClick("vert"));
        bleu.addActionListener(e -> handleColorClick("bleu"));
        jaune.addActionListener(e -> handleColorClick("jaune"));


        // Ajouter le panneau à la fenêtre
        frame.add(panel);
        frame.setVisible(true);

        System.out.println("Entrez votre code à 4 couleurs que vous voulez faire deviner :");

    }



    public static void gameLogic(List<String> colorToGuess, List<String> colorTry){
        int equal = 0;
        int contain = 0;


        boolean[] keyToFind = new boolean[4];
        boolean[] keyGuess = new boolean[4];


        for (int i = 0; i < colorToGuess.size(); i++) {
            if (colorTry.get(i) == colorToGuess.get(i)) {
                equal++;
                keyToFind[i] = true;
                keyGuess[i] = true;
            }
        }

        for (int i = 0; i < colorToGuess.size(); i++) {
            if (keyGuess[i]) continue;
            for (int j = 0; j < colorTry.size(); j++) {
                if (!keyToFind[j] && colorTry.get(i) == colorToGuess.get(j)) {
                    contain++;
                    keyToFind[j] = true;
                    break;
                }
            }
        }


        if (equal != 4) {
            nbTry--;
            System.out.println("il y a " + equal + " couleurs au bon endroit et " + contain +
                    " couleurs qui sont bon mais au mauvaise endroit. \nIl vous restes " + nbTry + " chances.");
        } else {
            System.out.println("Bravo");
            resetGame();

        } if (nbTry == 0) {
            System.out.println("Dommage ! Vous avez perdu. Le code était : " + colorToGuess);
            resetGame();
        }
    }

    public static void handleColorClick(String color) {
        if (!codeDefined) {
            colorSequence.add(color);
            System.out.println("Séquence secrète : " + colorSequence);
            if (colorSequence.size() == 4) {
                codeDefined = true;
                System.out.println("Code défini ! Essayez de le deviner. Il vous reste " + nbTry + " tentatives.");
            }
        } else {
            colorTry.add(color);
            System.out.println("Essai actuel : " + colorTry);
            if (colorTry.size() == 4) {
                gameLogic(colorSequence, colorTry);
                colorTry.clear();
            }
        }
    }

    public static void resetGame() {
        colorSequence.clear();
        colorTry.clear();
        nbTry = 12;
        codeDefined = false;
        System.out.println("\nNouvelle partie ! Entrez un nouveau code à 4 couleurs :");
    }


}

