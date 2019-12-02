package com.company;

import java.util.*;
import java.io.*;

public class OfficeManager extends LoginAccount {
    public OfficeManager(String username, String password){
        super(username,password);
    }
    public void printInfo(Map<String, BikePart> bpByName, Map<Integer, BikePart> bpByNumber, Map<String, SalesAssociate> saByUser) throws IOException {
        Warehouse warehouseDB = new Warehouse();
        Inventory inventory = new Inventory();
        int warehouseNumParts = inventory.getNumParts();
        Scanner start = new Scanner(System.in);
        System.out.println("Welcome Office Manager! Please enter the command you would like to use or enter 'Instructions' for instructions.");
        while (start.hasNext()) {
            String userInput = start.next();
            String input;

            if (userInput.equalsIgnoreCase("Instructions")) {
                System.out.println("Display - display the part by entering part name or part number" + "\n" +"Order - Order parts needed for warehouse"+ "\n" + "SalesCommission - Paying sales associates"+ "\n" + "Quit- update warehouse and quit program.");
                System.out.println("Disclaimer: all methods work with the data in the warehouse upon startup, meaning, unless you select 'Delivery' before you sort/add/display, you will only be altering/showing data that does NOT include what is in the inventory");
                System.out.println("Please enter the command you would like to execute.");
                input = start.next();
                if (input.equalsIgnoreCase("Quit")) {
                    System.out.println("updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            } else if (userInput.equalsIgnoreCase("Display")) {
                display(bpByName,bpByNumber);

                System.out.println("Command Complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if (input.equalsIgnoreCase("Quit")) {
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            } else if(userInput.equalsIgnoreCase("Order")) {
                orderPart(warehouseDB);

                System.out.println("Command Complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            } else if(userInput.equalsIgnoreCase("SalesCommission")){
                //enter salesCommission method command
                SalesCommission(saByUser);

                System.out.println("Command Complete. Enter another command or enter 'Quit' to quit");
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            } else if(userInput.equalsIgnoreCase("Quit")){
                System.out.println("Updating and exiting...");
                warehouseDB.printOutWarehouseDB(warehouseDB);
                System.exit(0);
            }
        }
    }
    public void display(Map<String,BikePart> bpByPartName, Map<Integer, BikePart> bpByPartNum) throws IOException{
        Warehouse warehouseDB = new Warehouse();
        Inventory inventory = new Inventory();
        int warehouseNumParts = inventory.getNumParts();
        Scanner start = new Scanner(System.in);
        System.out.println("Are you searching for name or number?");
        String input = start.next();
        if(input.equalsIgnoreCase("Name")){
            System.out.println("Enter the name of the part you would like to search:"); //make sure case sensitive names are capitalized
            String name = start.next();
            BikePart bp;
            bp = bpByPartName.get(name);
            bp.printInfo();
        } else if(input.equalsIgnoreCase("Number")){
            System.out.println("Enter the number of the part you would like to search:");
            Integer num = start.nextInt();
            BikePart bp;
            bp = bpByPartNum.get(num);
            bp.printInfo();
        }
    }
    public void orderPart(Warehouse warehouse){
        //look into warehouse
        warehouse.getWarehouse();
        //if part is less than or equal to 2
        //compare parts until one does not match
        //print out which parts needs to be ordered
        for (int i=0; i<warehouse.getWarehouse().size(); i++){
            String name = warehouse.getWarehouse().get(i).getPartName();
            int size = warehouse.getWarehouse().get(i).getQuantity();
            if (size <=2){
                System.out.println(name + " needs to be ordered.");
            }
        }
    }
    public void SalesCommission(Map<String, SalesAssociate> saByUser) {
        Scanner start = new Scanner(System.in);
        System.out.println("Enter the Sales Associate:");
        String input = start.next();
        SalesAssociate temp = saByUser.get(input);
        System.out.println("Enter the start date:");
        String startDate = start.next();
        System.out.println("Enter the end date:");
        String endDate = start.next();
        double totalEarnings = temp.getTotalEarnings();
        double commission = totalEarnings * .15;
        //print out statement that shows the amount that the salesAssociate will receive
        System.out.println(input + "will be paid: " + commission + " for the ending period of" + endDate);
    }
}
