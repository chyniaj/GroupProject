package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;
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
        FileOutputStream fileOut = new FileOutputStream("SalesInvoice.txt");
        PrintWriter out = new PrintWriter(fileOut);
        Instant timestamp = Instant.now();
        double totalCost;
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());

        out.printf("Sales Invoice for Bike Distributorship, %s %d %d at %d:%d%n",ldt.getMonth(), ldt.getDayOfMonth(), ldt.getYear(), ldt.getHour(), ldt.getMinute());
        String part = "Part";
        String num = "Number";
        String lp = "List Price";
        String sp = "Sale Price";
        String sStatus = "Sale Status";
        String quant = "Quantity";
        String tc = "Total Cost";
        out.printf("%1s %17s %20s %13s %13s %10s %16s", part, num, lp, sp, sStatus, quant, tc);
        out.println();//this is here for the new line setup in the file
        for(int i = 0; i < invoice.getInvoice().size(); i++){
            if(invoice.getInvoice().get(i).getSaleStatus() == true){
                totalCost = invoice.getInvoice().get(i).getQuantity() * invoice.getInvoice().get(i).getSalePrice();
            }
            else{
                totalCost = invoice.getInvoice().get(i).getQuantity() * invoice.getInvoice().get(i).getListPrice();
            }
            String part1 = invoice.getInvoice().get(i).getPartName();
            int num1 = invoice.getInvoice().get(i).getPartNum();
            double lp1 = invoice.getInvoice().get(i).getListPrice();
            double sp1 = invoice.getInvoice().get(i).getSalePrice();
            boolean ss1 = invoice.getInvoice().get(i).getSaleStatus();
            int quant1 = invoice.getInvoice().get(i).getQuantity();
            out.printf("%1s %17s %16s %13s %13s %10s %16s", part1, num1, lp1,sp1,ss1,quant1,totalCost);
            out.println();//this is here for the new line setup in the file
            ///WARNING! the formatting does not line up well with the mensBibsMedium item
        }
        out.println("Received by: Kerri and Chy");
        out.flush();
        fileOut.close();
    }
}

