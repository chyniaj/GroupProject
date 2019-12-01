package com.company;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.Collections;
import java.io.File;
import java.util.Comparator;
/**
 *
 * @author kerri
 */
public class Van implements Comparable<Van> {
    private ArrayList<BikePart> inventory;
    private ArrayList<BikePart> warehouse;
    private String vanName;
    private int numPartsVanWarehouse;

    /**
     * Constructor for objects of class Van
     *
     * @param vanName
     *
     * @throws java.io.IOException
     */
    public Van(String vanName) throws IOException
    {
        inventory = new ArrayList<BikePart>();
        warehouse = new ArrayList<BikePart>();
        this.vanName = vanName.trim();
        readVanInventory(inventory, vanName);
        readVanWarehouse(warehouse,vanName);
        this.inventory = inventory;
        this.warehouse = warehouse;
    }

    public Van(){
        inventory = new ArrayList<BikePart>();
        warehouse = new ArrayList<BikePart>();
        this.inventory = inventory;
        this.warehouse = warehouse;
    }

    public int getNumParts(){
        return warehouse.size();
    }

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

    /**
     * This method creates new text files with the name of the new van
     *
     * @param vanName name of Van
     *
     * @throws java.io.IOException
     */
    public static void createVan(String vanName) throws IOException{
        String fileName = vanName+"warehouse.txt";
        String fileName2 = vanName+"inventory.txt";
        File newVan = new File(fileName);
        File inventory = new File(fileName2);
        newVan.createNewFile();
        inventory.createNewFile();
    }

