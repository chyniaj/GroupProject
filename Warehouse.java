import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;
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
     *
     * Reads in info from warehouseDB.txt to intialize warehouse
     * @param warehouse
     * @return
     * @throws NumberFormatException
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
     * @param warehouse
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
                BikePart localBikePart = new BikePart(part.getPartName(), part.getPartNum(),part.getListPrice(), part.getSalePrice(), part.getSaleStatus(), part.getQuantity());
                warehouse.getWarehouse().add(localBikePart);
            }
            else 
            {
                Quantity = warehouse.getWarehouse().get(i).getQuantity() + part.getQuantity();
                //BikePart localBikePart = new BikePart(part.getPartName(), part.getPartNum(),part.getListPrice(),part.getSalePrice(), part.getSaleStatus(), part.getQuantity());

                warehouse.getWarehouse().get(i).setQuantity(Quantity);
            }
        }
    }

    /**
     * Sorts parts in the ArrayList to get part number.
     * 
     */
    public static void sortNumber(ArrayList<BikePart> list){
        Comparator partNumber = new ComparatorByPartNumber();
        Collections.sort(list, partNumber);
        for(BikePart bpt : list) {
            System.out.println(bpt.getPartNum());
        }
    }

    public static void sortName(ArrayList<BikePart> list) {
        Collections.sort(list);
        for(int i=0; i<list.size(); i++){
            String str = new String(list.get(i).getPartName());
            str.equalsIgnoreCase("Wheel");
            str.compareTo(str);
            System.out.println(str);
        }
    }
}
