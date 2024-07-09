import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.util.ArrayList;

/*  hold static functions to load the
    arrayList of passwords into the stack and
    to export the objects back into the file at the end.

    MUST DISABLE sendPass() functions
 */
public class SerializableFileHandler {

    /* readObjectsToFile uses FileInputStream and ObjectInputStream to place objects into arrayList
        Do Not Use Before File Is Created

        Put Check TO see if File Exists Later!!!!
     */
    public static ArrayList<Password> readObjectsFromFile() throws IOException, ClassNotFoundException {


        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream("SerializableTest.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Password> listOfPasswords = (ArrayList<Password>) objectInputStream
                    .readObject();
            System.out.println("Read Successful");


            return listOfPasswords;
        } finally {

            if (objectInputStream != null) {
                /*
                 * Closing a ObjectInputStream will also
                 * close the InputStream instance from which
                 * the ObjectInputStream is reading.
                 */
                objectInputStream.close();
            }

        }
    }


    /*
    Sends the ArrayList back into the file!!
    DOES NOT APPEND
    MUST OVERRIDE DATA to SEND

    Takes Argument of ArrayList<Passsword>;

    WILL CREATE FILE IF DATA EXISTS make sure to write in classes before calling
     */
    public static void returnObjectsToFile (ArrayList<Password>listOfObjects) throws IOException {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try{
            fileOutputStream = new FileOutputStream("SerializableTest.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(listOfObjects);

            System.out.println("Objects Written Successfully");



        }finally {
            if (objectOutputStream != null){
                objectOutputStream.close();

            }
        }


    }
}


