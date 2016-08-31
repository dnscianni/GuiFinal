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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Raymond
 */
class HangmanDrawnPanel extends JPanel {

    private JLabel timeLabel;
    private String answer;
    private int mistakes;
    private String sFoundLetters;
    private BufferedImage pic, pic2, pic3;
    int fire = 0;
    int x, y, w, x2;

    public HangmanDrawnPanel(String answer) {
        super();

        setBackground(Color.black);
        setForeground(Color.white);

        this.answer = answer;
        mistakes = 0;
        sFoundLetters = "";

        x = 160;
        y = 52;
        w = 20;
        x2 = 390;

        final DateFormat timeFormat = new SimpleDateFormat("MMMM dd, YYYY  HH:mm:ss");
        //Date date = new Date();
        timeLabel = new JLabel(timeFormat.format(new Date()));
        timeLabel.setBounds(240, 10, 200, 20);
        timeLabel.setForeground(Color.white);
        add(timeLabel);
        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fire == 0) {
                	try {
                        pic2 = ImageIO.read(new File("src\\fire1.jpg"));
                    } catch (IOException e1) {
                    }
                	fire = 1;
                } else if(fire == 1) {
                	try {
                        pic2 = ImageIO.read(new File("src\\fire2.jpg"));
                    } catch (IOException e2) {
                    }
                	fire = 2;
                } else {
                	try {
                        pic2 = ImageIO.read(new File("src\\fire3.jpg"));
                    } catch (IOException e3) {
                    }
                	fire = 0;
                }
                //repaint(getWidth() - 200, 0, 200, 20);
                timeLabel.setText(timeFormat.format(new Date()));
                repaint(136, 0, 464, 102);
                revalidate();
                //repaint(x2,y,w,w);
            }
        };
        Timer timer = new Timer(1000, timerListener);  
        timer.setInitialDelay(0);
        timer.start();

    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 250);
    }

    public void IncrementMistakes() {
        mistakes++;
        x -= 4;
        y -= 8;
        w += 8;
        x2 -= 4;
        repaint(136, 0, 464, 102);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Lucida Console", Font.PLAIN, 12);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString("Hangman", 10, 10);
        //g2.drawString(time, getWidth() - 200, 10);

        font = new Font("Lucida Console", Font.PLAIN, 35);
        g2.setFont(font);

        for (int i = 0; i < answer.length(); i++) {
            g.fillRect(45 + (i * 30), 245, 25, 5);
            if (sFoundLetters.contains("" + answer.charAt(i))) {
                g2.drawString("" + answer.charAt(i), 48 + (i * 30), 240);
            }
        }

        try {
            pic = ImageIO.read(new File("src\\wizard.jpg"));
        } catch (IOException e) {
        }
        try {
            pic3 = ImageIO.read(new File("src\\scroll.jpg"));
        } catch (IOException e) {
        }
        g.drawImage(pic, 110, 30, 325, 235, this);
        g.drawImage(pic3, -10, 200, 610, 160, this);
        
        g.drawImage(pic2, x, y, w, w, this);
        g.drawImage(pic2, x2, y, w, w, this);
        
        font = new Font("Lucida Console", Font.PLAIN, 35);
        g2.setFont(font);
        
        for(int i = 0 ; i < answer.length() ; i++){
            g.fillRect(45 + (i*30), 245, 25, 5);
            if(sFoundLetters.contains("" + answer.charAt(i))){
                g2.drawString("" + answer.charAt(i), 48 + (i*30), 240);
            }
        }
    }

    public void addToFoundLetters(String key) {
        sFoundLetters += key;
        repaint(45, 210, 400, 35);
    }
}
