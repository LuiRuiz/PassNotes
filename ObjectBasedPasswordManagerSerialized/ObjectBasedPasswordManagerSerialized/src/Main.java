import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

        public static void main(String[] args) throws IOException, ClassNotFoundException, InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {


//            try {
//                File file = new File("SerializableTest.txt");
//                if (file.createNewFile()) {
//                    System.out.println("File created: " + file.getName());
//                } else {
//                    System.out.println("File already exists.");
//                }
//            } catch (IOException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }


           Display display = new Display();

            while(display.getLoopConditions()){
                display.displayStructure();
                display.caseStructure();
            }






        }






    }


