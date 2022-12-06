package tictactoe.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisPage extends JFrame implements ActionListener
{
    private final int WIDTH = 900;
    private final int HEIGHT = 500;
    
    private final JFrame regisFrame;
    private final JTextField usernameEnt;
    private final JPasswordField passwordEnt;
    private final JPasswordField cfPasswordEnt;
    
    private final JButton cancelBtn;
    private final JButton summitBtn;
    
    
    public RegisPage()
    {
        regisFrame = new JFrame("Register Page");
        regisFrame.setResizable(false);
        regisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        regisFrame.setSize(WIDTH, HEIGHT);
        regisFrame.getContentPane().setBackground(new Color(0x78CEF2));
        regisFrame.setLayout(new BorderLayout());
        regisFrame.setLocationRelativeTo(null);
        regisFrame.setVisible(true);
        
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.setPreferredSize(new Dimension(100, 100));
        top.setBackground(new Color(0x78CEF2));    
        regisFrame.add(top, BorderLayout.NORTH);
        
        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(3, 0));
        middle.setPreferredSize(new Dimension(100, 300));
        middle.setBackground(new Color(0x78CEF2));
        
        JPanel middlePanel1 = new JPanel();
        middlePanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        middlePanel1.setBackground(new Color(0x78CEF2));
        middle.add(middlePanel1);
        
        JPanel middlePanel2 = new JPanel();
        middlePanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
        middlePanel2.setBackground(new Color(0x78CEF2));
        middle.add(middlePanel2);

        
        JPanel middlePanel3 = new JPanel();
        middlePanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        middlePanel3.setBackground(new Color(0x78CEF2));
        middle.add(middlePanel3);

        regisFrame.add(middle, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 40));
        bottom.setPreferredSize(new Dimension(100, 100));
        bottom.setBackground(new Color(0x78CEF2));
        regisFrame.add(bottom, BorderLayout.SOUTH);
        
        JLabel header = new JLabel("Register");
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

        JLabel cfPasswordLabel = new JLabel("Confirm Password: ");
        cfPasswordLabel.setForeground(Color.WHITE);
        cfPasswordLabel.setFont(new Font("verdana", Font.PLAIN, 24));
        
        cfPasswordEnt = new JPasswordField();
        cfPasswordEnt.setEchoChar('●');
        cfPasswordEnt.setFont(new Font("verdana", Font.PLAIN, 24));
        cfPasswordEnt.setPreferredSize(new Dimension(250, 40));
        
        middlePanel1.add(usernameLabel);
        middlePanel1.add(usernameEnt);

        middlePanel2.add(passwordLabel);
        middlePanel2.add(passwordEnt);
        
        middlePanel3.add(cfPasswordLabel);
        middlePanel3.add(cfPasswordEnt);
        
        cancelBtn = new JButton();
        cancelBtn.setText("Cancel");
        cancelBtn.setBackground(new Color(0x098BC2));
        cancelBtn.setOpaque(true);
        cancelBtn.setBorderPainted(false);
        cancelBtn.addActionListener(this);
        bottom.add(cancelBtn);
        
        summitBtn = new JButton();
        summitBtn.setText("Summit");
        summitBtn.setBackground(new Color(0x098BC2));
        summitBtn.setOpaque(true);
        summitBtn.setBorderPainted(false);
        summitBtn.addActionListener(this);
        bottom.add(summitBtn); 
    }

        
    private void summitBtnClicked()
    {
        RegisClass regis = new RegisClass();
        if (regis.checkInputIfEmtpy(usernameEnt.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please enter your username", "Warning", JOptionPane.WARNING_MESSAGE);
            usernameEnt.requestFocus();
        }
        else
        {
            String passText = new String(passwordEnt.getPassword());
            if (regis.checkInputIfEmtpy(passText))
            {
                JOptionPane.showMessageDialog(null, "Please enter your Password", "Warning", JOptionPane.WARNING_MESSAGE);
                passwordEnt.requestFocus();
            }
            else
            {
                String cfPassText = new String(cfPasswordEnt.getPassword());
                if (regis.checkInputIfEmtpy(cfPassText))
                {
                    JOptionPane.showMessageDialog(null, "Please confirm your Password", "Warning", JOptionPane.WARNING_MESSAGE);
                    cfPasswordEnt.requestFocus();
                }
                else 
                {
                    try
                    {
                        if (regis.checkUsernameExist(usernameEnt.getText()))
                        {
                            JOptionPane.showMessageDialog(null, "Username already exist", "Warning", JOptionPane.WARNING_MESSAGE);
                            regis.closeDatabase();
                            usernameEnt.requestFocus();
                        }
                        else if (passText.equals(cfPassText))
                        {
                            if (regis.register(usernameEnt.getText(), passText))
                            {
                                regis.closeDatabase();
                                JOptionPane.showMessageDialog(null, "Registration Successfully.", "Registration Successfully.", JOptionPane.INFORMATION_MESSAGE);
                                regisFrame.dispose();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Cannot make Registration.", "Registration Unsuccessfully.", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Password and Confirm Password are not match", "Warning.", JOptionPane.WARNING_MESSAGE);
                            cfPasswordEnt.requestFocus();
                        }
                    }
                    catch (SQLException e)
                    {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "Cannot read from database", "Warning", JOptionPane.WARNING_MESSAGE);

                    }
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == summitBtn)
        {
            summitBtnClicked();
        }
        else
        {
            regisFrame.dispose();
        }
    }
} 