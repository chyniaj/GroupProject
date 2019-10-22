import java.util.Comparator;

public class ComparatorByPartNumber implements Comparator<BikePart> {
    @Override
    public int compare(BikePart num1, BikePart num2) {
        //int compareInt = (num1.getPartNum().compareTo(num2.getPartNum()));;
        //if (compareInt < 0) return -1;
        //if (compareInt > 0) return 1;
        //return 0;
        
        
       if(num1.getPartNum() < (num2.getPartNum())) {
           return -1;
        }
       if(num1.getPartNum() > (num2.getPartNum())) {
           return 1;
        }
        return 0;
    }
}