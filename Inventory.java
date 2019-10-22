import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 * Write a description of class Inventory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Inventory
{
    private ArrayList<BikePart> inventory;
    private static int numParts;
    private BikePart part;
    /**
     * Constructor for objects of class Inventory
     */
    public Inventory()
    {
        inventory = new ArrayList<BikePart>();
        readDelivery(inventory);
        this.inventory = inventory;
    }

    public int getNumParts(){
        return inventory.size();
    }

    public ArrayList<BikePart> getInventory(){
        return this.inventory;
    }

    /**
     * Reads in Inventory text file and puts info. into an ArrayList
     *
     * @param  inventory ArrayList for values to be entered in.
     * @return  number of parts in ArrayList
     */
    public int readDelivery(ArrayList inventory) 
    {
        Scanner scnr = new Scanner(System.in);

        String partName;
        int partNum;
        double listPrice;
        double salePrice;
        boolean saleStatus;
        int quantity;

        try{
            FileInputStream fileIn = new FileInputStream("inventory.txt");
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
                inventory.add(part);
                numParts++;
            }
            fileIn.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File Not Found. Please restart and enter new file name.");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("IO Exception. Please restart.");
            e.printStackTrace();
        }
        return numParts;
    }

    /**
     * Updates warehouse ArrayList and prints it out to the warehouseDB.txt file
     * 
     * @param inventory, warehouse Objects of type Inventory and Warehouse
     */
    public static void updateWarehouseDB(Inventory inventory, Warehouse warehouse, int numParts){
        for (int i=0; i<inventory.getInventory().size(); i++){
            String temp = inventory.getInventory().get(i).getPartName();
            int numPartsHolder = numParts;
            if (numPartsHolder == 0){
                BikePart part = inventory.getInventory().get(i);
                warehouse.getWarehouse().add(part);
            }
            else{
                for (int j=0; j<warehouse.getWarehouse().size();j++){
                    String temp2 = warehouse.getWarehouse().get(j).getPartName();
                    if(temp.equals(temp2)){ //be aware of lower or uppercase AND TRIM!!!
                        int quant = inventory.getInventory().get(i).getQuantity();
                        int quant2 = warehouse.getWarehouse().get(j).getQuantity();
                        warehouse.getWarehouse().get(j).setQuantity(quant+quant2);

                    }
                    else if(!(temp.equals(temp2)) && j == warehouse.getWarehouse().size()){
                        BikePart part = inventory.getInventory().get(i);
                        warehouse.getWarehouse().add(part);
                    }

                }
            }
        }
    }
}
