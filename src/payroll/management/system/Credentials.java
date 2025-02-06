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
interface transactions{
    abstract void checkCredentials();
    abstract void checkEmployeeDetails();
}
public class Credentials implements transactions {
    private String employeeID = "";
    private String email, password;
    private String role="";
    private String path;
    
    Credentials(){
        
    }
     
    Credentials(String email, String password, String path){
        this.email = email;
        this.password = password;
        this.path = path;
    }
    

    @Override
    public void checkCredentials(){
       
        String line; 
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) 
        {
            while ((line = reader.readLine()) != null)
            {
                String[] values = line.split(",");

                if (values.length == 4) 
                {
                    String csvEmail = values[1].trim();
                    String csvPassword = values[2].trim();

                    if (csvEmail.equals(this.email) && csvPassword.equals(this.password)) 
                    {                  
                        this.employeeID = values[0].trim();
                        this.role = values[3].trim();
                    }
                }
            } 
          } catch (IOException ex) 
            {
                Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Override
    public void checkEmployeeDetails(){
        System.out.print("Something");
    }
    public String getEmployeeID(){
        return this.employeeID; 
    }
    public String getRole(){
        return this.role;
    }
    
}
