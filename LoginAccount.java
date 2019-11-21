package com.mycompany.cpscproject;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
/**
 * Write a description of class LoginAccount here.
 *
 * @author Chy and Kerri
 * @version 1
 */
public class LoginAccount 
{
       //private Person person;
    private String username;
    private byte[] password;
     Random r = new Random();
     byte[] nbyte = new byte[32];


    public LoginAccount(){

    }
    public LoginAccount( String username, String password) {
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
        System.out.println(Arrays.toString(newPass));
        System.out.println(Arrays.toString(this.password));
        System.out.println(Arrays.equals(this.password, newPass));
        if ((username.equals(this.getUsername())) && (Arrays.equals(this.password, newPass))) {
            logIn = "true";
            return logIn;
        }
        else{
            return logIn;
        }
}
}
        
