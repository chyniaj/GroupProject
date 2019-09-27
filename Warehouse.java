import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Write a description of class Warehouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Warehouse
{
    private String partName;
    ArrayList<BikePart> inWarehouse= new ArrayList<BikePart>();
    ArrayList<Inventory> Deliver = new ArrayList<Inventory>();

    public void displayPart(String partName)
    {
        String name = partName;
        for(int i =0; i < inWarehouse.size(); i++){
            if(name.equals(inWarehouse.get(i).getPartName()))
            {
                //inWarehouse.get(i).printInfo();
                System.out.println(
                    inWarehouse.get(i).getPartName()+" "+
                    inWarehouse.get(i).getPartNum()+" "+
                    inWarehouse.get(i).getListPrice()+" "+
                    inWarehouse.get(i).getSalePrice()+" "+
                    inWarehouse.get(i).getSaleStatus()+" "+
                    inWarehouse.get(i).getQuantity()
                );

            }
            else{
                System.out.println(name + " is not in file");
            }
        }

    }

    public void addPart( BikePart part)
    {
        int localpartnum = part.getPartNum();
        int Quantity = 0;
        
        for ( int j =0; j < inWarehouse.size(); j++)
        {if(localpartnum != inWarehouse.get(j).getPartNum())
            {
                BikePart localBikePart = new BikePart(part.getPartName(), part.getPartNum(),part.getListPrice(), 
                part.getSalePrice(), part.getSaleStatus(), part.getQuantity());
                
                inWarehouse.add(localBikePart);
                
            }
            else 
            {
                Quantity = inWarehouse.get(j).getQuantity() + part.getQuantity();
                BikePart localBikePart = new BikePart(part.getPartName(), part.getPartNum(),part.getListPrice(), 
                part.getSalePrice(), part.getSaleStatus(), part.getQuantity());
                
                inWarehouse.set(j, localBikePart);
                
            }

        }

    }

    public void printInfo()
    {
        try{
            System.out.println("Enter Output File: ");
            Scanner y = new Scanner(System.in);
            String outputfilename = y.nextLine();
            File outputfn = new File(outputfilename);

            PrintWriter outputfile = new PrintWriter(outputfn);

            outputfile.println(partName);

            outputfile.close(); 
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found.");

        }
    }
}
