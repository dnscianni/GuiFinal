/**
 * *************************************************************
 * file: HScore.java 
 * author: WYSIWizards 
 * class: CS 141 ? Programming and Problem Solving
 * 
* assignment: QTR Project date last modified: 2/28/2013
 * 
 * purpose: This program creates a Serializable class that 
 * will save all the high scores to a database, so as to 
 * read and write to a file for future use.
 * 
***************************************************************
 */
package hangman;

import java.io.Serializable;

public class HScore implements Serializable {

    private String[] init;
    private int[] scores;

    //method: HScore
         //purpose:to intialize the initial scores and 
         //names of the people with the highest scores.
    public HScore() {

        init = new String[]{"RMJ", "DNS", "AJD", "ABC", "MOM"};
        scores = new int[]{500, 400, 300, 100, 40};
    }

  
    //method: getInit
         //purpose:to get the initial name at index i.
    public String getInit(int i) {
        return init[i];
    }

    //method: getScores
         //purpose:to get the scores at index i
    public int getScores(int i) {
        return scores[i];
    }

    //method: didMake
         //purpose:to check whether the passed score is greater 
         //that the lowest score saved in scores[4], so as to 
         //add the new score to the highest scores.
    public boolean didMake(int score) {
        {
            if (scores[4] < score) {
                return true;
            }
        }

        return false;
    }

    //method: addHscore
         //purpose:to add the new high score to the array by making 
         //it the lowest score, then swapping it if it is larger than 
         //the one above it.
    public void addHscore(String name, int score) {


        scores[4] = score;
        init[4] = name;

        for (int i = 4; i > 0; i--) {
            if (scores[i] < scores[i - 1]) {
                break;
            }

            swap(i, i - 1);

        }

    }

    //method: swap
         //purpose:to swap the scores in the scores array at index i and j
    private void swap(int i, int j) {
        String sTemp = init[j];
        init[j] = init[i];
        init[i] = sTemp;

        int iTemp = scores[j];
        scores[j] = scores[i];
        scores[i] = iTemp;
    }
}
