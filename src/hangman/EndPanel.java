/*************************************************************** 
* file: EndPanel.java 
*  author: WYSIWizards
*  class: CS 141 � Programming and Problem Solving 
* 
*  assignment: QTR Project 
*  date last modified: 2/7/2013 
* 
*  purpose: This program creates a panel for the express use of end of the game
*  in which score  and  "end" is displayed
* 
****************************************************************/
package hangman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;



class EndPanel extends JPanel {
    
    private int score;
    
    public EndPanel() {
		setBackground(Color.black);
		setForeground(Color.white);
	}
    
    //method: getPreferredSize
    //purpose: to set the preferred size of the panel to 600,400 
    public Dimension getPreferredSize(){
        return new Dimension(600,400);
    }
    
    //method: paintComponent
    //purpose: paints the word "end" and the player's final score to the panel
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Font font = new Font("Lucida Console", Font.PLAIN, 30);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("END", this.getWidth()/2 - 100, 100);
        g.drawString("Score: " + score, this.getWidth()/2 - 100, 200);
    }
    //method: setScore
       //purpose: to set the final score of the game
    void setScore(int score) {
        this.score = score;
    }
    
    //method: getScore
       //purpose: to get the final score of the game
    public int getScore(){
        return score;
    }
}
