import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;


public class FileHandlerCipher {


    public void sendEncryptedKey(String encryptedKey){

//        try {
////            FileWriter myFile = new FileWriter("EncryptedKeyTest.txt");
////            myFile.write(encryptedKey);
////            myFile.close();
////
////            myFile = new FileWriter("IVTest.txt");
////            String sendIV= encode(passIV);//+encode(IV);
////            myFile.write(sendIV);
////            myFile.close();
//
//
//
//
//
//
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }

    }
    public static String[] readEncryptedKey(){
        String pass = null;
        try {
            pass = new Scanner(new File("EncryptedKeyTest.txt")).useDelimiter("\\Z").next();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String IV = null;
        try {
            IV = new Scanner(new File("IVTest.txt")).useDelimiter("\\Z").next();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new String[]{pass, IV};
    }



}
