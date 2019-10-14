package com.company;
import java.util.ArrayList;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
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
        return readDatabase(warehouse);
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
    public static int readDatabase(ArrayList<BikePart> warehouse) throws
            NumberFormatException
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
                String[] infoHolder = info.split(",");
                partName = infoHolder[0];
                partNum = Integer.parseInt(infoHolder[1]);
                listPrice = Double.parseDouble(infoHolder[2]);
                salePrice = Double.parseDouble(infoHolder[3]);
                saleStatus = Boolean.parseBoolean(infoHolder[4]);
                quantity = Integer.parseInt(infoHolder[5]);
                BikePart part = new BikePart(partName, partNum, listPrice,
                        salePrice, saleStatus, quantity);
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
     * This method will sort the ArrayList items by number order.
     *
     * @param list
     */
    //Change to the comparable/comparator method
    public static void sortNumber(ArrayList<BikePart> list){
        /**int x = list.size();
        for (int i = 0; i < x; i++){
            for(int j = 0; j < x; j++){
                if (list.get(i).getPartNum() > list.get(j).getPartNum()) {
                    BikePart temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }*/
        ComparatorByPartNumber partNumber = new ComparatorByPartNumber();
        Collections.sort(list, partNumber);
        for(BikePart bpt : list) {
            System.out.println(bpt.getPartName() + " " + bpt.getPartNum() + " " +
                bpt.getListPrice() + " " + bpt.getSalePrice() + " " + bpt.getSaleStatus());
        }
    }
    /**
     * This method will sort the ArrayList items in alphabetical order.
     *
     * @param list
     */
    //Change to the comparable method
    public static void sortName(ArrayList<BikePart> list) {
       // int x = list.size();
       // for (int i = 0; i < x; i++){
         //   String temp = list.get(i).getPartName();
           // for (int j = 0; j < x; j++){
                /*if 
(list.get(i).getPartName().compareTo(list.get(j).getPartName()) > 0) {
                    BikePart temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
                else if 
(list.get(i).getPartName().compareTo(list.get(j).getPartName()) == 0) {
                    BikePart temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                */
             //   String temp2 = list.get(j).getPartName();
               // if (temp.compareTo(temp2) < 0) {
                 //   BikePart part = list.get(i);
                   // list.set(i, list.get(j));
                 //   list.set(j, part);
              //  }
           // }
        //}
        Collections.sort(list);
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).getPartName());
        }
    }
    /**
     * Prints warehouse ArrayList to a text document
     *
     * @param warehouse
     */
    public static void printOutWarehouseDB(Warehouse warehouse) throws
            FileNotFoundException, IOException{
        FileOutputStream fileOut = new FileOutputStream("warehouseDB.txt");
        PrintWriter out = new PrintWriter(fileOut);
        for(int i = 0; i < warehouse.getWarehouse().size(); i++){
            out.write(warehouse.getWarehouse().get(i).getPartName()
                    +","+warehouse.getWarehouse().get(i).getPartNum()
                    +","+warehouse.getWarehouse().get(i).getListPrice()
                    +","+warehouse.getWarehouse().get(i).getSalePrice()
                    +","+warehouse.getWarehouse().get(i).getSaleStatus()
                    +","+warehouse.getWarehouse().get(i).getQuantity()+"\n");
            //out.write("\n");
            out.flush();
        }
        fileOut.close();
    }
    /**
     * Adds part to warehouse ArrayList





     *
     * @param warehouse, part ArrayList from Warehouse and a bikepart user wishes to
    add
     */
    public void addPart(Warehouse warehouse, BikePart part)
    {
        int localpartnum;
        int Quantity = 0;
        for ( int i =0; i < warehouse.getWarehouse().size(); i++)
        {
            localpartnum = warehouse.getWarehouse().get(i).getPartNum();
            if(localpartnum != warehouse.getWarehouse().get(i).getPartNum())
            {
                BikePart localBikePart = new BikePart(part.getPartName(),
                        part.getPartNum(),part.getListPrice(), part.getSalePrice(), part.getSaleStatus(),
                        part.getQuantity());
                warehouse.getWarehouse().add(localBikePart);
            }
            else
            {
                Quantity = warehouse.getWarehouse().get(i).getQuantity() +
                        part.getQuantity();
                BikePart localBikePart = new BikePart(part.getPartName(),
                part.getPartNum(),part.getListPrice(),part.getSalePrice(), part.getSaleStatus(),
                        part.getQuantity());
                warehouse.getWarehouse().get(i).setQuantity(Quantity);
            }
        }
    }
}