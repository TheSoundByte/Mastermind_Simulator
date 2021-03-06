package com.example.cjrosas.mastermind_simulator;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class CodeBreaker implements Serializable {
    private static List<String> allCombinations = new ArrayList<String>();
    private static final String[] letters = new String[6];
    private static int numOfTurns = 10;
    private String player;
    private static String secretC;
    private static String[] secretCode = new String[4];
    private ArrayList<Integer> hashCodes;

    public CodeBreaker(String p) {
        player = p;
        Random obj = new Random();
        getAllCombinations();
        secretC = allCombinations.get(obj.nextInt(allCombinations.size()));
//        for (int i = 0; i < secretC.length(); i++) {
//            secretCode[i] = secretC.substring(i, i + 1);
//        }
        secretCode[0] = secretC.substring(0, 1);
        secretCode[1] = secretC.substring(1, 2);
        secretCode[2] = secretC.substring(2, 3);
        secretCode[3] = secretC.substring(3, 4);
    }

    public String getGuess(String g) {
        if (numOfTurns == 0) {
            return "The code was: ".concat(this.getSecretC());
        } else {
            if (allCombinations.contains(g)) {
                numOfTurns--;
                return isCorrect(g);
            } else {
                return "RETRY";
            }
        }
    }
    public void getAllCombinations() {
        letters[0] = "G";
        letters[1] = "R";
        letters[2] = "U";
        letters[3] = "Y";
        letters[4] = "W";
        letters[5] = "O";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    for (int l = 0; l < 6; l++) {
                        String possible = letters[i]+letters[j]+letters[k]+letters[l];
                        allCombinations.add(possible);
                    }
                }
            }
        }
    }

    public String isCorrect(String guess) {
        if (guess.equals(secretC)) {
            return winGame();
        } else {
            return checkCorrect(guess);
        }
    }

    public String checkCorrect(String g) {
        String pegs = "";
        hashCodes = new ArrayList<>();
        String[] guess = new String[4];
        for (int i = 0; i < g.length(); i++) {
            guess[i] = g.substring(i, i + 1);
        }
        for (int i = 0; i < guess.length; i++) {
            if (guess[i].equals(secretCode[i])) {
                pegs = pegs.concat("B");
                hashCodes.add(guess[i].hashCode());
            }
        }
        for (int i = 0; i < guess.length; i++) {
            if (secretC.contains(guess[i]) && !(hashCodes.contains(guess[i].hashCode()))) {
                pegs = pegs.concat("W");
                hashCodes.add(guess[i].hashCode());
            } else {
                pegs = pegs.concat(" ");
            }
        }
        String shuffledString = "";

        while (pegs.length() != 0)
        {
            int index = (int) Math.floor(Math.random() * pegs.length());
            String c = pegs.substring(index, index + 1);
            pegs = pegs.substring(0,index)+pegs.substring(index+1);
            shuffledString = shuffledString.concat(c);
        }

        shuffledString = shuffledString.replaceAll(" ", "");
        return shuffledString;

    }

    public String winGame() {
//        if (highScore == null) {
//            highScore = this;
//        }
//        int turns = highScore.getNumOfTurns();
//        if (turns > numOfTurns) {
//            highScore = this;
//        }
        return getName() + " won with " + getNumOfTurns() + " turns left!";
    }

    public int getNumOfTurns() {
        return numOfTurns;
    }

    public String getSecretC() {
        return secretC;
    }

    public String getName() { return player; }
}
