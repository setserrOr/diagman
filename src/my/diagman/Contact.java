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
public class Contact
{
    String address, phone, mobile, email, city, postalCode, country;
    
    private Statement statement;
    private ResultSet rs;
        
        public Contact(Connection con)
        {
           // Initialize variables
           address = null;
           phone = null;
           mobile = null;
           email = null;
           city = null;
           postalCode = null;
           country = null;
           
           try
        {
            // Create statement object to run SQL statements
            statement = con.createStatement();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(ModelL.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

         public void setIdNum(int idNum) 
        {
     
            try
            {
                String contactId = Integer.toString(idNum);
                System.out.println("contact_id=" + contactId);
                // SQL statement that returns the patient info for a specific patient
                String sql = "select * from contact where contact_id=" + contactId;

                rs = statement.executeQuery(sql);
                while (rs.next())
                {
                    // Use result set to get last and first name
                    address = rs.getString("address");
                    phone = rs.getString("phone");
                    mobile = rs.getString("mobile");
                    city = rs.getString("city");
                    postalCode = rs.getString("postal_code");
                    email = rs.getString("email");
                    country = rs.getString("country");
                }
                rs.close();
            } 
            catch (SQLException ex)
            {
                Logger.getLogger(PatientInfo.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        
}
