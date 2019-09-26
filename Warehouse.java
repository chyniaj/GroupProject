import java.util.ArrayList;
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
            if(name.equals(inWarehouse.get(i).getName()))
            {
                System.out.println(inWarehouse.get(i).printInfo());
            }
            else{
                System.out.println("Name is not in file");
            }
        }

    }

    public void addPart(BikePart part)
    {
        for ( int j =0; j < Deliver.size(); j++)
        {if(Deliver.get(j).size().equals(inWarehouse.get(j).part()))
            {
                Deliver.get(j).size()= inWarehouse.get(j).part();
            }
            else if(!Deliver.get(j).size().equals(inWarehouse.get(j).part()))
            {
                Deliver.add(part);
            }

        }
        

    }
}
