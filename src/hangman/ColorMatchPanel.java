/**
 * *************************************************************
 * file: ColorMatchPanel.java 
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


class ColorMatchPanel extends JPanel implements ActionListener {

	private Timer gameTimer;
	private JButton buttons[];
	private String answer;
	private String[] words;
	private int color;
	private int word;
	private int turns;
	private Rectangle outOfBounds;
	private int score;
        private JLabel timeLabel;

         //method: ColorMatchPanel
         //purpose:to intialize the timer, buttons , and the out of bounds area
         // for the buttons
	public ColorMatchPanel(Timer t, int score) {

		outOfBounds = new Rectangle(getWidth() / 2, getHeight() / 4, 225, 105);
		outOfBounds.setLocation(225, 0);

		gameTimer = t;
		buttons = new JButton[5];
		setLayout(null);

		words = new String[] { "BLUE", "GREEN", "MAGENTA", "RED", "YELLOW" };
		turns = 0;

		this.score = score;

		setBackground(Color.black);
		setForeground(Color.white);

		for (int i = 0; i < 5; i++) {
			buttons[i] = new JButton();
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand("hit" + i);
			buttons[i].setIcon(new ImageIcon("src\\potion" + i + ".jpg"));
			buttons[i].setRolloverIcon(new ImageIcon("src\\potion" + i
					+ "Glow.jpg"));
			buttons[i].setBorder(null);
			add(buttons[i]);
		}

		randomize();
		revalidate();
		repaint();

		final DateFormat timeFormat = new SimpleDateFormat(
				"MMMM dd, YYYY  HH:mm:ss");
                timeLabel = new JLabel(timeFormat.format(new Date()));
                timeLabel.setForeground(Color.white);
                timeLabel.setBounds(240, 10, 200, 20);
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

	@Override
         //method: paintComponent
         //purpose: to paint to the screen the time, and the button that needs to
         // be pressed this round. Color for word is set by color.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		repaint();
		revalidate();
		Graphics2D g2 = (Graphics2D) g;
		Font font = new Font("Lucida Console", Font.PLAIN, 12);
		g2.setFont(font);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		font = new Font("Lucida Console", Font.PLAIN, 30);
		g2.setFont(font);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		switch (color) {
		case 0:
			g.setColor(Color.BLUE);
			break;
		case 1:
			g.setColor(Color.GREEN);
			break;
		case 2:
			g.setColor(Color.MAGENTA);
			break;
		case 3:
			g.setColor(Color.RED);
			break;
		default:
			g.setColor(Color.YELLOW);
		}
		;

		g2.drawString(words[word], getWidth() / 2, getHeight() / 4);

	}

         //method: randomize
         //purpose: to randomize the position of the buttons without them overlapping
         // each other and to randomize the answer and color of the button that
         // needs to be pressed.
	private void randomize() {
		for (int i = 0; i < 5; i++) {
			int x = (int) (Math.random() * 525);
			int y = (int) (Math.random() * 300);

                        if (i == 0) {
                            while (y <= 120 || x >= 500 || y >= 300) {
                                        x = (int) (Math.random() * 525);
					y = (int) (Math.random() * 300);
                                    }
                        } else if (i == 1) {
				while ((x >= buttons[0].getX() && x <= buttons[0].getX() + 75
						&& y >= buttons[0].getY() && y <= buttons[0].getY() + 75)
						|| (x + 75 >= buttons[0].getX()
								&& x + 75 <= buttons[0].getX() + 75
								&& y + 75 >= buttons[0].getY() && y + 75 <= buttons[0]
								.getY() + 75)
						|| (x >= buttons[0].getX()
								&& x <= buttons[0].getX() + 75
								&& y + 75 >= buttons[0].getY() && y + 75 <= buttons[0]
								.getY() + 75)
						|| (x + 75 >= buttons[0].getX()
								&& x + 75 <= buttons[0].getX() + 75
								&& y >= buttons[0].getY() && y <= buttons[0]
								.getY() + 75)
						|| (y <= 120) || x >= 500 || y >= 300) {

					x = (int) (Math.random() * 525);
					y = (int) (Math.random() * 300);
				}
			} else if (i == 2) {
				while (((x >= buttons[0].getX() && x <= buttons[0].getX() + 75
						&& y >= buttons[0].getY() && y <= buttons[0].getY() + 75)
						|| (x + 75 >= buttons[0].getX()
								&& x + 75 <= buttons[0].getX() + 75
								&& y + 75 >= buttons[0].getY() && y + 75 <= buttons[0]
								.getY() + 75)
						|| (x >= buttons[0].getX()
								&& x <= buttons[0].getX() + 75
								&& y + 75 >= buttons[0].getY() && y + 75 <= buttons[0]
								.getY() + 75) || (x + 75 >= buttons[0].getX()
						&& x + 75 <= buttons[0].getX() + 75
						&& y >= buttons[0].getY() && y <= buttons[0].getY() + 75))
						|| ((x >= buttons[1].getX()
								&& x <= buttons[1].getX() + 75
								&& y >= buttons[1].getY() && y <= buttons[1]
								.getY() + 75)
								|| (x + 75 >= buttons[1].getX()
										&& x + 75 <= buttons[1].getX() + 75
										&& y + 75 >= buttons[1].getY() && y + 75 <= buttons[1]
										.getY() + 75)
								|| (x >= buttons[1].getX()
										&& x <= buttons[1].getX() + 75
										&& y + 75 >= buttons[1].getY() && y + 75 <= buttons[1]
										.getY() + 75) || (x + 75 >= buttons[1]
								.getX()
								&& x + 75 <= buttons[1].getX() + 75
								&& y >= buttons[1].getY() && y <= buttons[1]
								.getY() + 75))
						|| (y <= 120) || x >= 500 || y >= 300) {

					x = (int) (Math.random() * 525);
					y = (int) (Math.random() * 300);
				}
			} else if (i == 3) {
				while (((x >= buttons[0].getX() && x <= buttons[0].getX() + 75
						&& y >= buttons[0].getY() && y <= buttons[0].getY() + 75)
						|| (x + 75 >= buttons[0].getX()
								&& x + 75 <= buttons[0].getX() + 75
								&& y + 75 >= buttons[0].getY() && y + 75 <= buttons[0]
								.getY() + 75)
						|| (x >= buttons[0].getX()
								&& x <= buttons[0].getX() + 75
								&& y + 75 >= buttons[0].getY() && y + 75 <= buttons[0]
								.getY() + 75) || (x + 75 >= buttons[0].getX()
						&& x + 75 <= buttons[0].getX() + 75
						&& y >= buttons[0].getY() && y <= buttons[0].getY() + 75))
						|| ((x >= buttons[1].getX()
								&& x <= buttons[1].getX() + 75
								&& y >= buttons[1].getY() && y <= buttons[1]
								.getY() + 75)
								|| (x + 75 >= buttons[1].getX()
										&& x + 75 <= buttons[1].getX() + 75
										&& y + 75 >= buttons[1].getY() && y + 75 <= buttons[1]
										.getY() + 75)
								|| (x >= buttons[1].getX()
										&& x <= buttons[1].getX() + 75
										&& y + 75 >= buttons[1].getY() && y + 75 <= buttons[1]
										.getY() + 75) || (x + 75 >= buttons[1]
								.getX()
								&& x + 75 <= buttons[1].getX() + 75
								&& y >= buttons[1].getY() && y <= buttons[1]
								.getY() + 75))
						|| ((x >= buttons[2].getX()
								&& x <= buttons[2].getX() + 75
								&& y >= buttons[2].getY() && y <= buttons[2]
								.getY() + 75)
								|| (x + 75 >= buttons[2].getX()
										&& x + 75 <= buttons[2].getX() + 75
										&& y + 75 >= buttons[2].getY() && y + 75 <= buttons[2]
										.getY() + 75)
								|| (x >= buttons[2].getX()
										&& x <= buttons[2].getX() + 75
										&& y + 75 >= buttons[2].getY() && y + 75 <= buttons[2]
										.getY() + 75) || (x + 75 >= buttons[2]
								.getX()
								&& x + 75 <= buttons[2].getX() + 75
								&& y >= buttons[2].getY() && y <= buttons[2]
								.getY() + 75))
						|| (y <= 120) || x >= 500 || y >= 300) {

					x = (int) (Math.random() * 525);
					y = (int) (Math.random() * 300);
				}
			} else if (i == 4) {
				while (((x >= buttons[0].getX() && x <= buttons[0].getX() + 75
						&& y >= buttons[0].getY() && y <= buttons[0].getY() + 75)
						|| (x + 75 >= buttons[0].getX()
								&& x + 75 <= buttons[0].getX() + 75
								&& y + 75 >= buttons[0].getY() && y + 75 <= buttons[0]
								.getY() + 75)
						|| (x >= buttons[0].getX()
								&& x <= buttons[0].getX() + 75
								&& y + 75 >= buttons[0].getY() && y + 75 <= buttons[0]
								.getY() + 75) || (x + 75 >= buttons[0].getX()
						&& x + 75 <= buttons[0].getX() + 75
						&& y >= buttons[0].getY() && y <= buttons[0].getY() + 75))
						|| ((x >= buttons[1].getX()
								&& x <= buttons[1].getX() + 75
								&& y >= buttons[1].getY() && y <= buttons[1]
								.getY() + 75)
								|| (x + 75 >= buttons[1].getX()
										&& x + 75 <= buttons[1].getX() + 75
										&& y + 75 >= buttons[1].getY() && y + 75 <= buttons[1]
										.getY() + 75)
								|| (x >= buttons[1].getX()
										&& x <= buttons[1].getX() + 75
										&& y + 75 >= buttons[1].getY() && y + 75 <= buttons[1]
										.getY() + 75) || (x + 75 >= buttons[1]
								.getX()
								&& x + 75 <= buttons[1].getX() + 75
								&& y >= buttons[1].getY() && y <= buttons[1]
								.getY() + 75))
						|| ((x >= buttons[2].getX()
								&& x <= buttons[2].getX() + 75
								&& y >= buttons[2].getY() && y <= buttons[2]
								.getY() + 75)
								|| (x + 75 >= buttons[2].getX()
										&& x + 75 <= buttons[2].getX() + 75
										&& y + 75 >= buttons[2].getY() && y + 75 <= buttons[2]
										.getY() + 75)
								|| (x >= buttons[2].getX()
										&& x <= buttons[2].getX() + 75
										&& y + 75 >= buttons[2].getY() && y + 75 <= buttons[2]
										.getY() + 75) || (x + 75 >= buttons[2]
								.getX()
								&& x + 75 <= buttons[2].getX() + 75
								&& y >= buttons[2].getY() && y <= buttons[2]
								.getY() + 75))
						|| ((x >= buttons[3].getX()
								&& x <= buttons[3].getX() + 75
								&& y >= buttons[3].getY() && y <= buttons[3]
								.getY() + 75)
								|| (x + 75 >= buttons[3].getX()
										&& x + 75 <= buttons[3].getX() + 75
										&& y + 75 >= buttons[3].getY() && y + 75 <= buttons[3]
										.getY() + 75)
								|| (x >= buttons[3].getX()
										&& x <= buttons[3].getX() + 75
										&& y + 75 >= buttons[3].getY() && y + 75 <= buttons[3]
										.getY() + 75) || (x + 75 >= buttons[3]
								.getX()
								&& x + 75 <= buttons[3].getX() + 75
								&& y >= buttons[3].getY() && y <= buttons[3]
								.getY() + 75))
						|| (y <= 120) || x >= 500 || y >= 300) {

					x = (int) (Math.random() * 525);
					y = (int) (Math.random() * 300);
				}
			}

			buttons[i].setBounds(x, y, 75, 75);
		}
		color = (int) (Math.random() * 5);
		word = (int) (Math.random() * 5);
	}

	@Override
         //method: actionPerformed
        //purpose: checks if the correct button was pressed, if so 100 pts are 
         //awarded to the player. It then calls for randomize and repaint. It
         // then increases turn by 1. If 5 turns has passed the game ends.
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().contains("" + color))
			score += 100;

		randomize();
		revalidate();
		repaint();
		turns++;

		if (turns == 5)
			gameTimer.start();

	}

         //method: getScore
        //purpose: to return the current score.
	int getScore() {
		return score;
	}

}
