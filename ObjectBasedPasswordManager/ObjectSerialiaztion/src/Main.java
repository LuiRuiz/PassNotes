import java.io.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

/*        ArrayList<Student> classList = new ArrayList<Student>();

        classList.add(new Student("Mink",12));
        classList.add(new Student("Mip", 7));
        classList.add(new Student("Connor", 8));

        Main mean = new Main();

        //mean.writeEmployeeObject(classList);
        mean.readEmployeeObject();/*


 */


        
                
    }
    private void writeEmployeeObject(ArrayList<Student> listOfEmployees) throws FileNotFoundException,
            IOException
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream("students.txt");
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
            fileInputStream = new FileInputStream("students.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);

            /*
             * Read an object from the ObjectInputStream.
             */
            ArrayList<Student> listOfEmployees = (ArrayList<Student>) objectInputStream
                    .readObject();
            System.out
                    .println("Successfully read list of employee objects from the file.\n");

            for (Student employee : listOfEmployees)
            {
                System.out.println("Seat  = " + employee.getSeat());
                System.out.println("Name = " + employee.getName());
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

