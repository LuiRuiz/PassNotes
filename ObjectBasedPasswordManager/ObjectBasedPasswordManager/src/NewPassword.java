import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class NewPassword extends Password implements Serializable {

    private final String smybols = "$!_@?*&";
    private String password = "";



    public NewPassword (int passLength,String name){
        super(passLength,name);

        for (Integer i : getPasswordArray(passLength)) {

            // turn int of getPasswordArray to char then add char


            password += getCharFromNum(i);



        }
        System.out.println("\nBefore Encrption: "+ password);
        pass = EncryptDecrypt.encrypt(password, "password");
        System.out.println("\nAfter Encription: "+ pass);
        try {
            sendPass(Integer.toString(passLength)+":"+name+":"+pass);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


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
}

