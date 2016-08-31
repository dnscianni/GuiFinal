/*************************************************************** 
* file: MenuPanel.java 
*  author: WYSIWizards
*  class: CS 141 � Programming and Problem Solving 
* 
*  assignment: QTR Project 
*  date last modified: 2/7/2013 
* 
*  purpose: This program creates a panel for the express use of the main menu
*  in which an image is shown.
* 
****************************************************************/
package hangman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


class MenuPanel extends JPanel {

    private BufferedImage pic;
    
    public MenuPanel(){

        setBackground(Color.black);
    }

    //method: getPreferredSize
    //purpose: to set the preferred size of the panel to 600,400 
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    //method: paintCompoent
    //purpose: to paint the image to the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw Text

        try {
            pic = ImageIO.read(new File("src\\wizard.jpg"));
        } catch (IOException e) {
        }
        g.drawImage(pic, 10, 30, 450, 310, this);
    }
}
