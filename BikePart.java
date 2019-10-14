package com.company;
/**
 * BikePart represents the aspects that compose a piece of a bike and its 
 respective prices (part name, part number, list price, sale price,and if the part
 is on sale)
 *
 * @author Kerri Lynch
 * @version 9/10/19
 */
public class BikePart implements Comparable<BikePart>
{
    private String partName;
    private int partNum;
    private double listPrice;
    private double salePrice;
    private boolean onSale;
    private int quantity;
    /**
     * Constructor for objects of class BikePart
     */
    public BikePart(String partName, int partNum, double listPrice,double
            salePrice, boolean onSale,int quantity)
    {
        this.partName = partName;
        this.partNum = partNum;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.onSale = onSale;
        this.quantity = quantity;
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
    public int getQuantity(){
        return this.quantity;
    }
    public void setPartName(String partName){
        this.partName = partName;
    }
    public void setPartNum(int partNum){
        this.partNum = partNum;
    }
    public void setListPrice(double listPrice){
        this.listPrice = listPrice;
    }
    public void setSalePrice(double salePrice){
        this.salePrice = salePrice;
    }
    public void setSaleStatus(boolean onSale){
        this.onSale = onSale;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public void printInfo(){
        System.out.println(partName+","+partNum+","+listPrice+","+salePrice+","+onSale+","+
                quantity);
    }

    public int compareTo(BikePart o) {
        int compareInt = this.partName.compareTo(o.partName);
        if (compareInt < 0) return -1;
        if (compareInt > 0) return 1;
        return 0;
    }

}