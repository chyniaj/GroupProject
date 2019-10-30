import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
/**
 * This is the class the user interacts with
 *
 * @author Kerri, Chy, Lewis
 * @version (a version number or a date)
 */
public class TesterClass
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
        
        //Create Van objects and add vans to fleet
        Van van1 = new Van("van1");
        Van van2 = new Van("van2");
        fleet.add(van1);
        fleet.add(van2);
        Scanner start = new Scanner(System.in);

        System.out.println("Welcome to the inventory! Please enter the command you'd like to execute or enter 'Instructions' for instructions.");

        while (start.hasNext()){
            String userInput = start.next();
            String input;

            if(userInput.equals("Instructions")){
                System.out.println("Delivery - read in a delivery"+"\n"+"Display - to display a part"+"\n"+"Add - add a part to warehouse"+"\n"+"NameSort - sorts warehouse from A-Z"+"\n"+"NumSort - sorts warehouse from lease to greatest"+"\n"+"Quit - update warehouse and quit program.");
                System.out.println("Disclaimer: all methods work with the data in the warehouse upon startup, meaning, unless you select 'Delivery' before you sort/add/display, you will only be altering/showing data that does NOT include what is in the inventory");
                System.out.println("Please enter the command you'd like to execute.");
                input = start.next();
                if(input.equals("Quit")){
                    System.out.println("Updating and exiting...");
                    inventory.updateWarehouseDB(inventory,warehouseDB);
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equals("Delivery")){
                System.out.println("Delivery processed. Enter another command or enter 'Quit' to quit.");
                inventory.updateWarehouseDB(inventory,warehouseDB);
                input = start.next();
                if(input.equals("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equals("Display")){
                System.out.println("Enter the name of the part you'd like to search for:");
                String partSearch = start.next();
                warehouseDB.displayPart(partSearch, warehouseDB);
                System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equals("Quit")){
                    System.out.println("Updating and exiting...");
                    //inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equals("Add")){
                System.out.println("Enter part name:");
                String partName = start.next();
                System.out.println("Enter part number:");
                int partNum = start.nextInt();
                System.out.println("Enter list price:");
                double listPrice = start.nextDouble();
                System.out.println("Enter sale price:");
                double salePrice = start.nextDouble();
                System.out.println("Enter sale status:");
                boolean saleStatus = start.nextBoolean();
                System.out.println("Enter quantity:");
                int quantity = start.nextInt();
                BikePart part = new BikePart(partName,partNum,listPrice,salePrice,saleStatus,quantity);
                warehouseDB.addPart(warehouseDB,part);

                System.out.println("Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equals("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equals("Sell")){ //waiting on Lewis 
                Scanner sellscn = new Scanner(System.in);
                System.out.println("Enter the number of the part you'd like to sell:");
                int sellNum = sellscn.nextInt();
                warehouseDB.sell(sellNum);
                
                System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equals("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }
            
            if (userInput.equals("NameSort")){
                warehouseDB.sortName(warehouseDB.getWarehouse());
                System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equals("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }

            if(userInput.equals("NumSort")){
                warehouseDB.sortNumber(warehouseDB.getWarehouse());
                System.out.println("Command complete. Enter another command or enter 'Quit' to quit.");
                input = start.next();
                if(input.equals("Quit")){
                    System.out.println("Updating and exiting...");
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    System.exit(0);
                }
            }
            
            if(userInput.equals("Add Van")){
            }
            
            if(userInput.equalsIgnoreCase("Van to Van")){
                van1.vanToVanComparison(van1,van2, transport);
                van1.vanUpdateWarehouse(van1,transport);
                
                
            }
            
            if (userInput.equalsIgnoreCase("Warehouse to Van")){
                
            }

            if(userInput.equals("Quit")){
                System.out.println("Updating and exiting...");
                warehouseDB.printOutWarehouseDB(warehouseDB);
                System.exit(0);
            }
        }

    }

}
