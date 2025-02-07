/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payroll.management.system;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paul
 */
public class HumanResource extends EmployeeDetails {
//    private ArrayList<ArrayList<String>> dataList;
    
   HumanResource(String employeeID, String path){
       super(employeeID, path);
   }
   
   DefaultTableModel TableData(ArrayList<ArrayList<String>> dataList, JTable jTableEmployeeList){
      DefaultTableModel model = (DefaultTableModel) jTableEmployeeList.getModel();
      Object rowData[] = new Object [5];
      for(int row=1; row<dataList.size(); row++){
        rowData[0] = dataList.get(row).get(0);
        rowData[1] = dataList.get(row).get(2);
        rowData[2] = dataList.get(row).get(1);
        rowData[3] = dataList.get(row).get(11);
        rowData[4] = dataList.get(row).get(13);
        model.addRow(rowData);
        }
      return model;
   }
   
}
