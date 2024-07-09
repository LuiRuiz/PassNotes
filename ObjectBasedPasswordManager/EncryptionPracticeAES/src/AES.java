import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

/**
 * Possible KEY_SIZE values are 128, 192 and 256
 * Possible T_LEN values are 128, 120, 112, 104 and 96
 */

public class AES {
    private SecretKey key;
    String keyArray;
    private final int KEY_SIZE = 128;
    private final int T_LEN = 128;
    private byte[] IV;
    public byte[] passIV;
   public SecretKey passBasedSecret;

    public void init() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        key = generator.generateKey();
    }

    public void setPassWord(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        String salt = "1234567890123456";
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);

        passBasedSecret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");

    }

    public String encrypt(String message) throws Exception {
        byte[] messageInBytes = message.getBytes();
        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        IV = encryptionCipher.getIV();

        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
        return encode(encryptedBytes);
    }
    public void encryptList(ArrayList <ClassForList> list) throws Exception {

        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        IV = encryptionCipher.getIV();
        CipherOutputStream cipherOutputStream = null;
        cipherOutputStream = new CipherOutputStream(new BufferedOutputStream(new FileOutputStream("testObject.txt")), encryptionCipher);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(cipherOutputStream);
        objectOutputStream.writeObject(list);
        objectOutputStream.close();

    }
    public String encryptKey() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] mainKeyInBytes = keyArray.getBytes();

        Cipher encrptionCipher = Cipher.getInstance("AES/GCM/NoPadding");

        encrptionCipher.init(Cipher.ENCRYPT_MODE,passBasedSecret);

        passIV = encrptionCipher.getIV();
        byte[]encryptedBytes = encrptionCipher.doFinal(mainKeyInBytes);

        return encode(encryptedBytes);
    }
    public String decryptKey (String encryptedKey) throws Exception{
        byte[] keyInBytes = decode(encryptedKey);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN,passIV );
        decryptionCipher.init(Cipher.DECRYPT_MODE, passBasedSecret, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(keyInBytes);
        return new String(decryptedBytes);
    }
    public String decryptKey (String encryptedKey, String masterIV) throws Exception{
        byte[] keyInBytes = decode(encryptedKey);
        byte[] masterIVBytes = masterIV.getBytes();
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN,passIV );
        decryptionCipher.init(Cipher.DECRYPT_MODE, passBasedSecret, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(keyInBytes);
        return new String(decryptedBytes);
    }
    public String decryptKeyAgain (String encryptedKey, String masterIV, SecretKey newKey) throws Exception{

        byte[] keyInBytes = decode(encryptedKey);
        byte[] masterIVBytes = masterIV.getBytes();
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128,masterIVBytes );

        decryptionCipher.init(Cipher.DECRYPT_MODE, newKey, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(keyInBytes);
        return new String(decryptedBytes);
    }

    public String decrypt(String encryptedMessage, String setKeyString, String setIVString) throws Exception {
        SecretKey setKey= new SecretKeySpec(decode(setKeyString),"AES");
        byte[]setIV = decode(setIVString);

        byte[] messageInBytes = decode(encryptedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN,setIV );
        decryptionCipher.init(Cipher.DECRYPT_MODE, setKey, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        return new String(decryptedBytes);
    }

    public void exportKeys(){
        keyArray = encode(key.getEncoded());
        System.err.println("Key: "+ keyArray);
        System.err.println("IV: " + encode(IV));
    }

    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public void sendEncryptedKey(String encryptedKey){
        try {
            FileWriter myFile = new FileWriter("EncryptedKeyTest.txt");
            myFile.write(encryptedKey);
            myFile.close();

             myFile = new FileWriter("IVTest.txt");
             String sendIV= encode(passIV)+encode(IV);
             myFile.write(sendIV);
             myFile.close();






        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public String[] readEncryptedKey(){
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