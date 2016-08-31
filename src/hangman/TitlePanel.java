/*************************************************************** 
* file: TitlePanel.java 
*  author: WYSIWizards
*  class: CS 141 � Programming and Problem Solving 
* 
*  assignment: QTR Project 
*  date last modified: 2/7/2013 
* 
*  purpose: This program creates a panel for the express use of the title screen
*  in which the name of the game is shown
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


public class TitlePanel extends JPanel {
	
	public TitlePanel() {
		setBackground(Color.black);
		setForeground(Color.white);
	}
	
    //method: getPreferredSize
    //purpose: to set the preferred size of the panel to 600,400 
    public Dimension getPreferredSize(){
        return new Dimension(600,400);
    }
    
    //method: paintComponent
    //purpose: to paint the name of the game and the name of the team 
    //that created it
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Lucida Console", Font.BOLD, 25);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("Escape From WYSIWizard Castle!", this.getWidth()/2-235, 100);
        font = new Font("Lucida Console", Font.PLAIN, 10);
        g2.setFont(font);
        g.drawString("a CS 245 Project",this.getWidth()/2-200, 160);
        font = new Font("Lucida Console", Font.PLAIN, 20);
        g2.setFont(font);
        g.drawString("by WYSIWIZARDS", this.getWidth()/2-200, 240);
    }
}
