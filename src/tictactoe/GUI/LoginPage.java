package tictactoe.GUI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame implements ActionListener
{
    private final int WIDTH = 900;
    private final int HEIGHT = 500;
    
    private JTextField usernameEnt;
    private JPasswordField passwordEnt;
    
    private JButton loginBtn;
    private JButton regisBtn;
    
    private JFrame loginFrame;
    
    public LoginPage()
    {   
        loginFrame = new JFrame("Login Page");
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(WIDTH, HEIGHT);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.getContentPane().setBackground(new Color(0x78CEF2));
        loginFrame.setLocationRelativeTo(null);
        
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.setPreferredSize(new Dimension(100, 100));
        top.setBackground(new Color(0x78CEF2));
        loginFrame.add(top, BorderLayout.NORTH);

        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(2, 0));
        middle.setPreferredSize(new Dimension(100, 300));
        middle.setBackground(new Color(0x78CEF2));

        JPanel middlePanel1 = new JPanel();
        middlePanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
        middlePanel1.setBackground(new Color(0x78CEF2));
        middle.add(middlePanel1);
        
        JPanel middlePanel2 = new JPanel();
        middlePanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
        middlePanel2.setBackground(new Color(0x78CEF2));
        middle.add(middlePanel2);
        loginFrame.add(middle, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 40));
        bottom.setPreferredSize(new Dimension(100, 100));
        bottom.setBackground(new Color(0x78CEF2));
        loginFrame.add(bottom, BorderLayout.SOUTH);


        JLabel header = new JLabel("Welcome");
        header.setForeground(Color.WHITE);
        header.setFont(new Font("verdana", Font.PLAIN, 48));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setHorizontalTextPosition(JLabel.CENTER);
        top.add(header);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("verdana", Font.PLAIN, 24));
        
        usernameEnt = new JTextField();
        usernameEnt.setFont(new Font("verdana", Font.PLAIN, 24));
        usernameEnt.setPreferredSize(new Dimension(250, 40));

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("verdana", Font.PLAIN, 24));
        
        passwordEnt = new JPasswordField();
        passwordEnt.setEchoChar('●');
        passwordEnt.setFont(new Font("verdana", Font.PLAIN, 24));
        passwordEnt.setPreferredSize(new Dimension(250, 40));

        middlePanel1.add(usernameLabel);
        middlePanel1.add(usernameEnt);

        middlePanel2.add(passwordLabel);
        middlePanel2.add(passwordEnt);
        
        regisBtn = new JButton();
        regisBtn.setText("Register");
        regisBtn.setBackground(new Color(0x098BC2));
        regisBtn.setOpaque(true);
        regisBtn.setBorderPainted(false);
        regisBtn.addActionListener(this);
        bottom.add(regisBtn);

        loginBtn = new JButton();
        loginBtn.setText("Login");
        loginBtn.setBackground(new Color(0x098BC2));
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        loginBtn.addActionListener(this);
        bottom.add(loginBtn);

        loginFrame.setVisible(true);
    }
    
    private void loginBtnClicked()
    {
        LoginClass login = new LoginClass();

        if (login.checkInputIfEmtpy(usernameEnt.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please enter your Username", "Warning", JOptionPane.WARNING_MESSAGE);
            usernameEnt.requestFocus();
        }
        else
        {
            String passText = new String(passwordEnt.getPassword());
            if (login.checkInputIfEmtpy(passText))
            {    
                JOptionPane.showMessageDialog(null, "Please enter your Password", "Warning", JOptionPane.WARNING_MESSAGE);
                passwordEnt.requestFocus();
            }
            else
            {
                if (login.login(usernameEnt.getText(), passText))
                {
                    JOptionPane.showMessageDialog(null, "Login Successfully", "Login Successfully", JOptionPane.INFORMATION_MESSAGE);
                    new TicTacToePage();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Username or Password is incorrect, Please try again.", "Warning", JOptionPane.WARNING_MESSAGE);
                    usernameEnt.setText("");
                    passwordEnt.setText("");
                    usernameEnt.requestFocus();
                }
            }
        } 
    }
    
    private void regisBtnClicked()
    {
        usernameEnt.setText("");
        passwordEnt.setText("");
        usernameEnt.requestFocus();
        new RegisPage();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {   
        if (e.getSource() == loginBtn)
        {
            loginBtnClicked();
        }
        else
        {
            regisBtnClicked();
        }
    }
}
