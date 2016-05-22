/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.diagman;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orestes
 */
public class Result
{
    // String ;
    
    private Statement statement;
    private ResultSet rs;
        
        public Result(Connection con)
        {
           // Initialize variables
           
           try
        {
            // Create statement object to run SQL statements
            statement = con.createStatement();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(ModelL.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
