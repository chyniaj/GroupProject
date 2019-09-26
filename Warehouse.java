
package com.company;
import java.util.ArrayList;

public class Warehouse {

    public static void sortNumber(ArrayList<BikePart> list){

        int x = list.size();
        for (int i = 0; i < x; i++){
            for(int j = 0; j < x; j++){
                if (list.get(i).getPartNum() > list.get(j).getPartNum()) {
                    BikePart temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

    }
    public static void sortName(ArrayList<BikePart> list) {
        int x = list.size();
        for (int i = 0; i < x; i++){
            for (int j = 0; j < x; j++){
                if (list.get(i).getPartName().compareTo(list.get(j).getPartName()) > 0) {
                    BikePart temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
                else if (list.get(i).getPartName().compareTo(list.get(j).getPartName()) == 0) {
                    BikePart temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
                else if (list.get(i).getPartName().compareTo(list.get(j).getPartName()) < 0) {
                    BikePart temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}
