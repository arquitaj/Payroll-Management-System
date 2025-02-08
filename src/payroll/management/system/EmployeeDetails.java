/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payroll.management.system;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    protected Boolean isLogin = false;
    protected Boolean isLogout = false;
    protected String date;
    
    EmployeeDetails(){
        
    }
    EmployeeDetails(String employeeID, String path){
        this.employeeID = employeeID;
        this.path = path;  
    }
    
    //To get all data from CSV Depending to the CSV file path
    public void allEmployeeDetails(){
        String line; 
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            while ((line = reader.readLine()) != null){
                String[] datas = line.split(",");
                ArrayList<String> row = new ArrayList<>();
                row.addAll(Arrays.asList(datas));
                dataList.add(row);
            } 
            reader.close();
        } catch (IOException ex){
            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //To get the employee name currently login
    String getEmployeeName(){
        for(int i=1; i<this.dataList.size(); i++){
            if(this.dataList.get(i).get(0).equals(this.employeeID)){
                this.firstName = this.dataList.get(i).get(1);
                this.lastName = this.dataList.get(i).get(2);
            }
        }
         return firstName+" "+lastName;     
    }
    //To return all Data available
    ArrayList<ArrayList<String>> getDataList(){
        return this.dataList;
    }
    
    //To check the Employees login if already login or not
    public void checkLogin(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = dateFormat.format(date);
        
        for(int i=1; i<this.dataList.size(); i++){
            if(this.dataList.get(i).size() > 2){
                if(this.dataList.get(i).get(0).equals(this.employeeID) && this.dataList.get(i).get(1).equals(this.date) && !this.dataList.get(i).get(2).equals("")){
                    this.isLogin = true;
                    break;
                }
            }
        }
    }
    
     public void checkLogout(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = dateFormat.format(date);
        
        for(int i=1; i<this.dataList.size(); i++){
            if(this.dataList.get(i).size() > 3){
                if(this.dataList.get(i).get(0).equals(this.employeeID) && this.dataList.get(i).get(1).equals(this.date) && !this.dataList.get(i).get(3).equals("")){
                    this.isLogout = true;
                    break;
                }
            }
        }
    }
     
    public void EmployeeLogin(){ 
       LocalTime time = LocalTime.now();
       String timeLogin = time.getHour()+":"+time.getMinute();
             
        if(isLogin){
            JOptionPane.showMessageDialog(null, "You are already time-in!");
        }else{
              File file = new File(this.path);
              try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))) {
                  
                    if(file.length() > 0 ){
                        writer.newLine();
                    }
                  writer.write(this.employeeID+","+this.date+","+timeLogin);
                  writer.close();

                JOptionPane.showMessageDialog(null, "Successfuly Time-in");
            }catch (IOException e) {
                e.printStackTrace();
              }
        }  
    }
    
    public void EmployeeLogout(){
       LocalTime time = LocalTime.now();
       String timeLogout = time.getHour()+":"+time.getMinute();
       if(isLogout){
           JOptionPane.showMessageDialog(null, "You are already time-out");
       }else{
            if(isLogin){
              for(int i=1; i<this.dataList.size(); i++){
                  if(this.dataList.get(i).get(0).equals(this.employeeID) && this.dataList.get(i).get(1).equals(this.date)){
                      this.dataList.get(i).add(timeLogout);
                  }
              }
              System.out.print(this.dataList);
            }else{
                 String[] datas = {this.employeeID, this.date," ",timeLogout};
                 ArrayList<String> row = new ArrayList<>();
                 row.addAll(Arrays.asList(datas));
                 dataList.add(row);
                 System.out.print(this.dataList);
            }
            try(BufferedWriter writer = new BufferedWriter (new FileWriter (this.path))){
                for(int i=0; i<this.dataList.size(); i++){
                    writer.write(this.dataList.get(i).get(0)+","+this.dataList.get(i).get(1)+","+this.dataList.get(i).get(2)+","+this.dataList.get(i).get(3));
                    if(i<this.dataList.size()-1)
                        writer.newLine();
                }
                writer.close();
                JOptionPane.showMessageDialog(null, "Successfuly Time-Out!");
            }catch(IOException e){
                e.printStackTrace();
            }
       }
    }
}
    
