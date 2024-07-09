import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.security.CryptoPrimitive;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;


public class Decrypt {
    SecretKey key;
    byte[] IV;
    private int KEY_SIZE = 128;
    private int T_LEN = 128;

    public void fromStrings(String SecretKey, String theIV){
        key = new SecretKeySpec(decode(SecretKey),"AES");
        this.IV = decode(theIV);
    }
    public String decrypt(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] messageInBytes = decode(message);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = cipher.doFinal(messageInBytes);
        return new String(decryptedBytes);

    }

    //initialize key first Cipher Made here with String IV
    public ArrayList<ClassForList> decryptList () throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IOException, ClassNotFoundException {

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        //createCipherInputStream
        CipherInputStream cipherInputStream = null;
        cipherInputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream("testObject.txt")),cipher);
        //create objectInputStream
        ObjectInputStream objectInputStream = new ObjectInputStream(cipherInputStream);

        return (ArrayList<ClassForList>) objectInputStream.readObject();


    }
    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        Decrypt readTest = new Decrypt();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Password:");

        String salt = "1234567890123456";
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec((input.next()).toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey masterPass = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");

        String MasterKeyString = AES.encode(masterPass.getEncoded());

        String[] keyAndIVs = FileHandlerCipher.readEncryptedKey();

        String MasterIVString = keyAndIVs[1].substring(0,16);

        readTest.fromStrings(MasterKeyString,MasterIVString);

        String key = readTest.decrypt(keyAndIVs[0]);

        System.out.println(key);

        Decrypt messageList = new Decrypt();
        String subIV = keyAndIVs[1].substring(16);
        messageList.fromStrings(key,subIV );
        //forms key and IV for object

        ArrayList<ClassForList>readStudents = null;
        try {
            readStudents = messageList.decryptList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (ClassForList item : readStudents){
            System.out.println(item.name);
        }










    }


}
