/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cpscproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kerri
 */
public class SystemAdministrator extends LoginAccount {
    //Person admin;
    //private String adminUsername = "admin";
    //private String adminPassword = "nimda";
    public SystemAdministrator(){
      //super(adminUsername,adminPassword);
      //adminUsername = "admin";
      //adminPassword = "nimda";
    }
    
    public SystemAdministrator(String username,String password){
     super(username,password);
    }
    public static void printInfo(ArrayList<OfficeManager> officeManagers, ArrayList<SalesAssociate> salesAssociates, ArrayList<WarehouseManager> warehouseManagers, Map<String,WarehouseManager> wmByUser, Map<String,OfficeManager> omByUser, Map<String,SalesAssociate> saByUser, ArrayList<Van> fleet) throws IOException{
     Scanner infoScan = new Scanner(System.in);
        System.out.println("Welcome to the System Admin panel. Please enter one of the following: CreateStaff, ResetPassword, Logout, or Quit.");
        String ans;
        while(infoScan.hasNext()){
            ans = infoScan.next();
            if(ans.equalsIgnoreCase("CreateStaff")){
                System.out.println("Are you creating a SalesAssociate, WarehouseManager, or OfficeManager?");
                ans = infoScan.next();
                if(ans.equalsIgnoreCase("SalesAssociate")){
                    createSalesAssociate(fleet,salesAssociates);
                }
                else if(ans.equalsIgnoreCase("OfficeManager")){
                    createOfficeManager(officeManagers);
                }
                else if(ans.equalsIgnoreCase("WarehouseManager")){
                    createWarehouseManager(warehouseManagers);
                }
                System.out.println("Enter another command or 'quit'");
                ans = infoScan.next();
                if(ans.equalsIgnoreCase("quit")){
                    
                }
            }
            else if (ans.equalsIgnoreCase("ResetPassword")){
                resetPassword(salesAssociates,warehouseManagers,officeManagers,wmByUser,omByUser,saByUser);
            }
            else if (ans.equalsIgnoreCase("Logout")){
                System.out.println("Logging out and returning to main menu...");
                 
            }
            else if(ans.equalsIgnoreCase("quit")){
                
            }
            
        }
    }
    public static void resetPassword(ArrayList<SalesAssociate> saList, ArrayList<WarehouseManager> wmList, ArrayList<OfficeManager> omList, Map<String,WarehouseManager> wmByUser, Map<String,OfficeManager> omByUser, Map<String,SalesAssociate> saByUser){
        Scanner resetIn = new Scanner(System.in);
        System.out.println("Is this staff member a WarehouseManager, OfficeManager or SalesAssociate?");
        String ans = resetIn.next();
        
        if(ans.equalsIgnoreCase("SalesAssociate")){
            System.out.println("Please enter the username of the team member whose password needs to be reset:");
            ans = resetIn.next();
            SalesAssociate temp = searchStaffSA(ans,saList);
            System.out.println("Enter new password:");
            ans = resetIn.next();
            SalesAssociate update = new SalesAssociate(temp.getUsername(), ans);
            saList.remove(temp);
            saByUser.remove(temp.getUsername());
            saList.add(update);
            saByUser.put(update.getUsername(),update);
            System.out.println("New password for "+ temp.getUsername()+ " is "+ ans);
        }
        
        
    }
    public static void createSalesAssociate(ArrayList<Van> fleet, ArrayList<SalesAssociate> salesAssociates) throws IOException{
        Scanner saIn = new Scanner(System.in);
        System.out.println("Enter username for new Sales Associate:");
        String username = saIn.next();
        System.out.println("Enter password for new Sales Associate:");
        String password = saIn.next();
        SalesAssociate sa = new SalesAssociate(username,password);
        //createVanSA(fleet);
        salesAssociates.add(sa);
        System.out.println("Sales Associate account " + username + "  has been created");
        //return sa;
    }
    public static void createOfficeManager(ArrayList<OfficeManager> officeManagers){
        Scanner omIn = new Scanner(System.in);
         System.out.println("Enter username for new Office Manager:");
        String username = omIn.next();
        System.out.println("Enter password for new Office Manager:");
        String password = omIn.next();
        OfficeManager om = new OfficeManager(username,password);
        officeManagers.add(om);
        //return om;
    }
    public static void createWarehouseManager(ArrayList<WarehouseManager> warehouseManagers){
        Scanner wmIn = new Scanner(System.in);
         System.out.println("Enter username for new Warehouse Manager:");
        String username = wmIn.next();
        System.out.println("Enter password for new Warehouse Manager:");
        String password = wmIn.next();
        WarehouseManager wm = new WarehouseManager(username,password);
        warehouseManagers.add(wm);
     //   return wm;
    }
    
    public static LoginAccount searchStaff(String input,ArrayList<LoginAccount> list){
        for(int i=0; i <list.size(); i++){
            if(input.equals(list.get(i).getUsername())){
                return list.get(i);
            }
        }
        System.out.println("Van not found. Please try again.");
        return null;
    }
    
    public static SalesAssociate searchStaffSA(String input,ArrayList<SalesAssociate> list){
        for(int i=0; i <list.size(); i++){
            if(input.equals(list.get(i).getUsername())){
                return list.get(i);
            }
        }
        System.out.println("Van not found. Please try again.");
        return null;
    }
}
