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


public class PatientInfo
{
    private Statement statement;
    private ResultSet rs;
    
    private String lName, fName, genderstr, occupation, nationality, patientId;
    private Boolean smoker, allergies;
    private String bDate;
    private int contact_id, gender;
    
    /**
     *
     * @param con is the connection statement input 
     */
    public PatientInfo(Connection con) 
    {
        // Initialize variables
        lName = null;
        fName = null;
        gender = -1;
        occupation = null;
        bDate = null;
        nationality = null;
        patientId = null;
        
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
        patientId = Integer.toString(idNum);
        System.out.println(patientId);
        try
        {
            
            
            // SQL statement that returns the patient info for a specific patient
            String sql = "select * from patients where patient_id=" + patientId;
            
            rs = statement.executeQuery(sql);
            while (rs.next())
            {
                // Use result set to get last and first name
                lName = rs.getString("last_name");
                fName = rs.getString("first_name");
                
                bDate = rs.getString("birthdate");
                genderstr = rs.getString("gender");
                occupation = rs.getString("occupation");
                smoker = rs.getBoolean("smoker");
                allergies = rs.getBoolean("allergies");
                contact_id = rs.getInt("contact_details");
                nationality = rs.getString("nationality");
                
            }
            rs.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(PatientInfo.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
// Get Methods for every variable
// -------------------------------------------    
    public String getLName()
    {        
        return lName;
    }
    
    public String getFName()
    {
        return fName;
    }
    
    public String getBDate()
    {
        return bDate;
    }
    
    public int getGender()
    {
        System.out.println("gender: " + gender);
        if (genderstr.equals("M"))
            gender = 0;
        else if (genderstr.equals("F"))
            gender = 1;
        else
            gender = -1;
       return gender;
    }

    
    public String getOccupation()
    {
        return occupation;
    }
    
    public Boolean getSmoker()
    {
        return smoker;
    }
    
    public Boolean getAllergies()
    {
        return allergies;
    }
    
    public int getContact()
    {
        return contact_id;
    }
    
    public String getNationality()
    {
        return nationality;
    }

// -------------------------------------------------------
    
// Edit methods for the instance variables
// -------------------------------------------------------

public void setTextFields(String firstName, String lastName, String occ, String nat)
{
    fName = firstName;
    lName = lastName;
    occupation = occ;
    nationality = nat;
}

public void setCheckboxes(boolean smk, boolean aller)
{
    smoker = smk;
    allergies = aller;
}

public void setCombo(int idx)
{
    gender = idx;
    
}

public void setBirthDate(String date)
{
    System.out.println(bDate);
    bDate = date;
    System.out.println(bDate);
}

    /**
     * Updates the patient info tab with the instance variables
     */
    public void updateDatabase()
    {
        // Set the update query in  a string
        String sql1 = "update patients set first_name='" + fName + "', last_name='" + lName + "' ,occupation='" + occupation + "', gender='" + genderstr + "', nationality='" + nationality + "' where patient_id=" + patientId;
        String sql2 = "update patients set birthdate='" + bDate + "', smoker='" + smoker.toString() + "' ,allergies='" + allergies.toString() + "' where patient_id=" + patientId;
        System.out.println(sql1);
        System.out.println(sql2);
        
        try
        {
            // Execute the query
            statement.execute(sql1);
            statement.execute(sql2);
        } catch (SQLException ex)
        {
            Logger.getLogger(PatientInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void createNewPatientInfo()
    {
        String sql = "insert into patients (first_name, last_name, occupation, gender, birthdate, smoker, allergies, contact_details, nationality) values ('first_name', 'last_name', null, '', '2000-01-01', false, false, null, null)";
        try
        {
            // Execute the insert statement
            statement.execute(sql);
            
           // statement.closeOnCompletion();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(PatientInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}