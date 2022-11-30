package tictactoe.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TicTacToePage extends JFrame implements ActionListener
{
    private final int ROWS = 10;
    private final int COLS = 10;
    private final int WIDTH = 1270;
    private final int HEIGHT = 800;
    
    private boolean player1_turn;
    private int player1_score = 0;
    private int player2_score = 0;
    
    private Timer timerPlayer1;
    private Timer timerPlayer2;
    
    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;
    private JLabel player1CounterLabel;
    private JLabel player2CounterLabel;
    
    
    private JButton resetButton;
    private JButton exitButton;
    
    private JButton[][] xoButtons = new JButton[ROWS][COLS];
    
    private int player1Second = 0;
    private int player2Second = 0;
    private int player1Minute = 1;
    private int player2Minute = 1;
    
    private String player1ddSecond,player1ddMinute; 
    private String player2ddSecond, player2ddMinute;
    private DecimalFormat dFormat = new DecimalFormat();
    
    
    public TicTacToePage()
    {
        JFrame tictactoeFrame = new JFrame("TicTacToe");
        tictactoeFrame.setResizable(false);
        tictactoeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tictactoeFrame.setSize(WIDTH, HEIGHT);
        tictactoeFrame.setLayout(new BorderLayout());
        tictactoeFrame.getContentPane().setBackground(new Color(0x78CEF2));
        tictactoeFrame.setLocationRelativeTo(null);
        tictactoeFrame.setLayout(new BorderLayout());
        
        JPanel buttonFrame = new JPanel();
        buttonFrame.setBackground(new Color(0x78CEF2));
        buttonFrame.setPreferredSize(new Dimension(WIDTH - 400, HEIGHT));
        buttonFrame.setLayout(new GridLayout(ROWS, COLS));
        tictactoeFrame.add(buttonFrame, BorderLayout.WEST);
        
        for (int i = 0 ; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                xoButtons[i][j] = new JButton();
                xoButtons[i][j].setFocusable(false);
                xoButtons[i][j].setFont(new Font("verdana", Font.BOLD, 30));
                xoButtons[i][j].addActionListener(this);
                buttonFrame.add(xoButtons[i][j]);
            }
        }
        
        JPanel scoreFrame = new JPanel();
        scoreFrame.setBackground(Color.GREEN);
        scoreFrame.setLayout(new GridLayout(3, 1));
        scoreFrame.setPreferredSize(new Dimension(400, HEIGHT));
        
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new GridLayout(2, 1));
        timerPanel.setBackground(new Color(0x78CEF2));
        scoreFrame.add(timerPanel);
        
        player1CounterLabel = new JLabel("Player 1 Time: 01:00", SwingConstants.CENTER);
        player2CounterLabel = new JLabel("Player 2 Time: 01:00", SwingConstants.CENTER);
        
        player1CounterLabel.setFont(new Font("verdana", Font.BOLD, 30));
        player2CounterLabel.setFont(new Font("verdana", Font.BOLD, 30));
        
        countDownPlayer1() ;
        countDownPlayer2();
        
        timerPanel.add(player1CounterLabel);
        timerPanel.add(player2CounterLabel);
        
        JPanel scorePanel = new JPanel();
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        scorePanel.setLayout(new GridLayout(2,2));
        scorePanel.setBackground(new Color(0x78CEF2));
        
        player1ScoreLabel = new JLabel();
        player1ScoreLabel.setText("Player X: " + String.valueOf(player1_score));
        player1ScoreLabel.setFont(new Font("verdana", Font.BOLD, 30));
        player1ScoreLabel.setBackground(new Color(0x78CEF2));
        player1ScoreLabel.setOpaque(true);
        player1ScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player1ScoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scorePanel.add(player1ScoreLabel);
        
        player2ScoreLabel = new JLabel();
        player2ScoreLabel.setText("Player O: " + String.valueOf(player2_score));
        player2ScoreLabel.setFont(new Font("verdana", Font.BOLD, 30));
        player2ScoreLabel.setBackground(new Color(0x78CEF2));
        player2ScoreLabel.setOpaque(true);
        player2ScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player2ScoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scorePanel.add(player2ScoreLabel);
        
        scoreFrame.add(scorePanel);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(0x78CEF2));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 100));
        
        resetButton = new JButton();
        resetButton.setText("Reset");
        resetButton.setFont(new Font("verdana", Font.BOLD, 20));
        resetButton.addActionListener(this);
        buttonsPanel.add(resetButton);
        
        exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.setFont(new Font("verdana", Font.BOLD, 20));
        exitButton.addActionListener(this);
        buttonsPanel.add(exitButton);
        
        scoreFrame.add(buttonsPanel);
        
        tictactoeFrame.add(scoreFrame, BorderLayout.EAST);
        
        tictactoeFrame.setVisible(true);
        randomStartingPlayer();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                if (e.getSource() == xoButtons[i][j])
                {
                    xoButtonClicked(i, j);
                }
            }
        }
        
        if (e.getSource() == resetButton)
        {
            resetButtonClicked();
        }
        
        else if (e.getSource() == exitButton)
        {
            exitButtonClicked();
        }
    }
    
    private void countDownPlayer1() 
    {
        timerPlayer1 = new Timer (900,new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent t) 
            {
                player1Second-- ; 
                player1ddSecond = dFormat.format(player1Second) ;
                player1ddMinute = dFormat.format(player1Minute) ; 
                player1CounterLabel.setText("Player 1 Time: " + player1ddMinute + ":" + player1ddSecond);
                if (player1Second == -1) 
                {
                    player1Second = 59 ; 
                    player1Minute--;
                    player1ddSecond = dFormat.format(player1Second);
                    player1ddMinute = dFormat.format(player1Minute) ; 
                    player1CounterLabel.setText("Player 1 Time: " + player1ddMinute + ":" + player1ddSecond);
                }
                if (player1Minute == 0 && player1Second == 0)
                {
                    timerPlayer1.stop();
                    timesUp();
                }
            }
        });
    }
    
    private void countDownPlayer2() 
    {
        timerPlayer2 = new Timer (900,new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent t) 
            {
                player2Second-- ; 
                player2ddSecond = dFormat.format(player2Second) ;
                player2ddMinute = dFormat.format(player2Minute) ; 
                player2CounterLabel.setText("Player 2 Time: " + player2ddMinute + ":" + player2ddSecond);
                if (player2Second == -1) 
                {
                    player2Second = 59 ; 
                    player2Minute --;
                    player2ddSecond = dFormat.format(player2Second);
                    player2ddMinute = dFormat.format(player2Minute) ; 
                    player2CounterLabel.setText("Player 2 Time: " + player2ddMinute + ":" + player2ddSecond);
                }
                if (player2Minute == 0 && player2Second == 0)
                {
                    timerPlayer2.stop();
                    timesUp();
                }
            }
        });
    }
    
    private void resetTimer()
    {
        timerPlayer1.stop();
        timerPlayer2.stop();

        player1Second = 0;
        player2Second = 0;

        player1Minute = 1;
        player2Minute = 1;

        player1CounterLabel.setText("Player 1 Time: 01:00");
        player2CounterLabel.setText("Player 2 Time: 01:00");  
    }
    
    private void timesUp()
    {   
        if (player1_turn)
        {
            JOptionPane.showMessageDialog(null, "Player 1 has ran out of time! Player 2 Wins!");
            player2_score++;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Player 2 has ran out of time! Player 1 Wins!");
            player1_score++;
        }
        updateScore();
        resetTimer();
        randomStartingPlayer();
        resetBoard();
    }
    
    private void randomStartingPlayer()
    {
        Random rd = new Random();
        int player = rd.nextInt(2);
        if (player == 0)
        {
            player1_turn = true;
            JOptionPane.showMessageDialog(null, "Player 1 Start");
            timerPlayer1.start();
            return;
        }
        player1_turn = false;
        JOptionPane.showMessageDialog(null, "Player 2 Start");
        timerPlayer2.start();
    }
    
    private void xoButtonClicked(int row, int col)
    {
        if (player1_turn)
        {
            if (xoButtons[row][col].getText().equals(""))
            {
                xoButtons[row][col].setText("X");
                player1_turn = false;
                timerPlayer1.stop();
                timerPlayer2.start();
            }
        }
        else
        {
            if (xoButtons[row][col].getText().equals(""))
            {
                xoButtons[row][col].setText("O");
                player1_turn = true;
                timerPlayer2.stop();
                timerPlayer1.start();
            }
        }
        
        if (checkHorizontal(row, col))
        {
            showWinner(row, col);
        }
        else if (checkVertical(row, col))
        {
            showWinner(row, col);
        }
        else if (checkDiagonal(row, col))
        {
            showWinner(row, col);
        }
    }
    
    private void resetButtonClicked()
    {
        int ans = JOptionPane.showConfirmDialog(null, "Do you want to reset the game?", "Reset", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == 0)
        {
            resetTimer();
            randomStartingPlayer();
            resetBoard();
        }
    }
    
    private void exitButtonClicked()
    {
        int ans = JOptionPane.showConfirmDialog(null, "Do you wish to exit the program?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == 0)
        {
            System.exit(0);
        }
    }
    
    private void showWinner(int row, int col)
    {
        resetTimer();
        if (xoButtons[row][col].getText().equals("X"))
        {
            JOptionPane.showMessageDialog(null, "Player 1 Wins!");
            player1_score++;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Player 2 Wins!");
            player2_score++;
        }
        randomStartingPlayer();
        updateScore();
        resetBoard();
    }
    
    private void updateScore()
    {
        player1ScoreLabel.setText("Player X: " + String.valueOf(player1_score));
        player2ScoreLabel.setText("Player O: " + String.valueOf(player2_score));
    }

    private void resetBoard()
    {
        for (int i = 0; i < ROWS; i++)
        {
           for (int j = 0; j < COLS; j++)
           {
              if (xoButtons[i][j].getText().equals("X") || xoButtons[i][j].getText().equals("O"))
              {
                  xoButtons[i][j].setText("");
              } 
           }
       }
    }
    
    private boolean checkHorizontal(int row, int col)
    {
        int count;
        String check = xoButtons[row][col].getText();
        
        count = checkLeft(row, col, check, -1) + checkRight(row, col, check, -1);
        
        return count >= 4;
    }
    
    private boolean checkVertical(int row, int col)
    {
        int count;
        String check = xoButtons[row][col].getText();
        
        count = checkUp(row, col, check, -1) + checkDown(row, col, check, -1);
        
        return count >= 4;
    }
    
    private boolean checkDiagonal(int row, int col)
    {
        int count;
        String check = xoButtons[row][col].getText();
        
        count = checkLeftSideUp(row, col, check, -1) + checkRightSideDown(row, col, check, -1);
        if (count >= 4)
        {
            return true;
        }
        
        count = checkRightSideUp(row, col, check, -1) + checkLeftSideDown(row, col, check, -1);
        return count >= 4;
    }

    private int checkLeft(int row, int col, String check, int count)
    {
        if (count == 5 || xoButtons[row][col].getText().equals(check) == false)
        {
            return count;
        }

        if (col == 0)
        {
            if (xoButtons[row][col].getText().equals(check))
            {
                return count + 1;
            }
            return count;
        }
        
        return checkLeft(row, col - 1, check, count + 1);
    }

    private int checkRight(int row, int col, String check, int count)
    {
        if (count == 5 || xoButtons[row][col].getText().equals(check) == false)
        {
            return count;
        }

        if (col == 9)
        {
            if (xoButtons[row][col].getText().equals(check))
            {
                return count + 1;
            }
            return count;
        }
        
        return checkRight(row, col + 1, check, count + 1);
    }

    
    private int checkUp(int row, int col, String check, int count)
    {
        if (count == 5 || xoButtons[row][col].getText().equals(check) == false)
        {
            return count;
        }

        if (row == 0)
        {
            if (xoButtons[row][col].getText().equals(check))
            {
                return count + 1;
            }
            return count;
        }
        
        return checkUp(row - 1, col, check, count + 1);
    }
    
    private int checkDown(int row, int col, String check, int count)
    {
        if (count == 5 || xoButtons[row][col].getText().equals(check) == false)
        {
            return count;
        }

        if (row == 9)
        {
            if (xoButtons[row][col].getText().equals(check))
            {
                return count + 1;
            }
            return count;
        }
        
        return checkDown(row + 1, col, check, count + 1);
    }
    
    private int checkLeftSideUp(int row, int col, String check, int count)
    {
        if (count == 5 || xoButtons[row][col].getText().equals(check) == false)
        {
            return count;
        }

        if (row == 0 || col == 0)
        {
            if (xoButtons[row][col].getText().equals(check))
            {
                return count + 1;
            }
            return count;
        }
        
        return checkLeftSideUp(row - 1, col - 1, check, count + 1);
    }
    
    private int checkLeftSideDown(int row, int col, String check, int count)
    {
        if (count == 5 || xoButtons[row][col].getText().equals(check) == false)
        {
            return count;
        }

        if (row == 9 || col == 0)
        {
            if (xoButtons[row][col].getText().equals(check))
            {
                return count + 1;
            }
            return count;
        }
        
        return checkLeftSideDown(row + 1, col - 1, check, count + 1);
    }
    
    private int checkRightSideUp(int row, int col, String check, int count)
    {
        if (count == 5 || xoButtons[row][col].getText().equals(check) == false)
        {
            return count;
        }

        if (row == 0 || col == 9)
        {
            if (xoButtons[row][col].getText().equals(check))
            {
                return count + 1;
            }
            return count;
        }
        
        return checkRightSideUp(row - 1, col + 1, check, count + 1);
    }
    
    private int checkRightSideDown(int row, int col, String check, int count)
    {
        if (count == 5 || xoButtons[row][col].getText().equals(check) == false)
        {
            return count;
        }

        if (row == 9 || col == 9)
        {
            if (xoButtons[row][col].getText().equals(check))
            {
                return count + 1;
            }
            return count;
        }
        
        return checkRightSideDown(row + 1, col + 1, check, count + 1);
    }
}