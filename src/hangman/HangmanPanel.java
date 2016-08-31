/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HangmanPanel extends JPanel implements ActionListener {

    private static JButton alphabet[];
    private HangmanDrawnPanel top;
    private int score;
    private int iFoundLetters;
    private String words[];
    private String answer;
    private Timer gameTimer;
    private BufferedImage pic;

    public HangmanPanel(Timer t) {
        super();
        
        setBackground(Color.black);
        setForeground(Color.white);

        gameTimer = t;
        score = 100;
        iFoundLetters = 0;
        words = new String[5];
        words[0] = "ABSTRACT";
        words[1] = "CEMETARY";
        words[2] = "NURSE";
        words[3] = "PHARMACY";
        words[4] = "CLIMBING";

        answer = words[(int) ((Math.random() * 5) - 1)];

        top = new HangmanDrawnPanel(answer);
        add(top);

        alphabet = new JButton[26];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = new JButton("" + (char) (65 + i));
            alphabet[i].addActionListener(this);
            alphabet[i].setActionCommand("" + (char) (65 + i));
            alphabet[i].setToolTipText("Press this button to select a letter for your guess");
            add(alphabet[i]);

        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    public int getScore() {
        return score;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            pic = ImageIO.read(new File("src\\scroll.jpg"));
        } catch (IOException e) {
        }
        g.drawImage(pic, -10, 200, 610, 160, this);
        
        Font font = new Font("Lucida Console", Font.PLAIN, 12);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString("Score: " + score, getWidth() - 100, getHeight() - 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String key = e.getActionCommand();
        
        for(int i = 0 ; i < 26 ; i ++){
            if(alphabet[i].getActionCommand().equals(key)){
                alphabet[i].setEnabled(false);
            }
        }

        if (answer.contains(key)) {
            top.addToFoundLetters(key);
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == key.charAt(0)) {
                    iFoundLetters++;
                }
            }
            if (iFoundLetters == answer.length()) {
                    gameTimer.start();
                }
        } else {
            score -= 10;
            top.IncrementMistakes();
            this.repaint(getWidth() - 100, 375, 80, 24);
            if (score == 40) {
                gameTimer.start();
            }
        }
    }
}
