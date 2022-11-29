package tictactoe.GUI;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import tictactoe.Database.DatabaseConnection;

public class LoginClass extends DatabaseConnection
{    
    public LoginClass()
    {
        super();
    }
    
    public boolean login(String username, String pwd)
    {
        try
        {
            return checkLoginTable(username, pwd);
        }
        catch (SQLException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Cannot read from database", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
    }
    
    public boolean checkLoginTable(String username, String pwd) throws SQLException
    {
        sql = "SELECT user_id FROM LoginTable WHERE username = ? AND pwd = ?;";
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, pwd);
        rs = ps.executeQuery();
        
        return rs.next();
    }
}
