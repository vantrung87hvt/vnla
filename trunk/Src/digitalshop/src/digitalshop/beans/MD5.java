package digitalshop.beans;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: admin
 */
public class MD5 {

    private static MessageDigest md;

    public static String encode(String stPassword_)  {
        try {
            String text = stPassword_;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(text.getBytes());
            BigInteger dis = new BigInteger(1, md5.digest());
            text = dis.toString(16);
            return text;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}