import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Display {

    private Scanner userInput = new Scanner(System.in);
    private List<Password> ActiveList;

    private String[] Case1;
    private String[] Case2;
    private String[] Case3;
    private String[] Case4;
    private String[] Case5;


    private int tracker = 0;

    private boolean loopCondition = true;

    /*Constructor Display() sets ActiveList to contents of file
     * and updates cases for first use
     *
     */
    public Display() {

        // set inital List
        ActiveList = FileHandler.handle();
        System.out.println("done");
        //load cases:
        updateCases();

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
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    Case1 = loadCase(i);
                    break;
                case 1:
                    Case2 = loadCase(i);
                    break;
                case 2:
                    Case3 = loadCase(i);
                    break;
                case 3:
                    Case4 = loadCase(i);
                    break;
                case 4:
                    Case5 = loadCase(i);
                    break;
            }
        }
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

    public void caseStructure() throws FileNotFoundException {
        System.out.println("Please Enter Numeric value: ");
        int choice = userInput.nextInt();

        updateCases();

        switch (choice) { // will need to be changed to account for string to vector or string to item.
            case 1: {

                System.out.println(EncryptDecrypt.decrypt(Case1[1], "password"));

                break;
            }
            case 2: {
                System.out.println(EncryptDecrypt.decrypt(Case2[1], "password"));
                break;
            }

            case 3: {
                System.out.println(EncryptDecrypt.decrypt(Case3[1], "password"));
                break;
            }

            case 4: {
                System.out.println(EncryptDecrypt.decrypt(Case4[1], "password"));
                break;
            }
            case 5: {
                System.out.println(EncryptDecrypt.decrypt(Case5[1], "password"));
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

            if (checkForEntryInLoop(i)) {
                System.out.println(ActiveList.get(i + tracker).getName());

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