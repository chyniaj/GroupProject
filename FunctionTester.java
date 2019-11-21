/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cpscproject;

import static com.mycompany.cpscproject.LoginAccount.hashPassword;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kerri
 */
public abstract class FunctionTester implements Map {
    public static void main(String[] args) throws IOException{
           SystemAdministrator admin = new SystemAdministrator("admin","nimda");
           Inventory inventory = new Inventory();
           Warehouse warehouseDB = new Warehouse();
           Map<String,BikePart> bpByName = new HashMap<>();
           toMap(warehouseDB,bpByName);
           ArrayList<Object> staff = new ArrayList<>();
           
        Scanner start = new Scanner(System.in);
        System.out.println("Welcome to the Inventory, are you a System Admin, Sales Associate, Office Manager, or Warehouse Manager?");
        String input;
        while(start.hasNext()){
            input = start.next();
            if(input.equalsIgnoreCase("SystemAdmin")){
                System.out.println("Hello, Administrator. Please login:");
                System.out.println("Username:");
                String usernameTemp = start.next();
                System.out.println("Password:");
                String passwordTemp = start.next();
                usernameTemp.trim();
                passwordTemp.trim();
                if(admin.logIn(usernameTemp, passwordTemp).equals("true")){
                System.out.println("Please enter the command you'd like to execute, quit, or logout");
                input = start.next();
                if (!(input.equalsIgnoreCase("LogOut"))){
                    if(input.equalsIgnoreCase("CreateStaff")){
                        Scanner createScan = new Scanner(System.in);
                        System.out.println("Which staff member would you like to create?");
                        String newMember = createScan.next();
                        if(newMember.equalsIgnoreCase("SalesAssociate")){
                           staff.add(admin.createSalesAssociate());
                            
                            
                        }
                    }
                 
                }
                else if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    System.exit(0);
                }
                System.out.println("Logging out");
                }
            }
             if(input.equalsIgnoreCase("SalesAssociate")){
                 System.out.println("Testing sale method");
                 SalesAssociate sa = new SalesAssociate("test123","password123");
                 sa.sale(warehouseDB, bpByName);
        }
    }
    }
    public static void toMap(Warehouse w, Map<String,BikePart> bpByName){
       for(int i=0; i< w.getWarehouse().size()-1; i++){
           String tempName = w.getWarehouse().get(i).getPartName();
           BikePart temp = w.getWarehouse().get(i);
           bpByName.put(tempName,temp);
       }
    }
}


