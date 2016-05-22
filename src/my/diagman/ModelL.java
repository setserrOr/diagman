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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author orestes
 */
public class ModelL
{
    // Array list for storing the IDs
    ArrayList idArray = new ArrayList();
    
    Statement statement;
    ResultSet rs;
    
    public ModelL(Connection con)
    {
        try
        {
            // Create statement object to run SQL statements
            statement = con.createStatement();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(ModelL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    /**
     *
     * @return the model for a jList object
     */
    public DefaultListModel createListModel()
    {
        
        DefaultListModel DML = new DefaultListModel();
        try
        {
            // Input the SQL query into a string. Make sure that the result set is going to be in alphabetical order
            String sql = "select patient_id, last_name, first_name from patients ORDER BY last_name";
            rs = statement.executeQuery(sql);
            idArray.clear();

            while (rs.next())
            {
                // Use result set to get last and first name
                String lname = rs.getString("last_name");
                String fname = rs.getString("first_name");
                

                // use result set to get the patient id
                int temp_id = rs.getInt("patient_id");
                
                // Save the value of an id to the id array
                idArray.add(temp_id);
                
                System.out.println("THIS IS THE ARRAYLIST" + idArray);
                // Add elements into the model to be used by the jList
                DML.addElement(lname + " " + fname);
                

            }
            rs.close();

        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(ModelL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DML;
    }
    
    /**
     *
     * @param index takes the index of the list that contains the IDs
     * @return returns the patient_id in the current position
     */
    public int getId(int index)
    {
        
        return (Integer) idArray.get(index);
        
    }
    
    /**
     * Deletes completely an entry that was selected by the index
     * @param index the position of the entry to delete
     */
    public void deleteSelectedEntry(int index)
    {
            String sql = "delete from patients where patient_id=" + index;
            
        try
        {
            statement.execute(sql);
        } catch (SQLException ex)
        {
            Logger.getLogger(ModelL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
