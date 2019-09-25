import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
/**
 * Write a description of class Warehouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Warehouse
{
    private ArrayList<BikePart> inWarehouse;
    private static int numParts;
    /**
     * Constructor for objects of class Warehouse
     */
    public Warehouse()
    {
        ArrayList<BikePart> inWarehouse = new ArrayList<BikePart>();
        this.numParts = readDatabase(inWarehouse);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  inWarehouse  ArrayList that will contain list of bike parts in the warehouse
     * @return void   
     */
    public static int readDatabase(ArrayList inWarehouse) 
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
                BikePart part = new BikePart(partName, partNum, listPrice, salePrice, saleStatus, quantity);
                inWarehouse.add(part);
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
}
