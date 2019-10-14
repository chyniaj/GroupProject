package com.company;
import java.util.Comparator;

public class ComparatorByPartNumber implements Comparator<BikePart> {
    @Override
    public int compare(BikePart num1, BikePart num2) {
        int compareInt = num1.compareTo(num2);
        if (compareInt < 0) return -1;
        if (compareInt > 0) return 1;
        return 0;
    }
}
