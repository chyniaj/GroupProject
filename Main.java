package com.company;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * This is the class the user interacts with
 *
 * @author Kerri, Chy, Lewis
 * @version Version 3
 */
public class Main
{
    public static void main(String[] args) throws IOException{

        //objects created here so that we don't lose them after the if/else is executed
        Inventory inventory = new Inventory();
        Warehouse warehouseDB = new Warehouse();
        int inventoryNumParts = inventory.getNumParts();
        int warehouseNumParts = warehouseDB.getNumParts();
        ArrayList<Van> fleet = new ArrayList<Van>();
        ArrayList<BikePart> transport = new ArrayList<BikePart>();
        ArrayList<BikePart> transport2 = new ArrayList<BikePart>();

        Scanner start = new Scanner(System.in);
        System.out.println("Welcome to the inventory! Please enter the command you'd like to execute or enter 'Instructions' for instructions.");
        while (start.hasNext()){
            String userInput = start.next();
            String input;

            if(userInput.equalsIgnoreCase("Instructions")){
                System.out.println("Delivery - read in a delivery"+"\n"+"Display - to display a part"+"\n"+"Add - add a part to warehouse"+"\n"+"NameSort - sorts warehouse from A-Z"+"\n"+"NumSort - sorts warehouse from lease to greatest"+"\n"+"AddVan - adds a van to the fleet" +"\n"+"VantoVan - moves parts from one van to the other" + "\n"+ "WarehousetoVan - moves parts from the warehouse to the van"+"\n"+ "SortVanPartName - sort the parts in the vans"+"\n"+"SortVanPartNumber - sort the parts in the vans"+"\n"+"Quit - update warehouse and quit program.");
                System.out.println("Disclaimer: all methods work with the data in the warehouse upon startup, meaning, unless you select 'Delivery' before you sort/add/display, you will only be altering/showing data that does NOT include what is in the inventory");
                System.out.println("Please enter the command you'd like to execute.");
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equalsIgnoreCase("Delivery")){
                System.out.println("Delivery processed. Enter another command or enter 'Quit' to quit.");
                inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equalsIgnoreCase("Display")){
                System.out.println("Enter the name of the part you'd like to search for:");
                String partSearch = start.next();
                warehouseDB.displayPart(partSearch, warehouseDB);
                System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    //inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equalsIgnoreCase("Add")){
                 warehouseDB.addPart(warehouseDB, warehouseNumParts);

                System.out.println("Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equals("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equalsIgnoreCase("Sell")){ //waiting on Lewis
                Scanner sellscn = new Scanner(System.in);
                System.out.println("Enter the number of the part you'd like to sell:");
                int partNum = sellscn.nextInt();
                System.out.println("Enter the amount you'd like to sell:");
                int sellNum = sellscn.nextInt();
                warehouseDB.sell(partNum,sellNum,warehouseDB);

                System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if (userInput.equalsIgnoreCase("NameSort")){
                warehouseDB.sortName(warehouseDB.getWarehouse());
                System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equalsIgnoreCase("NumSort")){
                warehouseDB.sortNumber(warehouseDB.getWarehouse());
                System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equalsIgnoreCase("AddVan")){
                System.out.println("Enter van name:");
                String vanName = start.next();
                Van van = new Van(vanName);
                fleet.add(van);

                System.out.println(vanName+" has been added to the fleet. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equalsIgnoreCase("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equalsIgnoreCase("VantoVan")){
                System.out.println("Enter the name of the van you'd like to move parts to:");
                String input1 = start.next();
                System.out.println("Enter the name of the van you'd like to take parts from:");
                String input2 = start.next();
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
                    input = start.next();
                    if(input.equalsIgnoreCase("Quit")){
                        System.out.println("Updating and exiting...");
                        warehouseDB.printOutWarehouseDB(warehouseDB);
                        System.exit(0);
                    }
                }
            }

            if (userInput.equalsIgnoreCase("warehousetovan")){
                System.out.println("Enter the name of the van you'd like to move parts to:");
                String input1 = start.next();
                Van van1 = searchFleet(input1,fleet);
                if (van1 != null){
                    van1.mainToVanComparison(van1,warehouseDB,transport2);
                    van1.updateVanhouseDB(van1,transport2);
                    van1.printOutVanWarehouse(van1);
                    System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                    if(userInput.equalsIgnoreCase("Quit")){
                        System.out.println("Updating and exiting...");
                        warehouseDB.printOutWarehouseDB(warehouseDB);
                        System.exit(0);
                    }
                }
            }

            if (userInput.equalsIgnoreCase("SortVanPartName")){
                System.out.println("Enter the van you would like to enter: ");
                input = start.next();
                Van van1 = searchFleet(input, fleet);
                if (van1 != null) {
                    van1.vanPartSortName(van1);//in order for this method to work you need to use the add van method before you can use this method
                    System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                    input = start.next();
                    if (input.equalsIgnoreCase("Quit")) {
                        System.out.println("Updating and exiting...");
                        warehouseDB.printOutWarehouseDB(warehouseDB);
                        System.exit(0);
                    }
                }
            }
            if (userInput.equalsIgnoreCase("SortVanPartNumber")){
                System.out.println("Enter the van you would like to enter: ");
                input = start.next();
                Van van1 = searchFleet(input, fleet);
                if (van1 != null) {
                    van1.vanPartSortNumber(van1);//in order for this method to work you need to use the add van method before you can use this method
                    System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                    input = start.next();
                    if (input.equalsIgnoreCase("Quit")) {
                        System.out.println("Updating and exiting...");
                        warehouseDB.printOutWarehouseDB(warehouseDB);
                        System.exit(0);
                    }
                }
            }
            if(userInput.equalsIgnoreCase("Quit")){
                System.out.println("Updating and exiting...");
                warehouseDB.printOutWarehouseDB(warehouseDB);
                System.exit(0);
            }
        }

    }


    /**
     * Method searches fleet arraylist to find inputted van
     *
     * @param input Name of van user wishes to search for
     *
     * @param fleet ArrayList containing vans that method will search through
     */
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
