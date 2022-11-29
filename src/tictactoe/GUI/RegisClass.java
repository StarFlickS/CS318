package tictactoe.GUI;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import tictactoe.Database.DatabaseConnection;

public class RegisClass extends DatabaseConnection
{
    public RegisClass()
    {
        super();
    }
    
    public boolean register(String username, String pwd)
    {
        try
        {
            insertIntoLoginTable(username, pwd);
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Cannot insert into database", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void insertIntoLoginTable(String username, String pwd) throws SQLException
    {
        sql = "INSERT INTO LoginTable (username, pwd) VALUES (?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, pwd);
        ps.executeUpdate();
    }
    
    public boolean checkUsernameExist(String username) throws SQLException
    {
        sql = "SELECT * FROM LoginTable WHERE username = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery();
        
        return rs.next();  
    }
}
