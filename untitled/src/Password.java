import java.io.*;
import java.nio.charset.StandardCharsets;

public class Password {
    protected String name;
    protected String pass;

    protected int passLength;


    /*
    this version of password is not meant to be generated
    but read from a file. the object will be created in the FileHandler
    handle() function

     */
    Password(int passLength, String name, String pass){
        this.passLength = passLength;
        this.name = name;
        this.pass = pass;
    }

    public Password(int passLength, String name) {
        this.passLength = passLength;
        this.name = name;
    }

    /*
    in case password needs to be sent to file
    likely put filePass
     */
    public final String assembleFileString(){
        return passLength +":"+name+":"+pass;
    }

    /*
     *getter for Name
     */
    public final String getName(){
        return name;
    }

    /*
     *getter for encryptedPass
     */
    public final String getEncryptedPassword(){
        return pass;
    }

    public void sendPass(String s) throws FileNotFoundException {
        try  {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.txt", true), StandardCharsets.UTF_8));

            bw.append(assembleFileString());
            bw.close();



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
