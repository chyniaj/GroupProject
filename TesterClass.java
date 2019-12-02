package com.company;

import java.io.IOException;
import java.util.*;

public class TesterClass {

    public static void main(String[] args) throws IOException {
        Inventory inventory = new Inventory();
        Warehouse warehouseDB = new Warehouse();
        WarehouseManager warehouseManager = new WarehouseManager("djay", "yajd");
        //SystemAdministrator systemAdmin = new SystemAdministrator();
        OfficeManager officeManager = new OfficeManager("uman", "namu");
        SalesAssociate salesAssociate = new SalesAssociate("were", "erew");
        SalesAssociate sa = new SalesAssociate("were", "erew");
        Van v = new Van();
        Map<String, BikePart> bpByNameMap = new HashMap<>();
        Map<Integer, BikePart> bpByNumberMap = new HashMap<>();
        toMap(warehouseDB, bpByNameMap);
        toMapNumber(warehouseDB, bpByNumberMap);
        Scanner start = new Scanner(System.in);
        System.out.println("Welcome! Please enter the command you'd like to execute or enter 'Commands' for list of commands.");
        while (start.hasNext()) {
            String userInput = start.next();
            String input;
            if (userInput.equalsIgnoreCase("Commands")) {
                System.out.println("OfficeManager"+"\n"+"WarehouseManager"+"\n"+"SalesAssociate"+"\n"+"Quit - quit program.");
                System.out.println("Please enter the command you would like to execute.");
                input = start.next();
                if (input.equalsIgnoreCase("Quit")) {
                    System.out.println("Updating and exiting...");
                    System.exit(0);
                }
            }else if(userInput.equalsIgnoreCase("SystemAdministrator")){
                //systemAdmin.printInfo();
            }else if(userInput.equalsIgnoreCase("OfficeManager")){
                officeManager.printInfo(bpByNameMap, bpByNumberMap);
            }else if(userInput.equalsIgnoreCase("WarehouseManager")){
                warehouseManager.printInfo(bpByNameMap, bpByNumberMap);
            }else if(userInput.equalsIgnoreCase("SalesAssociate")){
                salesAssociate.printInfo(sa, v, warehouseDB, bpByNameMap);
            }
        }
    }
    public static void toMap(Warehouse w, Map<String,BikePart> bpByPartName){
        for(int i=0; i< w.getWarehouse().size(); i++){
            String tempName = w.getWarehouse().get(i).getPartName();
            BikePart temp = w.getWarehouse().get(i);
            bpByPartName.put(tempName,temp);
        }
    }
    public static void toMapNumber(Warehouse w, Map<Integer,BikePart> bpByPartNumber){
        for(int i=0; i< w.getWarehouse().size(); i++){
            Integer tempNum = w.getWarehouse().get(i).getPartNum();
            BikePart temp = w.getWarehouse().get(i);
            bpByPartNumber.put(tempNum,temp);
        }
    }
}
