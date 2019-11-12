package com.company;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
/**
 * Write a description of class Warehouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Warehouse

{
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

    public int getNumParts(){
        return warehouse.size();
    }

    public ArrayList<BikePart> getWarehouse(){
        return this.warehouse;
    }

    /**
     * Reads in info from warehouseDB.txt to intialize warehouse
     *
     * @param  warehouse ArrayList that will contain list of bike parts in the warehouse
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

    //CREATE NEW ADD METHOD
    /**
     * Adds part to warehouse ArrayList
     *
     * @param warehouse, part ArrayList from Warehouse and a bikepart user wishes to add
     *
     * @return void
     */
    public void addPart(Warehouse warehouse) {
        int warehouseNumParts = 0;
        int Quantity = 0;
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
                    warehouse.getWarehouse().get(j).setQuantity(quant2);

                } else if (!(temp.equalsIgnoreCase(bkpart.getPartName())) && j == warehouse.getWarehouse().size()) {

                    warehouse.getWarehouse().add(bkpart);
                }
            }
            if (warehouseNumParts == 0) {
            warehouse.getWarehouse().add(bkpart);
            }
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
            System.out.println(bpt.getPartName() + " " + bpt.getPartNum()
                + " " + bpt.getListPrice() + " " + bpt.getSalePrice() + " " +
                    bpt.getSaleStatus() + " " + bpt.getQuantity());
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
            String str = new String(list.get(i).getPartName() + " " + list.get(i).getPartNum() + " " +
                list.get(i).getListPrice() + " " + list.get(i).getSalePrice() + " " + list.get(i).getSaleStatus()
                    + " " + list.get(i).getQuantity());
            System.out.println(str);
        }
    }


    //CREATE NEW SELL METHOD
    /**
     * Sells part from Inventory and prints to Warehouse
     *
     * @param partNum, name of the part
     *
     * @param number, the number of parts the user wants to sell
     *
     * @param warehouse, part ArrayList from Warehouse and a bikepart user wishes to adds
     *
     * @return void
     */
    public void sell(int partNum,int number,Warehouse warehouse )
    {
        Scanner input = new Scanner(System.in);
        BikePart part;
        int quant2;

        Instant now=Instant.now();
        System.out.println(now.toString());
        for(int i =0; i < warehouse.getWarehouse().size(); i++){
            int quant1 = warehouse.getWarehouse().get(i).getQuantity();
            if(warehouse.getWarehouse().get(i).getPartNum()==(partNum))
            {

                //System.out.println(warehouse.getWarehouse().get(i).getPartName());
                /*+" "+
                warehouse.getWarehouse().get(i).getPartNum()
                +" "+warehouse.getWarehouse().get(i).getQuantity());*/
                if(quant1 > number||quant1 < number||quant1 == number)
                {
                    quant2 = quant1 - number;
                    /* System.out.println(warehouse.getWarehouse().get(i).getPartName()+" "+
                    warehouse.getWarehouse().get(i).getPartNum()
                    +" "+ quant2);**/
                    part = new BikePart(warehouse.getWarehouse().get(i).getPartName(),
                            warehouse.getWarehouse().get(i).getPartNum(),
                            warehouse.getWarehouse().get(i).getListPrice(),
                            warehouse.getWarehouse().get(i).getSalePrice(),
                            warehouse.getWarehouse().get(i).getSaleStatus(),
                            quant2);
                    warehouse.getWarehouse().set(i, part);

                }
                else {
                    System.out.println("Not enough quantity on hand" + quant1);
                }

            }
        }
    }
}
