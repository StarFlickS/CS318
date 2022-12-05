package tictactoe.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection
{
    public Connection conn;
    private String url = "jdbc:sqlite:src/tictactoe/Database/database.db";
    protected String sql;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    public DatabaseConnection()
    {
        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Cannot connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public boolean checkInputIfEmtpy(String input)
    {
        return input.equals("");
    }    
    
    public void closeDatabase()
    {
        try
        {
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Cannot close the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
