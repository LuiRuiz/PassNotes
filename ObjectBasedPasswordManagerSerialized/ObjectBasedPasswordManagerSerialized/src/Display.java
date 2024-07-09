import javax.crypto.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Display {

    private boolean initalRun;
    private byte[] masterKey;

    private Scanner userInput = new Scanner(System.in);
    private ArrayList<Password> ActiveList;

    private String[] Case1;
    private String[] Case2;
    private String[] Case3;
    private String[] Case4;
    private String[] Case5;

    private String[] KeyandIVs;

    private final int KEY_SIZE = 128;


    private int tracker = 0;

    private boolean loopCondition = true;

    /*Constructor Display() sets ActiveList to contents of file
     * and updates cases for first use
     *
     */
    public Display() throws IOException, ClassNotFoundException, InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {


        try {
            File file = new File("testObject.txt");
            if (file.createNewFile()) {
                //Put into Login Mode

                //generate their FileKey
                AESEncryptDecrypt aesEncryptDecrypt = new AESEncryptDecrypt();
                try {
                    aesEncryptDecrypt.keyGenerator(userInput.nextLine());
                     masterKey = aesEncryptDecrypt.getMasterPassKey();
                } catch (NoSuchPaddingException e) {
                    throw new RuntimeException(e);
                } catch (IllegalBlockSizeException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (BadPaddingException e) {
                    throw new RuntimeException(e);
                } catch (InvalidKeyException e) {
                    throw new RuntimeException(e);
                }
                initalRun = true;
                System.out.println("File created: " + file.getName());

            } else {
                System.out.println("File already exists.");
                initalRun = false;
                System.out.println("Login-------------\nEnter Password:");
                masterKey = AESEncryptDecrypt.getMasterKey(userInput.nextLine());
                //login
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        KeyandIVs = AESEncryptDecrypt.readEncryptedKey();





        if (!initalRun) {
            try {
                //decrypt fileKey
                byte[] fileKey = AESEncryptDecrypt.decyptKey(KeyandIVs[0], KeyandIVs[1].substring(0,16), masterKey);


                ActiveList = AESEncryptDecrypt.readFromFile(fileKey, KeyandIVs[1].substring(16));
                updateCases();

            } catch (FileNotFoundException e) {

                ActiveList = new ArrayList<Password>();

            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            }
            System.out.println("done");
            //load cases:
        }else{
            ActiveList = new ArrayList<Password>();
        }


    }


    public boolean getLoopConditions() {
        return loopCondition;
    }

    private void updateTracker() {
        tracker += 5;
    }
    private void decrementTracker(){
        if (tracker-5 < 0 && ActiveList.size() >= 5 && ActiveList.size()%5 == 0){
            tracker = ActiveList.size() - (ActiveList.size()%5) - 5;
        }else if (tracker-5 < 0 && ActiveList.size() >= 5){
            tracker = ActiveList.size() - (ActiveList.size()%5);
        } else{
            tracker -=5;
        }
    }


	/* Function: CheckForEntryInLoop()
	*
	* Inputs:
		int i - iterator		THIS FUNCTION IS MEANT TO USE WITHIN A LOOP
	*
	* DESCRIPTION:
	*	this function is used inside functions with conditions that involve the password vector
	* it checks to see if the current iterator + tracker surpass the value of the password vectors length
	* if so return false
	*

	*/

    private boolean checkForEntryInLoop(int i) {
        if (ActiveList==null){
            return false;
        }
        return i + tracker < ActiveList.size();
    }

	/* Function: loadCases(std::vector<std::string>& activeCase, int i)
	* Description:
			takes an input of a vector of strings and an int i to represent the current iterator of a loop
	*
	*
	*
	*/

    private String[] loadCase(int i) {
        String[] returnCase = {"", ""};
        if (checkForEntryInLoop(i)) {
            returnCase[0] = ActiveList.get(i + tracker).getName();
            returnCase[1] = ActiveList.get(i+tracker).getEncryptedPassword();
        } else {
            returnCase = new String[]{"none", "none"};
        }
        return returnCase;
    }

    /* Function: updateCases();
     *  Description: Used to fill up cases structure.
     *
     *			Loops through 0-4 and uses a switch case structure to
     *			update variables case1-case5 by calling function loadCase()
     *
     *			loadCase() is dependent on the loop variable i and the value of tracker

     */
    private void updateCases() {
        // this loop is redundant just do loadCase(1) loadCase(2)
        Case1 = loadCase(0);
        Case2 = loadCase(1);
        Case3 = loadCase(2);
        Case4 = loadCase(3);
        Case5 = loadCase(4);


    }
    /*Function caseStructure()
     *
     * Description; Implemntation of case structre
     *
     *		Takes input of choice (int) based on display
     *		then calls function updateCases() to fill cases with newly encryoted passwords.
     *
     *		then decycrpt is called with imputs of the current encrypted password and master password
     *		to display decrpyted password
     *
     *		7 calls for a new password
     *
     *		8 calls to end the program by setting loopCondition variable to false
     *
     */

    public void caseStructure() throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        System.out.println("Please Enter Numeric value: ");
        int choice = userInput.nextInt();

        updateCases();

        switch (choice) { // will need to be changed to account for string to vector or string to item.
            case 1: {

                System.out.println(Case1[1]);

                break;
            }
            case 2: {
                System.out.println(Case2[1]);
                break;
            }

            case 3: {
                System.out.println(Case3[1]);
                break;
            }

            case 4: {
                System.out.println(Case4[1]);
                break;
            }
            case 5: {
                System.out.println(Case5[1]);
                break;
            }
            case 6: {
                updateTracker();
                updateCases();

                break;
            }
            case 7:{
                decrementTracker();
                updateCases();
                break;
            }
            case 8: {

                String accName;
                int passLength;
                // ask for new name of account
                System.out.println("Please enter Account Name: ");

                accName = userInput.next();


                // ask for length
                System.out.println("Enter desired password length: ");
                passLength = userInput.nextInt();

                NewPassword pass = new NewPassword(passLength, accName);


                ActiveList.add(pass);
                updateCases();


                break;
            }
            case 9: {

                SerializableFileHandler.returnObjectsToFile(ActiveList);
                try {
                    //
                    System.out.println("IV from Display() "+ KeyandIVs[1].substring(0,16) );
                    byte[] fileKey = AESEncryptDecrypt.decyptKey(KeyandIVs[0], KeyandIVs[1].substring(0,16), masterKey);
                    AESEncryptDecrypt.sendToFile(ActiveList, fileKey, KeyandIVs[1].substring(16));
                    System.out.println("File sent sucessfuly");
                } catch (IllegalBlockSizeException e) {
                    throw new RuntimeException(e);
                } catch (BadPaddingException e) {
                    throw new RuntimeException(e);
                }

                loopCondition = false;
                break;
            }

            default: {
                System.out.println("hello");
                break;
            }


        }
    }

    public void displayStructure() {
        for (int i = 0; i < 5; i++) {
            String currentNum = Integer.toString(i + 1);


            if(ActiveList==null){
                System.out.println(currentNum + ": " + "NONE");
            }
            else if ( checkForEntryInLoop(i)) {


                String accName = ActiveList.get(i + tracker).getName();


                System.out.println(currentNum + ": " + accName);

            } else {
                System.out.println(currentNum + ": " + "NONE");
            }
        }

        System.out.println("6: Next");
        System.out.println("7: Back");
        System.out.println("8: NEW ACCOUNT AND PASSWORD");
        System.out.println("9: END");


    }


}