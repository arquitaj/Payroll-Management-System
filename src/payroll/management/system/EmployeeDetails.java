/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payroll.management.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul
 */
public class EmployeeDetails {
    private String[] values;
    private String employeeID;
    private String path;
    private String firstName, lastName;
    
    
    EmployeeDetails(String employeeID, String path){
        this.employeeID = employeeID;
        this.path = path;
        
    }
    
    public void allEmployeeDetails(){
        String line; 
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) 
        {
            while ((line = reader.readLine()) != null)
            {
                this.values = line.split(",");
            } 
          } catch (IOException ex) 
            {
                Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    String getEmployeeName(){
        
         String line; 
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) 
        {
            while ((line = reader.readLine()) != null)
            {
                this.values = line.split(",");
                
                if (this.values.length == 19) 
            {
              String employeeID = values[0].trim();
            
                if (employeeID.equals(this.employeeID)) 
                    {                  
                        this.firstName = values[1].trim();
                        this.lastName = values[2].trim();
                  
                    }
            }
            } 
          } catch (IOException ex) 
            {
                Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
            }
          return firstName+" "+lastName;
    }
}
