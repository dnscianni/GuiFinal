/**
 * *************************************************************
 * file: GUIGame.java author: WYSIWizards class: CS 141 ? Programming and
 * Problem Solving
 * 
* assignment: QTR Project date last modified: 2/28/2013
 *
 * purpose: This program is the main method of the game, and creates all the
 * panels and the frame.
 * 
***************************************************************
 */
package hangman;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.io.*;
import javax.swing.JOptionPane;

public class GUIGame extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? " + SwingUtilities.isEventDispatchThread());

        GUIGame frame = new GUIGame("Escape From WYSIWizard Castle!");

    }
    private final JButton bPlay;
    private final JButton bHighScore;
    private final JButton bCredits;
    private final JButton bSkip;
    private final JButton bEnd;
    private final MenuPanel menu;
    private final HiScorePanel hiScore;
    private final JButton bBack;
    private final CreditPanel credits;
    private final TitlePanel titleP;
    private HangmanPanel hangmanGame;
    private ColorMatchPanel colorMatchGame;
    private SudokuPanel sudoku;
    private final EndPanel end;
    private final Timer hangmanTimer;
    private final Timer colorTimer;
    private final Timer gameTimer;
    private final JLabel time;
    private int score;
    private HScore hScore;

    private GUIGame(String game_Title) {
        setSize(600, 400);
        setTitle(game_Title);
        setBackground(Color.black);
        setForeground(Color.white);

        score = 0;

        hScore = new HScore();

        try {// this block of code will load the saved high score and set it to
            // the hScore in main
            FileInputStream fis = new FileInputStream("saved.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            hScore = (HScore) ois.readObject();
            fis.close();
            ois.close();

        } catch (ClassNotFoundException e1) {
        } catch (IOException e1) {
        }

        hangmanTimer = new Timer(0, this);
        hangmanTimer.setRepeats(false);
        hangmanTimer.setActionCommand("goToColor");
        colorTimer = new Timer(0, this);
        colorTimer.setRepeats(false);
        colorTimer.setActionCommand("goToSudoku");
        gameTimer = new Timer(0, this);
        gameTimer.setRepeats(false);
        gameTimer.setActionCommand("OVER");

        time = new JLabel();
        time.setBounds(getWidth() - 200, 10, 200, 10);

        bPlay = new JButton("PLAY");
        bHighScore = new JButton("High Scores");
        bCredits = new JButton("Credits");
        bBack = new JButton("Back");
        bSkip = new JButton("Skip");
        bEnd = new JButton("END");


        bPlay.setSize(110, 30);
        bPlay.setLocation(getWidth() - 130, getHeight() - 180);
        bPlay.setActionCommand("play");
        bPlay.addActionListener(this);
        bPlay.setToolTipText("Press this button to start your escape from the WYSI Wiazrds!");

        bHighScore.setSize(110, 30);
        bHighScore.setLocation(getWidth() - 130, getHeight() - 140);
        bHighScore.setActionCommand("hiScore");
        bHighScore.addActionListener(this);
        bHighScore.setToolTipText("Press this button to view the high scores.");


        bCredits.setSize(110, 30);
        bCredits.setLocation(getWidth() - 130, getHeight() - 100);
        bCredits.setActionCommand("credits");
        bCredits.addActionListener(this);
        bCredits.setToolTipText("Press this button to view the makers of this game.");

        bBack.setSize(110, 30);
        bBack.setLocation(getWidth() - 580, getHeight() - 120);
        bBack.setActionCommand("back");
        bBack.addActionListener(this);
        bBack.setToolTipText("Press this button to go back to the main menu.");

        bSkip.setSize(60, 25);
        bSkip.setLocation(10, getHeight() / 2 - 30);
        bSkip.setActionCommand("skip");
        bSkip.addActionListener(this);
        bSkip.setToolTipText("Press this button to skip the Hangman game and drop your score to zero.");



        bEnd.setSize(300, 100);
        bEnd.setLocation(getWidth() / 2 - 150, getHeight() / 2 + 50);
        bEnd.setActionCommand("menu");
        bEnd.addActionListener(this);
        bEnd.setToolTipText("This will lead you back to the main menu");

        menu = new MenuPanel();
        hiScore = new HiScorePanel();
        credits = new CreditPanel();
        titleP = new TitlePanel();
        end = new EndPanel();

        add(titleP);



        Timer t = new Timer(3000, this);
        t.setActionCommand("timer");
        t.setRepeats(false);
        t.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("hiScore".equals(e.getActionCommand())) {
            remove(bPlay);
            remove(bHighScore);
            remove(bCredits);
            remove(menu);
            hiScore.setHighScore(hScore);
            add(bBack);
            add(hiScore);
            revalidate();
            repaint();
        } else if ("back".equals(e.getActionCommand())) {
            remove(bBack);
            remove(hiScore);
            remove(credits);
            add(bPlay);
            add(bHighScore);
            add(bCredits);
            add(menu);
            revalidate();
            repaint();
        } else if ("credits".equals(e.getActionCommand())) {
            remove(bPlay);
            remove(bHighScore);
            remove(bCredits);
            remove(menu);
            add(bBack);
            add(credits);
            revalidate();
            repaint();
        } else if ("timer".equals(e.getActionCommand())) {
            remove(titleP);
            add(bPlay);
            add(bHighScore);
            add(bCredits);
            add(menu);
            revalidate();
            repaint();
        } else if ("play".equals(e.getActionCommand())) {
            remove(menu);
            remove(bPlay);
            remove(bHighScore);
            remove(bCredits);
            add(bSkip);
            add(time);
            hangmanGame = new HangmanPanel(hangmanTimer);
            add(hangmanGame);
            revalidate();
            repaint();
        } else if ("goToColor".equals(e.getActionCommand())) {

            hangmanTimer.stop();
            remove(hangmanGame);
            remove(bSkip);
            colorMatchGame = new ColorMatchPanel(colorTimer, hangmanGame.getScore());
            add(colorMatchGame);

            revalidate();
            repaint();



        } else if ("goToSudoku".equals(e.getActionCommand())) {

            colorTimer.stop();
            remove(colorMatchGame);
            remove(bSkip);
            sudoku = new SudokuPanel(gameTimer, colorMatchGame.getScore());
            add(sudoku);

            revalidate();
            repaint();

        } else if ("skip".equals(e.getActionCommand())) {
            hangmanTimer.stop();

            end.setScore(0);

            remove(hangmanGame);
            remove(bSkip);
            colorMatchGame = new ColorMatchPanel(colorTimer, 0);
            add(colorMatchGame);

            revalidate();
            repaint();
        } else if ("menu".equals(e.getActionCommand())) {
            remove(bEnd);
            remove(end);
            add(bPlay);
            add(bHighScore);
            add(bCredits);
            add(menu);
            revalidate();
            repaint();

        } else if ("OVER".equals(e.getActionCommand())) {
            gameTimer.stop();
            end.setScore(sudoku.getScore()); //change to sudoku
            remove(hangmanGame);
            remove(colorMatchGame);
            remove(sudoku);
            remove(bSkip);
            add(bEnd);
            add(end);
            revalidate();
            repaint();
            if (hScore.didMake(end.getScore())) {
                JFrame frame = new JFrame();
                String s;
                s = (String) JOptionPane.showInputDialog(
                        frame,
                        "Do you want to save your score?\n" + "Enter Your Initials",
                        "You Made It!",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null, null);

                //If a string was returned, say so.
                if ((s != null) && (s.length() > 0)) {

                    s = s.trim();
                    if (s.length() > 3) {
                        s = s.substring(0, 3);
                    }

                    hScore.addHscore(s, end.getScore());

                    try {
                        FileOutputStream fos = new FileOutputStream("saved.dat");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(hScore);
                        fos.close();
                        oos.close();

                    } catch (FileNotFoundException e1) {
                    } catch (IOException e1) {
                    }

                }




            }


        }

    }
}
