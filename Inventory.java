package com.company;

import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
    /**
     * Write a description of class Inventory here.
     *
     * @author (your name)
     * @version (a version number or a date)
     */
    public class Inventory {
        private static int numParts;
        private BikePart part;
        private ArrayList<BikePart> inventory;

        /**
         * Constructor for objects of class Inventory
         */
        public Inventory()
        {
            inventory = new ArrayList<BikePart>();
            this.numParts = readDelivery(inventory);
        }

        public int getNumParts(){
            return this.numParts;
        }

        public ArrayList getInventory(){
            return this.inventory;
        }

        /**
         * An example of a method - replace this comment with your own
         *
         * @param  //a sample parameter for a method
         * @return    the sum of x and y
         */
        public int readDelivery(ArrayList inventory)
        {
            Scanner scnr = new Scanner(System.in);

            //System.out.println();
            //String fileName = scnr.next();

            String partName;
            int partNum;
            double listPrice;
            double salePrice;
            boolean saleStatus;
            int quantity;
            //while(scanIn.hasNext()){
            try{
                FileInputStream fileIn = new FileInputStream("inventory.txt");
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
                    BikePart part = new BikePart(partName, partNum, listPrice, salePrice, saleStatus, quantity);
                    inventory.add(part);
                    numParts++;
                }
                fileIn.close();
            }
            catch (FileNotFoundException e){
                System.out.println("File Not Found. Please restart and enter new file name.");
                e.printStackTrace();
                //System.out.print("Please enter another file name:");
                //scanIn.hasNext();
            }
            catch (IOException e){
                System.out.println("IO Exception. Please restart.");
                e.printStackTrace();
            }
            //}
            return numParts;
        }

        /**method takes warehouse array printing out to warehouseDB.txt
         *
         * @param warehouse
         * @throws FileNotFoundException
         * @throws IOException
         */
        public static void printOutWarehouseDB(Warehouse warehouse) throws FileNotFoundException, IOException{
            FileOutputStream fileOut = new FileOutputStream(warehouseDB.txt);
            PrintWriter out = new PrintWriter(fileOut);
                for(int i = 0; i < warehouse.getWarehouse().size()-1; i++){
                    out.write(warehouse.getWarehouse().get(i).printInfo());
                    out.write("\n");
                    out.flush();
                }
                fileOut.close();
        }
    }
