import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {


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
        PasswordGenerator jenny = new PasswordGenerator(16,"test");

        fileHandler test = new fileHandler();

        ArrayList<String> bob fileHandler.handle();
        int counter = 1;
        for (String pass : bob){
            System.out.println(Integer.toString(counter)+": " +pass+":\t:"+EncryptDecrypt.decrypt(pass.split(":",2)[1],"password") );
            counter++;
        }

         */

        Display display = new Display();

        while(display.getLoopConditions()){
            display.displayStructure();
            display.caseStructure();
        }







    }
}
