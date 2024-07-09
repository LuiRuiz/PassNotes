import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.ArrayList;

import static com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode;

public class Main {
    public static void main(String[] args) throws Exception {
      AES aes = new AES();
      aes.init();
       aes.setPassWord("password");

        //String encryptedMessage = aes.encrypt("pineapple");
        ArrayList<ClassForList> students = new ArrayList<ClassForList>();
        students.add(new ClassForList("Steven", "Bio", 18));
        students.add(new ClassForList("Philip", "Health", 18));
        students.add(new ClassForList("Mark", "Chem", 18));

        //create file encrypt object
        aes.encryptList(students);

        //print original key and IV
      aes.exportKeys();


      //encrypt original Key with PassKey

     String ekey = aes.encryptKey();

     //Sends encrypted-key, passIV, mainIV to file
     aes.sendEncryptedKey( ekey);

        System.out.println( "Master IV: "+ AES.encode(aes.passIV));


     String[] list = aes.readEncryptedKey();



        System.out.println("Master IV from File: "+ list[1].substring(0,16));
        System.err.println("Encrypted KEY: "+ ekey);
        System.err.println("Encrypted KEY From File: "+ list[0]);

        String iv = list[1].substring(0,16);


        System.out.println(aes.decryptKey(list[0]));

        System.out.println(aes.decryptKey(list[0], list[1].substring(0,16)));

        //third way create passbased secret
        String salt = "1234567890123456";
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec("password".toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey masterPass = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");


        System.out.println("MasterPass Key Generated: "+ AES.encode(masterPass.getEncoded()));
        System.out.println("MasterPass Key from Intial Object: "+ AES.encode(aes.passBasedSecret.getEncoded()));

        //System.out.println("Key as arg: "+ aes.decryptKeyAgain(list[0], list[1].substring(0,16), masterPass));



        //Getting kEY FROM mASTER PASS GENERATIOM
        String MasterKeyString = AES.encode(masterPass.getEncoded());
        String MasterIVString = list[1].substring(0,16);



        //NOT USED
        String secondary_key = aes.decryptKey(list[0], list[1].substring(0,16));

        //String decryptedMessage = aes.decrypt(encryptedMessage, secondary_key, list[0].substring(16));
        //decrypt key
        Decrypt decrypt = new Decrypt();
        decrypt.fromStrings(MasterKeyString, MasterIVString );


        //System.out.println("Encrypted Message: "+ encryptedMessage);
        System.out.println("IV from file: "+ list[1].substring(16));
        System.out.println("Decrypted Key: "+ secondary_key);

        String decryptedMessage = decrypt.decrypt(ekey);
        System.out.println(decryptedMessage);

        Decrypt messageList = new Decrypt();
        String subIV = list[1].substring(16);
        messageList.fromStrings(decryptedMessage,subIV );
        //forms key and IV for object

        ArrayList<ClassForList>readStudents = messageList.decryptList();

        for (ClassForList item : readStudents){
            System.out.println(item.name);
        }

        //String finalResult = messageDecrypt.decrypt(encryptedMessage);

        //System.out.println(finalResult);






    }
}
