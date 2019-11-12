package com.mycompany.cpscproject;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
/**
 * Write a description of class LoginAccount here.
 *
 * @author Chy and Kerri
 * @version 1
 */
public class LoginAccount 
{
    // instance variables - replace the example below with your own
    protected String username;
    protected String password;
    protected Person user;
    protected byte[] encryptedPassword;
   

    /**
     * Constructor for objects of class LoginAccount
     */
    public LoginAccount(Person user,String username, String password)
    {
        username = this.username;
        password = this.password;
        user = this.user;
        encryptedPassword = scramblePassword(password);
    }

    public LoginAccount(){
        username = "user123";
        password = "testing123";
        user = new Person();
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public static byte[] scramblePassword(String password){
        Random r = new Random();
        final byte[] nbyte = new byte[20];
        r.nextBytes(nbyte);
        final byte[] scrambledPassword;
        final int iterations = 5;
        final int keyLength = 256;
       return scrambledPassword = hashPassword(password.toCharArray(), nbyte, iterations, keyLength);
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
}

