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
public class SalesAssociate extends LoginAccount {
    private double totalEarnings;
    
    public SalesAssociate(String username, String password){
       super(username,password); 
       totalEarnings = 0;
    }
    
   public double getTotalEarnings(){
       return this.totalEarnings;
   }
   
   public void setTotalEarnings(double totalEarnings){
       this.totalEarnings = totalEarnings;
   }
    public static void printInfo(SalesAssociate sa,Van v, Warehouse warehouseDB, Map<String,BikePart> bpByName) throws IOException{
        ArrayList<Van> fleet = new ArrayList<>();
        Scanner infoScan = new Scanner(System.in);
        System.out.println("Welcome to the Sales Associate panel. Please enter one of the following: vanAccess, sale, logout, or quit.");
        String ans;
        while(infoScan.hasNext()){
            ans = infoScan.next();
            if(ans.equalsIgnoreCase("vanAccess")){
                vanAccess(sa,v,warehouseDB,fleet);
            }
            if (ans.equalsIgnoreCase("sale")){
                sale(warehouseDB,bpByName, sa);
            }
            if (ans.equalsIgnoreCase("Logout")){
                System.out.println("Logging out and returning to main menu...");
            }
            
        }
    }
    public static void vanAccess(SalesAssociate sa, Van v, Warehouse warehouseDB, ArrayList<Van> fleet) throws IOException{
        Scanner vanScan = new Scanner(System.in);
        ArrayList<BikePart> transport = new ArrayList<>();
        ArrayList<BikePart> transport2 = new ArrayList<>();
        System.out.println("Enter the name of the van you'd like to access:");
        String option = vanScan.next();
        int identifier = sa.getUsername().length()-1;
        char charAt = sa.getUsername().charAt(identifier);
        char charAt2 = option.charAt(option.length()-1);
        
        if(charAt == charAt2){
            System.out.println("Welcome to van functions, Sales Associate. Please enter  'VantoVan','WarehousetoVan', 'Instructions' for instructions, or Quit to quit");
            while(vanScan.hasNext()){
                option = vanScan.next();
                if(option.equalsIgnoreCase("Instructions")){
                    System.out.println("Intructions: VantoVan - moves parts from one van to the other" + "\n" + "WarehousetoVan - moves parts from the warehouse to the van"+"\n"+"Quit - update warehouse and quit program.");
                    System.out.println("Please enter the command you'd like to execute.");
                    option = vanScan.next();
                }
                if(option.equalsIgnoreCase("VantoVan")){
                System.out.println("Enter the name of the van you'd like to move parts to:");
                String input1 = vanScan.next();
                System.out.println("Enter the name of the van you'd like to take parts from:");
                String input2 = vanScan.next();
                Van van1 = searchFleet(input1,fleet);
                Van van2 = searchFleet(input2,fleet);

                if (van1 != null && van2 != null){

                    int numPartsVan2 =van2.getWarehouse().size();
                    int numPartsVan1= van1.getWarehouse().size();

                    van1.vanToVanComparison(van1,van2, transport,numPartsVan2);
                    van1.vanUpdateWarehouse(van1,transport, numPartsVan1);
                    van1.printOutVanWarehouse(van1);
                    van2.printOutVanWarehouse(van2);
                    System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                    option = vanScan.next();  
                }
            }
                if (option.equalsIgnoreCase("warehousetovan")){
                System.out.println("Enter the name of the van you'd like to move parts to:");
                String input1 = vanScan.next();
                Van van1 = searchFleet(input1,fleet);
                if (van1 != null){
                    int numPartsVan = van1.getNumParts();
                    van1.mainToVanComparison(van1,warehouseDB,transport2);
                    van1.updateVanhouseDB(van1,transport2, numPartsVan);
                    van1.printOutVanWarehouse(van1);
                    }
                }
            }
        }
                else{
                    System.out.println("Van name does not match with your identifier. Please try again");
                    option = vanScan.next();
        }
    }
    
    
    public static void sale(Warehouse w, Map<String,BikePart> bpByNameMap, SalesAssociate sa) throws IOException{
        Scanner saleScan = new Scanner(System.in);
        ArrayList<BikePart> selling = new ArrayList<>();
        int warehouseNumParts;
        warehouseNumParts = w.getNumParts();
        System.out.println("How many parts would you like to sell?");
        int toSell = saleScan.nextInt();
        
        for(int i=0; i<toSell;i++){
            System.out.println("Enter part name:");
            String tempName = saleScan.next();
            System.out.println("Enter the quantity to sell:");
            int tempQuant = saleScan.nextInt();
            
            BikePart temp;
            boolean done = false;
            if(done == false){
                for(int j =0; j <= w.getWarehouse().size()-1; j++){
                    if(tempName.equalsIgnoreCase(w.getWarehouse().get(j).getPartName()))
                    {
                    if(tempQuant > w.getWarehouse().get(j).getQuantity()){
                        tempQuant = w.getWarehouse().get(j).getQuantity();
                    }
                    temp = new BikePart(tempName,w.getWarehouse().get(j).getPartNum(),w.getWarehouse().get(j).getListPrice(),w.getWarehouse().get(j).getSalePrice(),w.getWarehouse().get(j).getSaleStatus(),tempQuant);
                    selling.add(temp);
                    w.sell(warehouseNumParts, w, tempName, tempQuant);
                        
                    done = true;
                    }
                    else if(j==w.getWarehouse().size()-1 && done==false){
                    System.out.println("Part is not in warehouse."); 
                    //temp = null;
                    }
                }
            }
        }
        
        System.out.println("Parts have been removed from Warehouse and saes have been credited to "+ sa.getUsername() +". Preparing Sales Invoice:");
        SalesInvoice invoice = new SalesInvoice(selling);
        double totalCost = 0;
        for(int i=0;i < invoice.getInvoice().size(); i++){
        if(invoice.getInvoice().get(i).getSaleStatus() == true){
                totalCost = totalCost + invoice.getInvoice().get(i).getQuantity() * invoice.getInvoice().get(i).getSalePrice();
            }
            else{
                totalCost = totalCost + invoice.getInvoice().get(i).getQuantity() * invoice.getInvoice().get(i).getListPrice();
            }
        }
        sa.setTotalEarnings(totalCost);
        invoice.printSalesInvoice(invoice);
        
        if (selling.size() == 0){
            System.out.println("No parts that were entered exist in the Warehouse, an Invoice will not be printed. Returning to main menu.");
        }
                }
    public static Van searchFleet(String input,ArrayList<Van> fleet){
        Van van = new Van();
        for(int i=0; i < fleet.size(); i++){
            if(input.equals(fleet.get(i).getVanName())){
                return van = fleet.get(i);
            }
        }
        System.out.println("Van not found. Please try again.");
        return null;
    }
}




