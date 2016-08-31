/**
 * *************************************************************
 * file: HiScorePanel.java author: WYSIWizards class: CS 141 � Programming and
 * Problem Solving
 * 
* assignment: QTR Project date last modified: 2/7/2013
 * 
* purpose: This program creates a panel for the express use of showing the High
 * Scores
 * 
***************************************************************
 */
package hangman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class HiScorePanel extends JPanel {

    private HScore hScore;

    public HiScorePanel() {
        setBackground(Color.black);
        setForeground(Color.white);
    }

    //method: getPreferredSize
    //purpose: to set the preferred size of the panel to 600,400 
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    //method: paintComponent
    //purpose: to paint the High Scores, currently is not dynamic 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Lucida Console", Font.BOLD, 30);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("High Scores", 190, 100);
        font = new Font("Lucida Console", Font.PLAIN, 20);
        g2.setFont(font);
        for (int i = 0, j = 160; i < 5; i++, j += 20) {
            g.drawString(hScore.getInit(i)+
            "......"+hScore.getScores(i)
            , 225, j);
        }
        
    }

    void setHighScore(HScore hScore) {
        this.hScore = hScore;
    }
}
