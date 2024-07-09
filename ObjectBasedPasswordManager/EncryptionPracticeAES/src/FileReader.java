import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static String[] read() {


        String pass = null;


        try {
            pass = new Scanner(new File("EncryptedKeyTest.txt")).useDelimiter("\\Z").next();

        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String IV = null;
        String masterIV = null;
        String fileIV = null;

        try {
            IV = new Scanner(new File("IVTest.txt")).useDelimiter("\\Z").next();
            masterIV =  IV.substring(0,16);
            fileIV = IV.substring(16);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new String[]{pass, IV};
    }
}
