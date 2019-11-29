package com.company;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class WarehouseManager extends LoginAccount {
    public WarehouseManager(String username, String password){
        super(username,password);
    }
    public void printInfo(Map<String, BikePart> bpByPartName, Map<Integer, BikePart> bpByPartNumber) throws IOException {
        Warehouse warehouseDB = new Warehouse();
        Inventory inventory = new Inventory();
        int warehouseNumParts = inventory.getNumParts();
        Scanner start = new Scanner(System.in);
        System.out.println("Welcome Warehouse Manager! Please enter the command you would like to use or enter 'Instructions' for instructions.");
        while (start.hasNext()) {
            String userInput = start.next();
            String input;

            if (userInput.equalsIgnoreCase("Instructions")) {
                System.out.println("Delivery/Update - read in or update the file" + "\n" + "Display - display the part by entering part name or part number" + "\n" + "Quit- update warehouse and quit program.");
                System.out.println("Disclaimer: all methods work with the data in the warehouse upon startup, meaning, unless you select 'Delivery' before you display, you will only be altering/showing data that does NOT include what is in the inventory");
                System.out.println("Please enter the command you would like to execute.");
                input = start.next();
                if (input.equalsIgnoreCase("Quit")) {
                    System.out.println("updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            } else if (userInput.equalsIgnoreCase("Delivery")) {
                inventory.updateWarehouseDB(inventory, warehouseDB, warehouseNumParts);
                System.out.println("Delivery processed. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if (input.equalsIgnoreCase("Quit")) {
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            } else if (userInput.equalsIgnoreCase("Display")) {
                display(bpByPartName,bpByPartNumber);

                System.out.println("Command Complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if (input.equalsIgnoreCase("Quit")) {
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }else if(userInput.equalsIgnoreCase("Quit")){
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
}
