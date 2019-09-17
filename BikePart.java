
/**
 * BikePart represents the aspects that compose a piece of a bike and its respective prices (part name, part number, list price, sale price,and if the part is on sale)
 *
 * @author Kerri Lynch
 * @version 9/10/19
 */
public class BikePart
{
    private String partName;
    private int partNum;
    private double listPrice;
    private double salePrice;
    private boolean onSale;

    /**
     * Constructor for objects of class BikePart
     */
    public BikePart(String partName, int partNum, double listPrice,double salePrice, boolean onSale)
    {
        this.partName = partName;
        this.partNum = partNum;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.onSale = onSale;

    }

    public String getPartName()
    {
        return this.partName;
    }

    public int getPartNum()
    {
        return this.partNum;
    }

    public double getListPrice(){
        return this.listPrice;
    }

    public double getSalePrice(){
        return this.salePrice;
    }

    public boolean getSaleStatus(){
        return this.onSale;
    }
    
    public void setPartName(){
        this.partName = partName;
    }
    
    public void setPartNum(){
        this.partNum = partNum;
    }
    
    public void setListPrice(){
        this.listPrice = listPrice;
    }
    
    public void setSalePrice(){
        this.salePrice = salePrice;
    }
    
    public void setSaleStatus(){
        this.onSale = onSale;
    }

    public void printInfo(){
        System.out.println(partName+","+partNum+","+listPrice+","+salePrice+","+onSale);
    }

}
