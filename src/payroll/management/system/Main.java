/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payroll.management.system;

/**
 *
 * @author Paul
 */
public class Main {
    
    public static void main (String [] args ){
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
          HumanResourceGUI HR = new HumanResourceGUI();
          HR.setVisible(true);
    }
}
