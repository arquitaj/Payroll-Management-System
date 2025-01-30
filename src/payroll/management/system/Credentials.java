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
abstract class transactions{
    abstract String checkCredentials();
}
public class Credentials extends transactions {
    private String email, password;
    String role="";
    
    Credentials(String email, String password){
        this.email = email;
        this.password = password;
    }
    
 
    @Override
    String checkCredentials(){
        String path = "MotorPHDatabase//CredentialsDatabase.csv";
        String line;
        
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
        while ((line = reader.readLine()) != null){
            String[] values = line.split(",");
              
            if (values.length == 4) {
                String csvEmail = values[1].trim();
                String csvPassword = values[2].trim();

                if (csvEmail.equals(this.email) && csvPassword.equals(this.password)) {
                    this.role = values[3].trim();
                }
            }
        } 
      } catch (IOException ex) {
            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return this.role;
    }
}
