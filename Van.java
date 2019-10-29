import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
/**
 * Write a description of class Van here.
 *
 * @author Kerri, Chy and Lewis
 * @version 10/23/19
 */
public class Van
{

    private ArrayList<BikePart> inventory = new ArrayList<BikePart>();
    private ArrayList<BikePart> warehouse = new ArrayList<BikePart>();
    private String vanName;

    /**
     * Constructor for objects of class Van
     */
    public Van(String vanName)
    {
        inventory = new ArrayList<BikePart>();
        warehouse = new ArrayList<BikePart>();
        this.vanName = vanName.trim();
        readVanInventory(inventory, vanName);
        readVanWarehouse(warehouse,vanName);
        this.inventory = inventory;
        this.warehouse = warehouse;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList<BikePart> getWarehouse()
    {
        return this.warehouse;
    }

    public ArrayList<BikePart> getInventory()
    {
        return this.inventory;
    } 

    public String getVanName(){
        return this.vanName;
    }

    public static void readVanWarehouse(ArrayList<BikePart> warehouse, String vanName)
    {
        try{
            String partName;
            int partNum;
            double listPrice;
            double salePrice;
            boolean saleStatus;
            int quantity;
            FileInputStream fileIn = new FileInputStream(vanName+"warehouse.txt");
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

    }

    public static void readVanInventory(ArrayList<BikePart> inventory, String vanName){
        try{
            String partName;
            int partNum;
            double listPrice;
            double salePrice;
            boolean saleStatus;
            int quantity;
            FileInputStream fileIn = new FileInputStream(vanName+"inventory.txt");
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
    }

    public static void printOutVanWarehouse(Van v) throws FileNotFoundException, IOException{
        FileOutputStream fileOut = new FileOutputStream(v.getVanName()+"warehouse.txt");
        PrintWriter out = new PrintWriter(fileOut);
        for(int i = 0; i < v.getWarehouse().size(); i++){
            out.println(v.getWarehouse().get(i).getPartName()+","+v.getWarehouse().get(i).getPartNum()+","+v.getWarehouse().get(i).getListPrice()+","+v.getWarehouse().get(i).getSalePrice()+","+v.getWarehouse().get(i).getSaleStatus()+","
                +v.getWarehouse().get(i).getQuantity());
        }
        out.flush();
        fileOut.close();
    }

    public static void printOutVanInventory(Van v) throws FileNotFoundException, IOException{
        FileOutputStream fileOut = new FileOutputStream(v.getVanName()+"inventory.txt");
        PrintWriter out = new PrintWriter(fileOut);
        for(int i = 0; i < v.getWarehouse().size(); i++){
            out.println(v.getWarehouse().get(i).getPartName()+","+v.getWarehouse().get(i).getPartNum()+","+v.getWarehouse().get(i).getListPrice()+","+v.getWarehouse().get(i).getSalePrice()+","+v.getWarehouse().get(i).getSaleStatus()+","
                +v.getWarehouse().get(i).getQuantity());
        }
        out.flush();
        fileOut.close();
    }

    /**
     * Updates warehouse ArrayList and prints it out to the warehouseDB.txt file
     * 
     * @param inventory, warehouse Objects of type Inventory and Warehouse
     */
    public static void vanToVanComparison(Van van1, Van van2, ArrayList<BikePart> transport){
        if (van2.getWarehouse().size() == 0){
            System.out.println("Van" + van2.getVanName() + " is empty.");
        }
        else {
            for (int i=0; i<van1.getInventory().size(); i++){
                String temp = van1.getInventory().get(i).getPartName();

                for (int j=0; j<van2.getWarehouse().size();j++){
                    String temp2 = van2.getWarehouse().get(j).getPartName();
                    if(temp.equals(temp2)){ //be aware of lower or uppercase AND TRIM!!!
                        BikePart temp3 = van2.getWarehouse().get(j);
                        van2.getWarehouse().remove(van2.getWarehouse().get(j));
                    }
                }
            }
        }
    }

    public static void vanUpdateWarehouse(Van van1, ArrayList<BikePart> transport){
        for (int i=0; i<transport.size(); i++){
            String temp = transport.get(i).getPartName();
            if (van1.getWarehouse().size() == 0){
                BikePart part = transport.get(i);
                van1.getWarehouse().add(part);
            }
            else{
                for (int j=0; j<van1.getWarehouse().size();j++){
                    String temp2 = van1.getWarehouse().get(j).getPartName();
                    if(temp.equals(temp2)){ //be aware of lower or uppercase AND TRIM!!!
                        int quant = transport.get(i).getQuantity();
                        int quant2 = van1.getWarehouse().get(j).getQuantity();
                        van1.getWarehouse().get(j).setQuantity(quant+quant2);

                    }
                    else if(!(temp.equals(temp2)) && j == van1.getWarehouse().size()){
                        BikePart part = transport.get(i);
                        van1.getWarehouse().add(part);
                    }

                }

            }
        }
    }
}
