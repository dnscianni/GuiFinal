/**
 * *************************************************************
 * file: CreditPanel.java 
 * author: WYSIWizards 
 * class: CS 141 � Programming and Problem Solving
 * 
* assignment: QTR Project date last modified: 2/7/2013
 * 
* purpose: This program creates a panel for the express use of the credits
 * which shows the creators of this game 
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

public class CreditPanel extends JPanel {
    
	public CreditPanel() {
		setBackground(Color.black);
		setForeground(Color.white);
	}
	
    //method: getPreferredSize
    //purpose: to set the preferred size of the panel to 600,400 
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    //method: paintComponent
    //purpose: to paint the creaters of this game along with their ID's 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Lucida Console", Font.PLAIN, 20);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("Created By", this.getWidth() / 2 - 60, 100);
        g.drawString("Alejandro Diaz      008202922", this.getWidth() / 2 - 170, 130);
        g.drawString("Raymond Mandujano   008265699", this.getWidth() / 2 - 170, 150);
        g.drawString("David Scianni       007402772", this.getWidth() / 2 - 170, 170);
    }
}
