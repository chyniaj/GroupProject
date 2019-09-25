import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
/**
 * Write a description of class TesterClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TesterClass
{
    public static void main(String[] args) throws IOException{
        //we need to print the instructions
        Scanner start = new Scanner(System.in);
        String userInput = start.next();
        
        //variables needed to execute commands (this is so they don't get lost once block ends
        Inventory newIn = new Inventory();
        BikePart test = new BikePart("test",1234,44.00,34.00,true,3);
        if(userInput.equals("Delivery")){
        }
        else if(userInput.equals("Display")){
        }

    }
}
