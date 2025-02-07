/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payroll.management.system;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul
 */
public class EmployeeDetails {
    private String[] values;
    private String employeeID="";
    private String path="";
    private String firstName, lastName;
    private ArrayList<ArrayList<String>> dataList = new ArrayList<>();
    
    EmployeeDetails(){
        
    }
    EmployeeDetails(String employeeID, String path){
        this.employeeID = employeeID;
        this.path = path;  
    }
    
    public void allEmployeeDetails(){
        String line; 
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            while ((line = reader.readLine()) != null){
                String[] datas = line.split(",");
                ArrayList<String> row = new ArrayList<>();
                row.addAll(Arrays.asList(datas));
                dataList.add(row);
            } 
        } catch (IOException ex){
            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    String getEmployeeName(){
        for(int i=1; i<this.dataList.size(); i++){
            if(this.dataList.get(i).get(0).equals(this.employeeID)){
                this.firstName = this.dataList.get(i).get(1);
                this.lastName = this.dataList.get(i).get(2);
            }
//            System.out.println(dataList.get(i));
        }
         return firstName+" "+lastName;     
    }
    
    ArrayList<ArrayList<String>> getDataList(){
        return this.dataList;
    }
}
