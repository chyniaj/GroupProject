package com.mycompany.cpscproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 * Write a description of class LoginAccount here.
 *
 * @author Chy and Kerri
 * @version 1
 */
public class LoginAccount 
{
    private Person person;
    private String username;
    private byte[] password;
     Random r = new Random();
     byte[] nbyte = new byte[32];


    public LoginAccount(){

    }
    public LoginAccount(String username, String password) {
        //this.person = person;
        this.username = username;
        r.nextBytes(nbyte);
        this.password = hashPassword(password.toCharArray(),nbyte,5,256);
    }
    public String getUsername()
    {
        return this.username;
    }

    public byte[] getPassword(){
        return this.password;
    }
    

    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try{
        SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
        PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
        SecretKey key = skf.generateSecret( spec );
        byte[] res = key.getEncoded( );
        return res;
    
    } 
    catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
        throw new RuntimeException( e );
    }
    }
    
     public String logIn(String userName, String Password){
        String logIn = "false";
        byte[] newPass = hashPassword(Password.toCharArray(),nbyte,5,256);
        if ((username.equals(this.getUsername())) && (Arrays.equals(this.password, newPass))) {
            logIn = "true";
            return logIn;
        }
        else{
            return logIn;
        }
}
     public static void readInWM(ArrayList<WarehouseManager> warehouseManagers) throws IOException{
         try{
         String tempUser;
         String tempPassword;
         Scanner input = new Scanner(System.in);
         System.out.println("Enter name of file containing the Warehouse Manager information:");
         String fileName = input.next();
         
         File file = new File(fileName+".txt");
         FileInputStream fileIn = new FileInputStream(file);
         
         Scanner scanIn = new Scanner(fileIn);
            while(scanIn.hasNextLine()){
                String info = scanIn.nextLine();
                info = info.trim();
                String[] infoHolder = info.split(",");
                tempUser = infoHolder[0];
                tempPassword = infoHolder[1];
                WarehouseManager staff = new WarehouseManager(tempUser,tempPassword);
                warehouseManagers.add(staff);
            }
            fileIn.close();
         }
            catch (FileNotFoundException e){
            System.out.println("File Not Found. Please restart and enter new file name.");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.print("IOException. Please restart.");
            e.printStackTrace();
        }
        }
     
          public static void readInOM(ArrayList<OfficeManager> list) throws IOException{
         try{
         String tempUser;
         String tempPassword;
         Scanner input = new Scanner(System.in);
         System.out.println("Enter name of file containing the Office Manager information:");
         String fileName = input.next();
         
         File file = new File(fileName+".txt");
         FileInputStream fileIn = new FileInputStream(file);
         
         Scanner scanIn = new Scanner(fileIn);
            while(scanIn.hasNextLine()){
                String info = scanIn.nextLine();
                info = info.trim();
                String[] infoHolder = info.split(",");
                tempUser = infoHolder[0];
                tempPassword = infoHolder[1];
                OfficeManager staff = new OfficeManager(tempUser,tempPassword);
                list.add(staff);
            }
            fileIn.close();
         }
            catch (FileNotFoundException e){
            System.out.println("File Not Found. Please restart and enter new file name.");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.print("IOException. Please restart.");
            e.printStackTrace();
        }
        }
         public static void readInSA(ArrayList<SalesAssociate> list){
         try{
         String tempUser;
         String tempPassword;
         double totalEarnings;
         Scanner input = new Scanner(System.in);
         System.out.println("Enter name of file containing the Sales Associate information:");
         String fileName = input.next();
         
         File file = new File(fileName+".txt");
         FileInputStream fileIn = new FileInputStream(file);
         
         Scanner scanIn = new Scanner(fileIn);
            while(scanIn.hasNextLine()){
                String info = scanIn.nextLine();
                info = info.trim();
                String[] infoHolder = info.split(",");
                tempUser = infoHolder[0];
                tempPassword = infoHolder[1];
                totalEarnings = Double.parseDouble(infoHolder[2]);
                SalesAssociate staff = new SalesAssociate(tempUser,tempPassword);
                staff.setTotalEarnings(totalEarnings);
                list.add(staff);
            }
            fileIn.close();
        }
        
        catch (FileNotFoundException e){
            System.out.println("File Not Found. Please restart and enter new file name.");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.print("IOException. Please restart.");
            e.printStackTrace();
        }

     }
     
     public static void printOutStaff(ArrayList<LoginAccount> staff) throws FileNotFoundException{
         FileOutputStream fileOut = new FileOutputStream("SalesInvoice.txt");
         PrintWriter out = new PrintWriter(fileOut);
     }
}
        