    /**
     * This method reads the van warehouse from a text file
     *
     * @param warehouse Van's warehouse ArrayList
     *
     * @param vanName Name of van
     */
    public static void readVanWarehouse(ArrayList<BikePart> warehouse, String vanName)
    {
        try {
            String partName;
            int partNum;
            double listPrice;
            double salePrice;
            boolean saleStatus;
            int quantity;
            File file = new File(vanName + "warehouse.txt");
            FileInputStream fileIn = new FileInputStream(file);
            Scanner scanIn = new Scanner(fileIn);
            /*if (file.length() == 0){
                ;
            }*/
            while (scanIn.hasNextLine()) {
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

    /**
     * This method reads the van inventory from a text file
     *
     * @param inventory Van's inventory arraylist
     *
     * @param vanName Name of van
     */
    public static void readVanInventory(ArrayList<BikePart> inventory, String vanName) {
        try {
            String partName;
            int partNum;
            double listPrice;
            double salePrice;
            boolean saleStatus;
            int quantity;
            FileInputStream fileIn = new FileInputStream(vanName + "inventory.txt");
            Scanner scanIn = new Scanner(fileIn);
            while (scanIn.hasNextLine()) {
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
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found. Please restart and enter new file name.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.print("IOException. Please restart.");
            e.printStackTrace();
        }
    }

    /**
     * this method will print the Van warehouse back out to the Warehouse text file
     *
     * @param v Van whose warehouse is being printed
     *
     * @throws FileNotFoundException
     *
     * @throws IOException
     */
    public static void printOutVanWarehouse(Van v) throws FileNotFoundException, IOException{
        FileOutputStream fileOut = new FileOutputStream(v.getVanName()+"warehouse.txt");
        PrintWriter out = new PrintWriter(fileOut);
        for(int i = 0; i < v.getWarehouse().size(); i++){
            out.println(v.getWarehouse().get(i).getPartName()+","+v.getWarehouse().get(i).getPartNum()+","+v.getWarehouse().get(i).getListPrice()+","+v.getWarehouse().get(i).getSalePrice()+","+v.getWarehouse().get(i).getSaleStatus()+","+v.getWarehouse().get(i).getQuantity());
        }
        out.flush();
        fileOut.close();
    }

    /**
     * this method will print the Van inventory back out to the inventory text file
     *
     * @param v Van whose inventory is being printed
     *
     * @throws FileNotFoundException
     *
     * @throws IOException
     */
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
     * this method compares a van's inventory to a van's warehouse and checks to see if there are parts that is needed.
     *
     * @param van1 Van that user wishes to move items to
     *
     * @param van2 Van that user wishes to take items from
     *
     * @param transport ArrayList that will move the specified items
     */
    public static void vanToVanComparison(Van van1, Van van2, ArrayList<BikePart> transport, int numPartsVan2){
        if (numPartsVan2 == 0){
            System.out.println(van2.getVanName() + " is empty.");
        }
        else {
            for (int i=0; i<van1.getInventory().size(); i++){
                String temp = van1.getInventory().get(i).getPartName();
                int quant = van1.getInventory().get(i).getQuantity();
                for (int j=0; j<van2.getWarehouse().size();j++){
                    String temp2 = van2.getWarehouse().get(j).getPartName();
                    if(temp.equals(temp2)){ //be aware of lower or uppercase AND TRIM!!!
                        int update = van2.getWarehouse().get(j).getQuantity() - quant;
                        if(update > 0){
                            van2.getWarehouse().get(j).setQuantity(update);
                            BikePart temp3 = van2.getWarehouse().get(j);
                            transport.add(temp3);
                        }
                        else if(update == 0 || update < 0){
                            BikePart temp3 = van2.getWarehouse().get(j);
                            transport.add(temp3);
                            van2.getWarehouse().remove(van2.getWarehouse().get(j));
                        }
                    }
                }
            }
        }
    }

    /**
     * This method updates a van's warehouse
     *
     * @param van1 Van that user wishes to move items to
     *
     * @param transport ArrayList that will move specified items
     */
    public static void vanUpdateWarehouse(Van van1, ArrayList<BikePart> transport, int numPartsVan1){
        for (int i=0; i<transport.size(); i++){
            String temp = transport.get(i).getPartName();
            if (numPartsVan1 == 0){
                for(int k=0; k<transport.size();k++){
                    BikePart part = transport.get(k);
                    van1.getWarehouse().add(part);
                }
            }
            else{
                for (int j=0; j<van1.getWarehouse().size();j++){
                    String temp2 = van1.getWarehouse().get(j).getPartName();
                    if(temp.equals(temp2)){ //be aware of lower or uppercase AND TRIM!!!
                        int quant = transport.get(i).getQuantity();
                        int quant2 = van1.getWarehouse().get(j).getQuantity();
                        van1.getWarehouse().get(j).setQuantity(quant+quant2);
                    }
                    else if(!(temp.equals(temp2)) && j == van1.getWarehouse().size() - 1){
                        BikePart part = transport.get(i);
                        van1.getWarehouse().add(part);
                    }
                }
            }
        }
    }

    /**
     * The method compares the Main warehouse with a van's warehouse and checks to see if there are parts that is needed.
     *
     * @param v Van requesting items from warehouse
     *
     * @param w Main warehouse
     *
     * @param transport2 ArrayList which will move specified items
     */
    public static void mainToVanComparison(Van v, Warehouse w, ArrayList<BikePart> transport2){
        if(w.getWarehouse().size() == 0){
            System.out.println("Warehouse is empty. Command unable to execute.");
        }
        else if(v.getInventory().size()==0){
            System.out.println("Inventory of "+v.getVanName()+" is empty. Command unable to execute.");
        }
        else{
            for (int i=0; i<v.getInventory().size(); i++){
                String temp = v.getInventory().get(i).getPartName();
                int quant = v.getInventory().get(i).getQuantity();
                for (int j=0; j<w.getWarehouse().size();j++){
                    String temp2 = w.getWarehouse().get(j).getPartName();
                    if(temp.equals(temp2)){ //be aware of lower or uppercase AND TRIM!!!
                        int update = w.getWarehouse().get(j).getQuantity() - quant;
                        if(update > 0){
                            w.getWarehouse().get(j).setQuantity(update);
                            BikePart temp3 = w.getWarehouse().get(j);
                            transport2.add(temp3);
                        }
                        else if(update==0 || update < 0){
                            BikePart temp3 = w.getWarehouse().get(j);
                            transport2.add(temp3);
                            w.getWarehouse().remove(w.getWarehouse().get(j));
                        }
                    }
                }
            }
        }
    }

    /**
     * This method updates a van's warehouseDB
     *
     * @param v Van that items are being moved to
     *
     * @param transport2 ArrayList which will move specified items
     */
    public static void updateVanhouseDB(Van v,ArrayList<BikePart> transport2,int numPartsVan){
        if (numPartsVan == 0){
            for(int k=0; k<transport2.size();k++){
                BikePart part = transport2.get(k);
                v.getWarehouse().add(part);
            }
        }
        else{
            for (int i=0; i<transport2.size(); i++){
                for (int j=0; j<v.getWarehouse().size();j++){
                    String name = transport2.get(i).getPartName();
                    String name2 = v.getWarehouse().get(j).getPartName();
                    if(name.equalsIgnoreCase(name2)){ //be aware of lower or uppercase AND TRIM!!!
                        int quant = transport2.get(i).getQuantity();
                        int quant2 = v.getWarehouse().get(j).getQuantity();
                        v.getWarehouse().get(j).setQuantity(quant+quant2);
                    }
                    else if(!(name.equals(name2)) && j == v.getWarehouse().size()){
                        BikePart part = transport2.get(i);
                        v.getWarehouse().add(part);
                    }
                }
            }
        }
    }

    @Override
    public int compareTo(Van o) {
        return this.vanName.compareToIgnoreCase(o.vanName);
    }

    public static void vanSortName(ArrayList<Van> fleet) {
        Collections.sort(fleet);
        for(int i=0; i<fleet.size(); i++){
            String str = new String(fleet.get(i).getVanName());
            System.out.println(str);
        }
    }

    /**
     * This method sorts through the part(s) in the van's inventory by number order
     * @param van1 Van that items are being sorted
     */
    public static void vanPartSortNumber(Van van1){
        ArrayList<BikePart> van = van1.getWarehouse();
        System.out.println("Sorting numbers from least to greatest...");
        Comparator partNumber = new ComparatorByPartNumber();
        Collections.sort(van, partNumber);
        for(BikePart bpt : van) {
            System.out.println(bpt.getPartName() + " " + bpt.getPartNum()
                    + " " + bpt.getListPrice() + " " + bpt.getSalePrice() + " " +
                    bpt.getSaleStatus() + " " + bpt.getQuantity());
        }
    }

    /**
     * This method sorts through the part(s) in the van's inventory by name order
     *
     * @param van1 Van that items are being sorted
     */
    public static void vanPartSortName(Van van1){
        ArrayList<BikePart> van = van1.getWarehouse();
        Collections.sort(van);
        for(int i=0; i<van.size(); i++){
            String str = new String(van.get(i).getPartName() + " " + van.get(i).getPartNum() + " " +
                    van.get(i).getListPrice() + " " + van.get(i).getSalePrice() + " " + van.get(i).getSaleStatus()
                    + " " + van.get(i).getQuantity());
            System.out.println(str);
        }
    }
}