package com.company;

import static com.company.Van.createVan;
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

    /**
     * This method prints out info that would be for the commands
     * @param sa
     * @param warehouseDB
     * @param bpByName
     * @param salesAssociates
     * @throws IOException
     */
    public static void printInfo(SalesAssociate sa, Warehouse warehouseDB, Map<String,BikePart> bpByName, ArrayList<SalesAssociate> salesAssociates) throws IOException{
        ArrayList<Van> fleet = new ArrayList<>();
        Scanner infoScan = new Scanner(System.in);
        System.out.println("Welcome to the Sales Associate panel. Please enter one of the following: vanAccess, sale, or quit.");
        String ans;
        while(infoScan.hasNext()){
            ans = infoScan.next();
            if(ans.equalsIgnoreCase("vanAccess")){
                System.out.println("Has your SalesAssociate van been set-up yet? (answer with Y/N)");
                ans = infoScan.next();
                if(ans.equalsIgnoreCase("yes")){
                    System.out.println("Enter van name (must match character at end of Sales Associate ID):");
                    String vanName = infoScan.next();
                    int identifier = sa.getUsername().length()-1;
                    char charAt = sa.getUsername().charAt(identifier);
                    char charAt2 = vanName.charAt(vanName.length()-1);
                    if(charAt == charAt2){
                        Van van = new Van(vanName);
                        fleet.add(van);
                        System.out.println(vanName + " has been added to fleet. " + sa.getUsername() + " can now perform transfers using van.");
                        System.out.println("Transferring to van control panel...");
                        vanAccess(sa,van,warehouseDB,fleet);
                    }
                    else{
                        System.out.println("Van identifier must match identifier at the end of your username. Please enter the van name again.");
                        vanName = infoScan.next();
                        identifier = sa.getUsername().length()-1;
                        charAt = sa.getUsername().charAt(identifier);
                        charAt2 = vanName.charAt(vanName.length()-1);
                        if(charAt == charAt2){
                            Van van = new Van(vanName);
                            fleet.add(van);
                            System.out.println(vanName + " has been added to fleet. SalesAssociate can now perform transfers using van.");
                            System.out.println("Transferring to van control panel...");
                            vanAccess(sa,van,warehouseDB,fleet);
                        }
                        else{
                            System.out.println("Second attempt failed. Exiting program for security purposes.");
                            System.exit(0);
                        }
                    }
                }
                else if(ans.equalsIgnoreCase("no")){
                    System.out.println("Enter name of van to create files (must match character at end of Sales Associate ID:");
                    String vanName = infoScan.next();
                    createVan(vanName);
                    System.out.println("Empty van files created. "+ sa.getUsername() + " can now acces files to " + vanName + ". Refreshing program for changes to be applied...");
                    System.exit(0);
                }
                System.out.println("Welcome back to the Sales Associate panel. Please enter one of the following: vanAccess, sale, or quit.");
                ans = infoScan.next();
                if(ans.equalsIgnoreCase("quit")){
                    System.out.println("Logging out and exiting...");
                    //inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    printOutSA(salesAssociates);
                    System.exit(0);

                }
            }
            if (ans.equalsIgnoreCase("sale")){
                sale(warehouseDB,bpByName, sa);
                System.out.println("Welcome back to the Sales Associate panel. Please enter one of the following: vanAccess, sale, or quit.");
                ans = infoScan.next();
                if(ans.equalsIgnoreCase("quit")){
                    System.out.println("Logging out and exiting...");
                    //inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                    warehouseDB.printOutWarehouseDB(warehouseDB);
                    printOutSA(salesAssociates);
                    System.exit(0);
                }

            }
            if (ans.equalsIgnoreCase("quit")){
                System.out.println("Logging out and exiting...");
                //inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                warehouseDB.printOutWarehouseDB(warehouseDB);
                printOutSA(salesAssociates);
                System.exit(0);
            }

        }
    }

    /**
     * This method is used to get access to the vans and to be able to perform
     * other commands that will involve vans
     * @param sa
     * @param v
     * @param warehouseDB
     * @param fleet
     * @throws IOException
     */
    public static void vanAccess(SalesAssociate sa, Van v, Warehouse warehouseDB, ArrayList<Van> fleet) throws IOException{
        Scanner vanScan = new Scanner(System.in);
        ArrayList<BikePart> transport = new ArrayList<>();
        ArrayList<BikePart> transport2 = new ArrayList<>();
        //System.out.println("Enter the name of the van you'd like to access:");
        String option;
        String userMatch = v.getVanName();
        int identifier = sa.getUsername().length()-1;
        char charAt = sa.getUsername().charAt(identifier);
        char charAt2 = userMatch.charAt(userMatch.length()-1);

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
                    //System.out.println("Enter the name of the van you'd like to move parts to:");
                    //String input1 = vanScan.next();
                    System.out.println("Performing van to van transfer using " + userMatch + ".");
                    System.out.println("Enter the name of the van you'd like to take parts from:");
                    String input2 = vanScan.next();
                    Van van1 = searchFleet(userMatch,fleet);
                    Van van2 = searchFleet(input2,fleet);
                    if(van2 == null){
                        System.out.println("Van to move parts to was not found in system. Please enter the van name to manually import files into system.");
                        String van2Name = vanScan.next();
                        van2 = new Van(van2Name);
                    }

                    if (van1 != null && van2 != null){

                        int numPartsVan2 =van2.getWarehouse().size();
                        int numPartsVan1= van1.getWarehouse().size();

                        van1.vanToVanComparison(van1,van2, transport,numPartsVan2);
                        van1.vanUpdateWarehouse(van1,transport, numPartsVan1);
                        van1.printOutVanWarehouse(van1);
                        van2.printOutVanWarehouse(van2);
                        System.out.println("Van to van transfer complete. Enter another command within the vanAccess panel or enter 'Quit' to quit.");
                        option = vanScan.next();
                        if(option.equalsIgnoreCase("quit")){
                            System.out.println("Logging out and exiting...");
                            //inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                            warehouseDB.printOutWarehouseDB(warehouseDB);
                            System.exit(0);
                        }
                    }
                    else{
                        System.out.println("Command unable to execute, van not found. Please restart and make sure both vans are in the system.");
                        System.exit(0);
                    }
                }
                if (option.equalsIgnoreCase("warehousetovan")){
                    //System.out.println("Enter the name of the van you'd like to move parts to:");
                    //option = vanScan.next();
                    System.out.println("Performing warehouse to van transfer using " + userMatch + ".");

                    Van van1 = searchFleet(userMatch,fleet);
                    if (van1 != null){
                        int numPartsVan = van1.getNumParts();
                        van1.mainToVanComparison(van1,warehouseDB,transport2);
                        van1.updateVanhouseDB(van1,transport2, numPartsVan);
                        van1.printOutVanWarehouse(van1);
                    }
                    System.out.println("Warehouse to van transfer complete. Enter another command within the vanAccess panel or enter 'Quit' to quit.");
                    option = vanScan.next();
                    if(option.equalsIgnoreCase("quit")){
                        System.out.println("Logging out and exiting...");
                        //inventory.updateWarehouseDB(inventory,warehouseDB, warehouseNumParts);
                        warehouseDB.printOutWarehouseDB(warehouseDB);
                        System.exit(0);
                    }
                }
            }
        }
        else{
            System.out.println("Van name does not match with your identifier. Please try again");
            option = vanScan.next();
        }
    }

    /**
     * This method is used to perform a sale of an item.
     * @param w
     * @param bpByNameMap
     * @param sa
     * @throws IOException
     */
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
        if (selling.size() == 0){
            System.out.println("No parts that were entered exist in the Warehouse, an Invoice will not be printed and " + sa.getUsername() + " will receive no earnings. Returning to Sales Associate panel.");
        }
        else{
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
            System.out.println("Parts have been removed from Warehouse and " + totalCost + " has been credited to "+ sa.getUsername() +". Printing Sales Invoice...");

            sa.setTotalEarnings(totalCost);
            invoice.printSalesInvoice(invoice);
        }


    }

    /**
     * This method searches the fleet for a van that the user is looking for.
     * @param input
     * @param fleet
     * @return
     */
    public static Van searchFleet(String input,ArrayList<Van> fleet){
        for(int i=0; i < fleet.size(); i++){
            if(input.equals(fleet.get(i).getVanName())){
                return fleet.get(i);
            }
        }
        //System.out.println("Van not found. Pleas");
        return null;
    }

    /**
     * This method creates a van that the Sales Associate will be able to use.
     * @param fleet
     * @throws IOException
     */
    public static void createVanSA(ArrayList<Van> fleet) throws IOException{
        Scanner vanIn = new Scanner(System.in);
        System.out.println("Have the files for specified van already been created?");
        String ans = vanIn.next();
        if (ans.equalsIgnoreCase("yes")){
            System.out.println("Enter van name (must match character at end of Sales Associate ID):");
            String vanName = vanIn.next();
            Van van = new Van(vanName);
            fleet.add(van);
            System.out.println("Van has been added to fleet. SalesAssociate can now perform transfers using van.");
        }
        else if (ans.equalsIgnoreCase("no")){
            System.out.println("Enter name of van to create files (must match character at end of Sales Associate ID:");
            String vanName = vanIn.next();
            createVan(vanName);
            System.out.println("Empty van files created. SalesAssociate can now acces files to " + vanName + ". Restart program for changes to be applied.");
        }
    }
}




