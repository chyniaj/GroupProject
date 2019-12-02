/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cpscproject;

import static com.mycompany.cpscproject.LoginAccount.readInOM;
import static com.mycompany.cpscproject.LoginAccount.readInSA;
import static com.mycompany.cpscproject.LoginAccount.readInWM;
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
           //creation of arraylists
           ArrayList<Van> fleet = new ArrayList<>();
           ArrayList<SalesAssociate> salesAssociates = new ArrayList<>();
           ArrayList<OfficeManager> officeManagers = new ArrayList<>();
           ArrayList<WarehouseManager> warehouseManagers = new ArrayList<>();
           //reading in of staff files
           readInSA(salesAssociates);
           readInOM(officeManagers);
           readInWM(warehouseManagers);
           //move to main method
           mainLogin(admin, warehouseDB,inventory,fleet, salesAssociates, officeManagers, warehouseManagers);
    }
    
    public static void mainLogin(SystemAdministrator admin, Warehouse warehouseDB, Inventory inventory, ArrayList<Van> fleet, ArrayList<SalesAssociate> salesAssociates,ArrayList<OfficeManager> officeManagers, ArrayList<WarehouseManager> warehouseManagers ) throws IOException{
        //creation of all maps
        Map<String,SalesAssociate> saByUser = new HashMap<>();
        Map<String,WarehouseManager> wmByUser = new HashMap<>();
        Map<String,OfficeManager> omByUser = new HashMap<>();
        Map<String,BikePart> bpByName = new HashMap<>();
        //arraylists to maps
        toMap(warehouseDB,bpByName);
        toMapWM(warehouseManagers,wmByUser);
        toMapOM(officeManagers,omByUser);
        toMapSA(salesAssociates, saByUser);
        
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
                admin.printInfo(officeManagers,salesAssociates,warehouseManagers,wmByUser,omByUser,saByUser,fleet);
                System.out.println("You have returned to main menu");
                }
                else{
                    System.out.println("Log-in attempt failed. Please try again.");
                    System.out.println("Welcome to the Inventory, are you a System Admin, Sales Associate, Office Manager, or Warehouse Manager?");
                    input = start.next();
                }
            }
            else if (input.equalsIgnoreCase("SalesAssociate")){
                System.out.println("Hello, SalesAssociate. Please login:");
                System.out.println("Username:");
                String usernameTemp = start.next();
                SalesAssociate toLogin = saByUser.get(usernameTemp);
                System.out.println("Password:");
                String passwordTemp = start.next();
                usernameTemp.trim();
                passwordTemp.trim();
                if(toLogin.logIn(usernameTemp, passwordTemp).equals("true")){
                toLogin.printInfo(toLogin, warehouseDB, bpByName, salesAssociates);
            }
                else{
                    System.out.println("Log-in attempt failed. Please try again.");
                    System.out.println("Welcome to the Inventory, are you a System Admin, Sales Associate, Office Manager, or Warehouse Manager?");
                    input = start.next();
                }
            }
            else if(input.equalsIgnoreCase("WarehouseManager")){
               System.out.println("Hello, WarehouseManager. Please login:");
                System.out.println("Username:");
                String usernameTemp = start.next();
                WarehouseManager toLogin = wmByUser.get(usernameTemp);
                System.out.println("Password:");
                String passwordTemp = start.next();
                usernameTemp.trim();
                passwordTemp.trim();
                if(toLogin.logIn(usernameTemp, passwordTemp).equals("true")){
                    //toLogin.printInfo()
                } 
                else{
                    System.out.println("Log-in attempt failed. Please try again.");
                    System.out.println("Welcome to the Inventory, are you a System Admin, Sales Associate, Office Manager, or Warehouse Manager?");
                    input = start.next();
                }
            }
            else if(input.equalsIgnoreCase("OfficeManager")){
                System.out.println("Hello, OfficeManager. Please login:");
                System.out.println("Username:");
                String usernameTemp = start.next();
                OfficeManager toLogin = omByUser.get(usernameTemp);
                System.out.println("Password:");
                String passwordTemp = start.next();
                usernameTemp.trim();
                passwordTemp.trim();
                if(toLogin.logIn(usernameTemp, passwordTemp).equals("true")){
                    //toLogin.printInfo()
                } 
                else{
                    System.out.println("Log-in attempt failed. Please try again.");
                    System.out.println("Welcome to the Inventory, are you a System Admin, Sales Associate, Office Manager, or Warehouse Manager?");
                    input = start.next();
                }
            }
    }
    }
    
    public static void toMap(Warehouse w, Map<String,BikePart> bpByName){
       for(int i=0; i< w.getWarehouse().size(); i++){
           String tempName = w.getWarehouse().get(i).getPartName();
           BikePart temp = w.getWarehouse().get(i);
           bpByName.put(tempName,temp);
       }
    }
    public static void toMapWM(ArrayList<WarehouseManager> list, Map<String,WarehouseManager> wmByUser){
       for(int i=0; i< list.size(); i++){
           String tempName = list.get(i).getUsername();
           WarehouseManager temp = list.get(i);
           wmByUser.put(tempName,temp);
}
    }
       public static void toMapOM(ArrayList<OfficeManager> list, Map<String,OfficeManager> omByUser){
       for(int i=0; i< list.size(); i++){
           String tempName = list.get(i).getUsername();
           OfficeManager temp = list.get(i);
           omByUser.put(tempName,temp);
}
    }
    public static void toMapSA(ArrayList<SalesAssociate> saList, Map<String,SalesAssociate> saByUser){
       for(int i=0; i< saList.size(); i++){
           String tempName = saList.get(i).getUsername();
           SalesAssociate temp = saList.get(i);
           saByUser.put(tempName,temp);
}
    }
}


