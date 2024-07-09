import java.io.*;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

        public static void main(String[] args) throws IOException, ClassNotFoundException {


            try {
                File myObj = new File("test.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

/*
            Display display = new Display();

            while(display.getLoopConditions()){
                display.displayStructure();
                display.caseStructure();
            }

*/
            ArrayList<Password> listOfPasses = new ArrayList<Password>();

            //listOfPasses.add(new NewPassword(12,"TEST1"));
            //listOfPasses.add(new NewPassword(6,"TEST2"));
            //listOfPasses.add(new NewPassword(8,"TEST3"));

            Main mink = new Main();

            //mink.writeEmployeeObject(listOfPasses);
            mink.readEmployeeObject();






        }
    private void writeEmployeeObject(ArrayList<Password> listOfEmployees) throws FileNotFoundException,
            IOException
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream("SerializableTest.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);




            /*
             * Write the specified object to the
             * ObjectOutputStream.
             */
            objectOutputStream.writeObject(listOfEmployees);
            System.out
                    .println("Successfully written list of employee objects to the file.\n");
        }
        finally
        {

            if (objectOutputStream != null)
            {
                /*
                 * Closing a ObjectOutputStream will also
                 * close the OutputStream instance to which
                 * the ObjectOutputStream is writing.
                 */
                objectOutputStream.close();
            }
        }

    }
    private void readEmployeeObject() throws IOException,
            FileNotFoundException, ClassNotFoundException
    {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try
        {
            fileInputStream = new FileInputStream("SerializableTest.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);

            /*
             * Read an object from the ObjectInputStream.
             */
            ArrayList<Password> listOfEmployees = (ArrayList<Password>) objectInputStream
                    .readObject();
            System.out
                    .println("Successfully read list of employee objects from the file.\n");

            for (Password employee : listOfEmployees)
            {
                System.out.println("Seat  = " + employee.getEncryptedPassword());
                System.out.println("\n");
                System.out.println("Name = " + employee.getName());
                System.out.println("Decrypted = " + EncryptDecrypt.decrypt(employee.getEncryptedPassword(),"password"));
                System.out.println("--------------------------");


        }

        }
        finally
        {

            if (objectInputStream != null)
            {
                /*
                 * Closing a ObjectInputStream will also
                 * close the InputStream instance from which
                 * the ObjectInputStream is reading.
                 */
                objectInputStream.close();
            }
        }

    }

    }
