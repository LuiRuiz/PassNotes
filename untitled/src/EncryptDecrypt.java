public class EncryptDecrypt {


    public static String encrypt(String password, String key){
        String temp = key;


        while(key.length() < password.length() ){
            key+=temp;
        }
        String outputString = "";

        // calculate length of input string
        int len = password.length();

        // perform XOR operation of key
        // with every character in string
        for (int i = 0; i < len; i++)
        {

            outputString = outputString +
                    Character.toString((char) (password.charAt(i) ^ key.charAt(i)));


        }

        //System.out.println(outputString);
        return outputString;

    }
    public static String decrypt(String password, String key){
        return encrypt(password,key);
    }
}




