/**
 * *************************************************************
 * file: SudokuPanel.java 
 * author: WYSIWizards 
 * class: CS 141 ? Programming and Problem Solving
 * 
* assignment: QTR Project date last modified: 2/7/2013
 * 
 * purpose: This program creates a panel for the express use of the color
 * match game. It keeps track of the buttons used and the score the user has 
 * earned.
 * 
***************************************************************
 */
package hangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.Timer;

class SudokuPanel extends JPanel implements ActionListener {

	private Timer gameTimer;
	private int score;
        private int tempScore;
        private JTextField[][] sudoku;
        private String[][] answers;
        private Boolean[][] checked;
        private JButton submit;
        private JButton quit;
        private JLabel displayScore;
        private JLabel timeLabel;
        private BufferedImage pic;

         //method: ColorMatchPanel
         //purpose:to intialize the timer, buttons , and the out of bounds area
         // for the buttons
	public SudokuPanel(Timer t, int score) {

		gameTimer = t;
		setLayout(null);

		this.score = score + 540;
                tempScore = score;
                
                displayScore = new JLabel("Score: " + this.score);
                displayScore.setBounds(50, 270, 80, 30);
                displayScore.setForeground(Color.white);
                this.add(displayScore);
        
		setBackground(Color.black);
		setForeground(Color.white);    
                
                submit = new JButton("Submit");
                submit.setActionCommand("submit");
                submit.addActionListener(this);
                submit.setBounds(45, 300, 80, 20);
                add(submit);
                
                quit = new JButton("Quit");
                quit.setActionCommand("quit");
                quit.addActionListener(this);
                quit.setBounds(475, 300, 80, 20);
                add(quit);
                
                sudoku = new JTextField[9][9];
                checked = new Boolean[9][9];
                answers = new String[9][9];
                
                for(int i = 0 ; i < 9 ; i++){
                    for(int j = 0 ; j < 9 ; j++){
                        checked[i][j] = false;
                    }
                }
                
                answers[0] = new String[] {"8", "3", "5", "4", "1", "6", "9", "2", "7"};
                answers[1] = new String[] {"2", "9", "6", "8", "5", "7", "4", "3", "1"};
                answers[2] = new String[] {"4", "1", "7", "2", "9", "3", "6", "5", "8"};
                answers[3] = new String[] {"5", "6", "9", "1", "3", "4", "7", "8", "2"};
                answers[4] = new String[] {"1", "2", "3", "6", "7", "8", "5", "4", "9"};
                answers[5] = new String[] {"7", "4", "8", "5", "2", "9", "1", "6", "3"};
                answers[6] = new String[] {"6", "5", "2", "7", "8", "1", "3", "9", "4"};
                answers[7] = new String[] {"9", "8", "1", "3", "4", "5", "2", "7", "6"};
                answers[8] = new String[] {"3", "7", "4", "9", "6", "2", "8", "1", "5"};
                
                
                for(int i = 0 ; i < 9 ; i++){
                    for(int j = 0 ; j < 9 ; j++){
                        sudoku[i][j] = new JTextField();
                        
                        sudoku[i][j].setBounds(187 + (25*j), 100 + (25*i), 15, 15);
                        
                        add(sudoku[i][j]);
                    }
                }
                
                sudoku[0][0].setEditable(false);
                sudoku[0][0].setText("8");
                
                sudoku[0][3].setEditable(false);
                sudoku[0][3].setText("4");
                
                sudoku[0][5].setEditable(false);
                sudoku[0][5].setText("6");
                
                sudoku[0][8].setEditable(false);
                sudoku[0][8].setText("7");
                
                sudoku[1][6].setEditable(false);
                sudoku[1][6].setText("4");
                
                sudoku[2][1].setEditable(false);
                sudoku[2][1].setText("1");
                
                sudoku[2][6].setEditable(false);
                sudoku[2][6].setText("6");
                
                sudoku[2][7].setEditable(false);
                sudoku[2][7].setText("5");
                
                sudoku[3][0].setEditable(false);
                sudoku[3][0].setText("5");
                
                sudoku[3][2].setEditable(false);
                sudoku[3][2].setText("9");
                
                sudoku[3][4].setEditable(false);
                sudoku[3][4].setText("3");
                
                sudoku[3][6].setEditable(false);
                sudoku[3][6].setText("7");
                
                sudoku[3][7].setEditable(false);
                sudoku[3][7].setText("8");
                
                sudoku[4][4].setEditable(false);
                sudoku[4][4].setText("7");
                
                sudoku[5][1].setEditable(false);
                sudoku[5][1].setText("4");
                
                sudoku[5][2].setEditable(false);
                sudoku[5][2].setText("8");
                
                sudoku[5][4].setEditable(false);
                sudoku[5][4].setText("2");
                
                sudoku[5][6].setEditable(false);
                sudoku[5][6].setText("1");
                
                sudoku[5][8].setEditable(false);
                sudoku[5][8].setText("3");
                
                sudoku[6][1].setEditable(false);
                sudoku[6][1].setText("5");
                
                sudoku[6][2].setEditable(false);
                sudoku[6][2].setText("2");
                
                sudoku[6][7].setEditable(false);
                sudoku[6][7].setText("9");
                
                sudoku[7][2].setEditable(false);
                sudoku[7][2].setText("1");
                
                sudoku[8][0].setEditable(false);
                sudoku[8][0].setText("3");
                
                sudoku[8][3].setEditable(false);
                sudoku[8][3].setText("9");
                
                sudoku[8][5].setEditable(false);
                sudoku[8][5].setText("2");
                
                sudoku[8][8].setEditable(false);
                sudoku[8][8].setText("5");
                
                

		revalidate();
		repaint();

		final DateFormat timeFormat = new SimpleDateFormat(
				"MMMM dd, YYYY  HH:mm:ss");
                timeLabel = new JLabel(timeFormat.format(new Date()));
                timeLabel.setForeground(Color.white);
                timeLabel.setBounds(440, 10, 200, 20);
                add(timeLabel);
		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// repaint(getWidth() - 200, 0, 200, 20);
                                timeLabel.setText(timeFormat.format(new Date()));
				repaint(136, 0, 464, 102);
				revalidate();
				// repaint(x2,y,w,w);
			}
		};
		Timer timer = new Timer(1000, timerListener);
		timer.setInitialDelay(0);
		timer.start();
               
	}

         //method: paintComponent
         //purpose: to paint to the screen the time, and the button that needs to
         // be pressed this round. Color for word is set by color.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		repaint();
		revalidate();
		Graphics2D g2 = (Graphics2D) g;
                try {
                    pic = ImageIO.read(new File("src\\door.jpg"));
                } catch (IOException ex) {
                    Logger.getLogger(SudokuPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.drawImage(pic, 130, 10, 325, 350, this);
		Font font = new Font("Lucida Console", Font.PLAIN, 12);
		g2.setFont(font);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		font = new Font("Lucida Console", Font.PLAIN, 30);
		g2.setFont(font);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if("quit".equals(e.getActionCommand())) {
            score = tempScore;
            gameTimer.start();
        }
        else if("submit".equals(e.getActionCommand())){
            boolean endGame = true;
            for(int i = 0 ; i < 9 ; i++){
                for(int j = 0 ; j < 9 ; j++){
                    if(!answers[i][j].equals(sudoku[i][j].getText())){
                        if(!checked[i][j]){
                            score -=10;
                            checked[i][j] = true;
                        }
                        endGame = false;
                    }
                }
            }
            if(endGame){
                gameTimer.start();
            }
        }
        displayScore.setText("Score " + score);
    }

    int getScore() {
        return score;
    }
        
}