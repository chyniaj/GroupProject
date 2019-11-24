/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cpscproject;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import com.mycompany.cpscproject.BikePart;
import java.time.LocalDateTime;
import java.time.ZoneId;
/**
 *
 * @author kerri
 */
public class Warehouse {
    private ArrayList<BikePart> warehouse;
    private static int numParts;
    /**
     * Constructor for objects of class Warehouse
     */
    public Warehouse()
    {
        warehouse = new ArrayList<BikePart>();
        readDatabase(warehouse);
        this.warehouse = warehouse;
    }
    
    public Warehouse(int numParts){
        warehouse = new ArrayList<BikePart>();
        numParts = this.numParts;
    }
    
    public int getNumParts(){
        return warehouse.size();
    }

    public ArrayList<BikePart> getWarehouse(){
        return this.warehouse;
    }

    /**
     * Reads in info from warehouseDB.txt to initialize warehouse
     *
     * @param  inWarehouse  ArrayList that will contain list of bike parts in the warehouse
     * @return void   
     */
    public static int readDatabase(ArrayList<BikePart> warehouse) throws NumberFormatException
    {
        try{
            String partName;
            int partNum;
            double listPrice;
            double salePrice;
            boolean saleStatus;
            int quantity;
            FileInputStream fileIn = new FileInputStream("warehouseDB.txt");
            Scanner scanIn = new Scanner(fileIn);

            while(scanIn.hasNextLine()){
                String info = scanIn.nextLine();
                info = info.trim();
                String[] infoHolder = info.split(",");
                partName = infoHolder[0];
                partNum = Integer.parseInt(infoHolder[1]);
                listPrice = Double.parseDouble(infoHolder[2]);
                salePrice = Double.parseDouble(infoHolder[3]);
                saleStatus = Boolean.parseBoolean(infoHolder[4]);
                quantity = Integer.parseInt(infoHolder[5]);
                BikePart part = new BikePart(partName, partNum, listPrice, salePrice, saleStatus, quantity);
                warehouse.add(part);
                numParts++;
            }
            fileIn.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File Not Found. Please restart and enter new file name.");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.print("IOException. Please restart.");
            e.printStackTrace();
        }

        return numParts;

    }

    /**
     * Displays requested part and displays a message if part is not in warehouse
     * 
     * @param partName, warehouse
     * 
     * @return void
     */
    public void displayPart(String partName, Warehouse warehouse)
    {
        String name = partName;
        boolean done = false;
        if(done == false){
            for(int i =0; i <= warehouse.getWarehouse().size()-1; i++){
                if(name.equals(warehouse.getWarehouse().get(i).getPartName()))
                {
                    warehouse.getWarehouse().get(i).printInfo();
                    done = true;
                }
                else if(i==warehouse.getWarehouse().size()-1 && done==false){
                    System.out.println("Part is not in file."); 
                }
            }
        }
    }

    /**
     * Prints warehouse ArrayList to a text document
     * 
     * @param warehouse Warehouse whose items are being printed to a text file
     * 
     * @return void
     */
    public static void printOutWarehouseDB(Warehouse warehouse) throws FileNotFoundException, IOException{
        FileOutputStream fileOut = new FileOutputStream("warehouseDB.txt");
        PrintWriter out = new PrintWriter(fileOut);

        for(int i = 0; i < warehouse.getWarehouse().size(); i++){
            out.println(warehouse.getWarehouse().get(i).getPartName()+","+warehouse.getWarehouse().get(i).getPartNum()+","+warehouse.getWarehouse().get(i).getListPrice()+","+warehouse.getWarehouse().get(i).getSalePrice()+","+warehouse.getWarehouse().get(i).getSaleStatus()+","
                +warehouse.getWarehouse().get(i).getQuantity());

        }
        out.flush();
        fileOut.close();
    }

