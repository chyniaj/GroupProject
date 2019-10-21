import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.time.Instant;
/**
 * Write a description of class Warehouse here.
 *
 * @lewis (your name)
 * @version (a version number or a date)
 */
public class Warehouse
{
    private String partName;
    ArrayList<BikePart> inWarehouse= new ArrayList<BikePart>();
    ArrayList<Inventory> Deliver = new ArrayList<Inventory>();

    /**
     * @param parameter partName- the name of the bicycle part
     *
     */
    public void displayPart(String partName)//method displays bicycle part name
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
                    inWarehouse.get(i).getQuantity()//prints out each information of the bicycle
                );

            }
            else{
                System.out.println(name + " is not in file");
            }
        }

    }

    /**
     * @param parameter BikePart part-  bicycle part information from the arraylist BikePart
     */
    public void addPart(BikePart part)
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

    public void Sell(int partNum)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("How many do you want to sell?");
        int number = input.nextInt();
        int quant;

        Instant now=Instant.now();
        System.out.println(now.toString());
        if(userInput.equals("Sell"))
        {
            for(int i =0; i < inWarehouse.size(); i++){

                if(inWarehouse.get(i).getPartNum() == (partNum))
                {
                    System.out.println(inWarehouse.get(i).getPartName()+" "+
                        inWarehouse.get(i).getPartNum()+" "+inWarehouse.get(i).getQuantity());

                }
                if(inWarehouse.get(i).getPartNum() <= number) 
                {
                    quant = inWarehouse.get(i).getQuantity()- number;
                    System.out.println(inWarehouse.get(i).getPartName()+" "+
                        inWarehouse.get(i).getPartNum()+" "+inWarehouse.get(i).getQuantity());
                }
                else {
                    System.out.println("Not enough quantity on hand" +inWarehouse.get(i).getQuantity());
                }

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
