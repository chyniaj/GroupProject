/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cpscproject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kerri
 */
public class SalesInvoice {

    private ArrayList<BikePart> selling;
    
    public SalesInvoice(ArrayList<BikePart> selling){
        this.selling = selling;
        
    }
    
    public ArrayList<BikePart> getInvoice(){
        return selling;
    }
    
    public static void printSalesInvoice(SalesInvoice invoice) throws FileNotFoundException, IOException{
        Scanner identiScan = new Scanner(System.in);
        String identifier;
        FileOutputStream fileOut = new FileOutputStream("SalesInvoice");
        PrintWriter out = new PrintWriter(fileOut);
        Instant timestamp = Instant.now();
        double totalCost;
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        
        out.printf("Sales Invoice for Bike Distributorship, %s %d %d at %d:%d%n",ldt.getMonth(), ldt.getDayOfMonth(), ldt.getYear(), ldt.getHour(), ldt.getMinute());
        out.println("Part        Number        List Price       Sale Price      Sale Status         Quantity        Total Cost");
        for(int i = 0; i < invoice.getInvoice().size(); i++){
            if(invoice.getInvoice().get(i).getSaleStatus() == true){
                totalCost = invoice.getInvoice().get(i).getQuantity() * invoice.getInvoice().get(i).getSalePrice();
            }
            else{
                totalCost = invoice.getInvoice().get(i).getQuantity() * invoice.getInvoice().get(i).getListPrice();
            }
            out.println(invoice.getInvoice().get(i).getPartName()+",      "+invoice.getInvoice().get(i).getPartNum()+",         "+invoice.getInvoice().get(i).getListPrice()+",        "+invoice.getInvoice().get(i).getSalePrice()+",        "+invoice.getInvoice().get(i).getSaleStatus()+",        "
                +invoice.getInvoice().get(i).getQuantity()+"              "+ totalCost);
                
        }
        out.println("Received by: Kerri and Chy");
        out.flush();
        fileOut.close();
    }
}
