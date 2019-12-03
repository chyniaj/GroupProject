package com.company;

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
    private String username;
    private byte[] password;
    private String passwordHolder;
    Random r = new Random();
    byte[] nbyte = new byte[32];


    public LoginAccount(){

    }
    public LoginAccount(String username, String password) {
        //this.person = person;
        this.passwordHolder = password;
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

    public String getPasswordHolder(){
        return this.passwordHolder;
    }

    /**
     * This method hashes a password so it is more private
     * @param password
     * @param salt
     * @param iterations
     * @param keyLength
     * @return
     */
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

    /**
     * This method is used to log in each person to their respective accounts.
     * @param userName
     * @param Password
     * @return
     */
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

    /**
     * This method reads in the Warehouse Managers file
     * @param warehouseManagers
     * @throws IOException
     */
    public static void readInWM(ArrayList<WarehouseManager> warehouseManagers) throws IOException{
        try{
            String tempUser;
            String tempPassword;
            //Scanner input = new Scanner(System.in);
            //System.out.println("Enter name of file containing the Warehouse Manager information:");
            //String fileName = input.next();

            File file = new File("WarehouseManagers.txt");
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
        catch(FileNotFoundException e){
            System.out.println("File Not found. Please restart and enter new file name.");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("IOException. Please restart.");
            e.printStackTrace();
        }
    }

    /**
     * This method reads in the Office Managers file.
     * @param list
     * @throws IOException
     */
    public static void readInOM(ArrayList<OfficeManager> list) throws IOException{
        try{
            String tempUser;
            String tempPassword;
            //Scanner input = new Scanner(System.in);
            //System.out.println("Enter name of file containing the Office Manager information:");
            //String fileName = input.next();

            File file = new File("OfficeManagers.txt");
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
        catch(FileNotFoundException e){
            System.out.println("File Not found. Please restart and enter new file name.");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("IOException. Please restart.");
            e.printStackTrace();
        }
    }

    /**
     * This file reads in the Sales Associates file.
     * @param list
     */
    public static void readInSA(ArrayList<SalesAssociate> list){
        try{
            String tempUser;
            String tempPassword;
            double totalEarnings;
            //Scanner input = new Scanner(System.in);
           // System.out.println("Enter name of file containing the Sales Associate information:");
            //String fileName = input.next();

            File file = new File("SalesAssociates.txt");
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

    /**
     * This method prints out to the Warehouse Managers' file.
     * @param staff
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void printOutWM(ArrayList<WarehouseManager> staff) throws FileNotFoundException, IOException{
        //Scanner scanOut = new Scanner(System.in);
        //System.out.println("Please enter the name of them Warehouse Manager information file you wish to update (do not include .txt)");
        //String ans = scanOut.next();
        FileOutputStream fileOut = new FileOutputStream("WarehouseManagers.txt");
        PrintWriter out = new PrintWriter(fileOut);
        for (int i = 0; i< staff.size(); i++){
            out.println(staff.get(i).getUsername() + "," + staff.get(i).getPasswordHolder());
        }
        out.flush();
        fileOut.close();
    }

    /**
     * This method prints out to the Office Managers' file.
     * @param staff
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void printOutOM(ArrayList<OfficeManager> staff) throws FileNotFoundException, IOException{
        //Scanner scanOut = new Scanner(System.in);
        //System.out.println("Please enter the name of the Office Manager information file you wish to update (do not include .txt)");
        //String ans = scanOut.next();
        FileOutputStream fileOut = new FileOutputStream("OfficeManagers.txt");
        PrintWriter out = new PrintWriter(fileOut);
        for (int i = 0; i< staff.size(); i++){
            out.println(staff.get(i).getUsername() + "," + staff.get(i).getPasswordHolder());
        }
        out.flush();
        fileOut.close();
    }

    /**
     * This method prints out the Sales Associates' file.
     * @param staff
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void printOutSA(ArrayList<SalesAssociate> staff) throws FileNotFoundException, IOException{
        //Scanner scanOut = new Scanner(System.in);
        //System.out.println("Please enter the name of the Sales Associate information file you wish to update (do not include .txt)");
        //String ans = scanOut.next()
        FileOutputStream fileOut = new FileOutputStream("SalesAssociates.txt");
        PrintWriter out = new PrintWriter(fileOut);
        for (int i = 0; i< staff.size(); i++){
            out.println(staff.get(i).getUsername() + "," + staff.get(i).getPasswordHolder() + "," + staff.get(i).getTotalEarnings());
        }
        out.flush();
        fileOut.close();
    }
}
