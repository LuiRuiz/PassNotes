import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


public class fileHandler
{
    File f = new File("test.txt");
    /*
    Function: handle();
    Parameters: NONE
    Description: creates a scanner for a TEST FILE using UTF-8 charset to read
                   a file's content first with a delimiter of ":" to capture the length of the password
                   and the name of the account IE (bank). It then switches delimiter to "" or none
                   and reads the next n characters based on length

                   After, the delimeter resets to ":" and the workable string NAME:ENCRYPTEDPASS
                   is stored to an ArrayList of strings
     Returns: ArrayList of Strings

     */
    public static ArrayList <String> handle() {

           //Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));

           // to be returned later
           ArrayList<String> arrayListofPasswords = new ArrayList<String>();
           //scanner
        Scanner readStart = null;
        try {
            readStart = new Scanner(new File("test.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String length, name;
           //what is added to arrayListofStrings
           String finalString = "";

           //initialize delimiter before loop
           readStart.useDelimiter(":");

           //create stringbuilder before start of loop


           String builderStringResult;

           // start to read length and name based on delimiter ':"
           while (readStart.hasNext()) {
               length = readStart.next();
               name = readStart.next();


               int reallength = Integer.parseInt(length);
               int count = 0;

               char intch;
               // must set dilimiter to none to read character by character
               readStart.useDelimiter("");
               readStart.next();


               //NOTE::    Might want to create builder uptop and clear content of builder here
               StringBuilder builder = new StringBuilder();

               while (count < reallength) {
                 /*
                 here after we receive password length and accountName we will the read
                 the next N characters in the password and use a string builder to build a string
                 thatresult string is given value at end of loop
                  */
                   intch = readStart.next().charAt(0);
                   builder.append(intch);
                   count++;


               }

               //reset dilimiter to restart loop?
               readStart.useDelimiter(":");
               builderStringResult = builder.toString();

               /* DEBUG PRINTING REMOVE LATER*/
               //System.out.println("builder result: " + builderStringResult);
               //System.out.println("Decrypted String Builder result: " + EncryptDecrypt.decrypt(builderStringResult, "password"));


               finalString = name + ":" + builderStringResult;
               arrayListofPasswords.add(finalString);
               //END OF LOOP ITERATION
           }
           //AFTER WHILE LOOP
           //r.close();
           readStart.close();

           return arrayListofPasswords;



    }
}