    /**
     * Adds part to warehouse ArrayList
     * 
     * @param warehouse, part ArrayList from Warehouse and a bikepart user wishes to add
     * 
     * @param partName, name of part that user puts in
     * 
     * @param part, Bikepart user wishes to add 
     * 
     * @return void
     */
    public void addPart(Warehouse warehouse, int warehouseNumParts) {
        Scanner start = new Scanner(System.in);
        System.out.println("Enter part name, part number, list price, sale price, sale status, and quantity:");
        String part = start.next();
        part = part.trim();
        String[] infoHolder = part.split(",");
        String partName = infoHolder[0];
        int partNum = Integer.parseInt(infoHolder[1]);
        double listPrice = Double.parseDouble(infoHolder[2]);
        double salePrice = Double.parseDouble(infoHolder[3]);
        boolean saleStatus = Boolean.parseBoolean(infoHolder[4]);
        int quantity = Integer.parseInt(infoHolder[5]);
        BikePart bkpart = new BikePart(partName, partNum, listPrice, salePrice, saleStatus, quantity);
            for (int j = 0; j < warehouse.getWarehouse().size(); j++) {
                String temp = warehouse.getWarehouse().get(j).getPartName();
                if (temp.equalsIgnoreCase(bkpart.getPartName())) { //be aware of lower or uppercase AND TRIM!!!
                    int quant2 = warehouse.getWarehouse().get(j).getQuantity();
                    warehouse.getWarehouse().get(j).setQuantity(quant2 + quantity);

                } else if (!(temp.equalsIgnoreCase(bkpart.getPartName())) && j == warehouse.getWarehouse().size()) {

                    warehouse.getWarehouse().add(bkpart);
                }
            }
            if (warehouseNumParts == 0) {
            warehouse.getWarehouse().add(bkpart);
            }
            System.out.println(bkpart.getPartName()+" has been added to the warehouse.");
        }

    /**
     * Sorts parts in the ArrayList to get part number.
     * 
     * @param   list   the list of BikePart objects
     * 
     * @return void
     */
    public static void sortNumber(ArrayList<BikePart> list){
        System.out.println("Sorting numbers from least to greatest...");
        Comparator partNumber = new ComparatorByPartNumber();
        Collections.sort(list, partNumber);
        for(BikePart bpt : list) {
            System.out.println(bpt.getPartNum());
        }
    }

    /**
     * Sorts parts in the ArrayList to get part name.
     * 
     * @param   list   the list of BikePart objects
     * 
     * @return void
     */
    public static void sortName(ArrayList<BikePart> list) {
        Collections.sort(list);
        for(int i=0; i<list.size(); i++){
            String str = new String(list.get(i).getPartName());
            System.out.println(str);
        }
    }
    
    
    /**
     * Sells specified part from Warehouse
     * 
     * @param warehouseNumParts number of parts in the warehouse
     * @param warehouse, part ArrayList from Warehouse and a bikepart user wishes to adds
     */
    public void sell(int warehouseNumParts, Warehouse warehouse, String tempName, int tempQuant)
    {
        
        /*Scanner sellscn = new Scanner(System.in);
        System.out.println("Enter the number of the part you'd like to sell:");
        int partNumber = sellscn.nextInt();
        System.out.println("Enter the amount you'd like to sell:");
        int quantity = sellscn.nextInt();*/
        
        System.out.println("Time of sale:");
        Instant timestamp = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(), ldt.getYear(), ldt.getHour(), ldt.getMinute());
        
        for (int i=0; i<warehouse.getWarehouse().size(); i++){
            String name = warehouse.getWarehouse().get(i).getPartName();

            if (warehouseNumParts == 0){
                System.out.println("There is nothing to sell.");
            }
            else{
                if(name.equalsIgnoreCase(tempName)){ 
                    int temp = warehouse.getWarehouse().get(i).getQuantity() - tempQuant;
                    
                    if(temp == 0 || temp < 0){
                        warehouse.getWarehouse().remove(i);
                        System.out.println("All of this part has been sold. Part removed from warehouse.");
                    }
                    else if (temp > 0){
                        warehouse.getWarehouse().get(i).setQuantity(temp);
                        System.out.println(tempQuant+" of the specified part have been sold. "+temp+" of this part remain in the warehouse.");
                    }
                }
            } 
        }

    }
}