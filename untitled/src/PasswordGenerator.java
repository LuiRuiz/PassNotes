import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

public class PasswordGenerator {
    private String password = "";
    private String name ="";
    private final String smybols = "$!_@?*&";
    public int passLength;

    private boolean canGetPassword = false;

    /* Password will be generated here
        requires: getPasswordArrayList()
                  randomChar()
                  encriptPass()
     */
    public PasswordGenerator(int Length, String passwordName) throws FileNotFoundException {
        this.passLength = Length;
        this.name = passwordName;

        for (Integer i : getPasswordArray(passLength)) {

            // turn int of getPasswordArray to char then add char


            password+= getCharFromNum(i);

            System.out.print(i);

        }
        System.out.println("\nBefore Encrption: "+ password);
        password = EncryptDecrypt.encrypt(password, "password");
        System.out.println("\nAfter Encription: "+ password);
        sendPass(Integer.toString(passLength)+":"+passwordName+":"+password);

        canGetPassword = true;



    }

    public int[] getPasswordArray(int length){
        Random rand = new Random();
        int[] passwordArray =  new int[length];
        Arrays.fill(passwordArray, 0);
        for (int i = 0; i < passwordArray.length; i++){
            if (i <= 3){
                passwordArray[i]= i;
            }else{
                passwordArray[i]= rand.nextInt(4);
            }

        }



        return passwordArray;
    }

    public void printSomething(){


        for (Integer i : getPasswordArray(passLength)){
            System.out.println(i);
        }

    }

    public char getCharFromNum(int i){
        Random rand = new Random();
        int min, max;

        char genChar = ' ';
        switch(i){
            case 0:
                //sybols
                min = 0;
                max = 0;
                genChar =  smybols.charAt(rand.nextInt(7));
                break;
            case 1:
                //lowercase
                max =122;
                min = 98;
                genChar = (char) (rand.nextInt(max - min) + min);
                break;
            case 2:
                max = 90;
                min = 63;
                //upper cass
                genChar = (char) (rand.nextInt(max - min) + min);
                break;
            case 3:
                //
                max = 57;
                min = 48;
                genChar = (char) (rand.nextInt(max - min) + min);
                break;



        }
        return genChar;

    }

    protected void sendPass(String finalStringPackage) throws FileNotFoundException {
        try  {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.txt", true), StandardCharsets.UTF_8));

            bw.append(finalStringPackage);
            bw.close();



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}




