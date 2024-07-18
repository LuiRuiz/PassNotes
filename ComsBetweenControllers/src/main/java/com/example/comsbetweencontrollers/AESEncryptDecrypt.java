package com.example.comsbetweencontrollers;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class AESEncryptDecrypt {



        private byte[] passIV;
        private byte[] keyArray;
        private byte[] FileIV;
        private final int KEY_SIZE = 128;

        private SecretKey key;
        private SecretKey masterPass;

        /*
        - Generator That Creates the Key IV and MASTER IV
        - ONLY CALL on first iteration
        -
        -USES: keyGenerator(), init(), generateKey(), encyptKey(),and SendPassAndIV()
        -
         */
        public void keyGenerator(String Password) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
            init(Password);
            generateKey();
            sendPassAndIV();
        }

        /*
        - Type: Initializer Function
        - Creates passkey and IV for fileKey encription
         */
        public void init(String Password) {
            String salt = "1234567890123456";
            SecretKeyFactory factory = null;
            try {
                factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            KeySpec spec = new PBEKeySpec(Password.toCharArray(), salt.getBytes(), 65536, 256);
            try {
                masterPass = new SecretKeySpec(factory.generateSecret(spec)
                        .getEncoded(), "AES");
               // System.out.println("Intial MasterPass key" + encode(masterPass.getEncoded()));
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }

        }

        /*
        -Type: Intializer Function
        -Generates fileKey and IV
         */
        private void generateKey() {
            KeyGenerator generator = null;
            try {
                generator = KeyGenerator.getInstance("AES");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            generator.init(KEY_SIZE);
            key = generator.generateKey();
            try {
                //just to get IV
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                FileIV = cipher.getIV();

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        }

        /*
        -Type: Initalizer Function
        -Encrypts fileKey with MasterKey and masterIV
         */
        public String encryptKey() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

            byte[] mainKeyInBytes = key.getEncoded();

            Cipher encrptionCipher = Cipher.getInstance("AES/GCM/NoPadding");

            encrptionCipher.init(Cipher.ENCRYPT_MODE, masterPass);

            passIV = encrptionCipher.getIV();
            //System.out.println("MASTERIV: " + encode(passIV));
            byte[] encryptedBytes = encrptionCipher.doFinal(mainKeyInBytes);

            return encode(encryptedBytes);
        }


        public void sendPassAndIV() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
            try {
                FileWriter myFile = new FileWriter("EncryptedKeyTest.txt");
                myFile.write(encryptKey());
                myFile.close();

                myFile = new FileWriter("IVTest.txt");
                String sendIV = encode(passIV) + encode(FileIV);
                myFile.write(sendIV);
                myFile.close();


            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }

        public byte[] getMasterPassKey() {
            //System.out.println("MASTERKEY FROM FUNCTION: " + encode(masterPass.getEncoded()));
            return masterPass.getEncoded();
        }

        public static String[] readEncryptedKey() {
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


        /*
        Un ecnrypts key with masterKey byte and MasterIV
         */
        public static byte[] decyptKey(String encryptedKey, String masterIV, byte[] MasterKey) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
            byte[] encryptedKeyBytes = decode(encryptedKey);


            SecretKey masterKey = new SecretKeySpec(MasterKey, "AES");


            Cipher decryptionCipher = null;

            decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec spec = new GCMParameterSpec(128, decode(masterIV));


            decryptionCipher.init(Cipher.DECRYPT_MODE, masterKey, spec);

            byte[] decryptedBytes = null;

            //System.err.println("MASTER PASS: " + encode(masterKey.getEncoded()) + "\nMAsterIV: " + encode(decryptionCipher.getIV()));

            decryptedBytes = decryptionCipher.doFinal(encryptedKeyBytes);

            return decryptedBytes;

        }

        public static void sendToFile(ArrayList<Password> list, byte[] keyArray, String fileIV) throws IOException {

            byte[] fileIVBytes = fileIV.getBytes();
            SecretKey fileKey = new SecretKeySpec(keyArray, "AES");
            GCMParameterSpec spec = new GCMParameterSpec(128, fileIVBytes);

            Cipher encryptionCipher = null;
            try {
                encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException(e);
            }
            try {
                encryptionCipher.init(Cipher.ENCRYPT_MODE, fileKey, spec);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            } catch (InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            }
            CipherOutputStream cipherOutputStream = null;
            cipherOutputStream = new CipherOutputStream(new BufferedOutputStream(new FileOutputStream("testObject.txt")), encryptionCipher);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(cipherOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        }

        public static ArrayList<Password> readFromFile(byte[] keyArray, String fileIV) throws IOException, ClassNotFoundException {

            byte[] fileIVBytes = fileIV.getBytes();
            SecretKey fileKey = new SecretKeySpec(keyArray, "AES");
            GCMParameterSpec spec = new GCMParameterSpec(128, fileIVBytes);

            Cipher decryptionCipher = null;
            try {
                decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException(e);
            }
            try {
                decryptionCipher.init(Cipher.DECRYPT_MODE, fileKey, spec);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            } catch (InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            }

            CipherInputStream cipherInputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream("testObject.txt")), decryptionCipher);
            ObjectInputStream objectInputStream = new ObjectInputStream(cipherInputStream);

            return (ArrayList<Password>) objectInputStream.readObject();


        }

        public static byte[] getMasterKey(String password) {
            SecretKey master;
            String salt = "1234567890123456";
            SecretKeyFactory factory = null;
            try {
                factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
            try {
                master = new SecretKeySpec(factory.generateSecret(spec)
                        .getEncoded(), "AES");
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("MASTER FROM FUNCTION " + encode(master.getEncoded()));
            return master.getEncoded();
        }


        private static byte[] decode(String data) {
            return Base64.getDecoder().decode(data);
        }

        public static String encode(byte[] data) {
            return Base64.getEncoder().encodeToString(data);
        }

    }

