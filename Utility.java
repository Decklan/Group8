/**
 * This file (Utility.java) includes the implementation
 * of the Utility class. This class constructs a Scanner
 * object, so other classes can derive from it to
 * avoid creating and recreating this object throughout
 * the program.  Furthermore, the Utility class will hold
 * any of the input validation functions that may be used
 * frequently throughout the program
 *
 * Created by MMC on 11/11/16.
 */

import java.util.Scanner;




public class Utility {

//Fields

    protected Scanner input;    //Create new scanner object


//Constructor

    public Utility(){

    //Declare an object of type Scanner
        input = new Scanner(System.in);

    }




    /*Performs a check on the user's input to see if
        *they answered some form of "yes."
        * INPUT: NONE
        * OUTPUT: 0 for no, 1 for yes, 2 for an invalid response
        */
    public int checkAnswer() {

        String temp = input.nextLine();  //Catches the user's input
        int responseFlag;

        if (temp.equalsIgnoreCase("no") || temp.equalsIgnoreCase("n")) {
            responseFlag = 0;
        }

        else if (temp.equalsIgnoreCase("yes") || temp.equalsIgnoreCase("y")) {
            responseFlag = 1;
        }

         else {
            System.out.println("Not a valid response.");
            responseFlag = 2;
        }

        return responseFlag;

        /*  Error check not robust enough
         * Returns the value of the string check (true or false).
         * return (temp.equalsIgnoreCase("yes") || temp.equalsIgnoreCase("y"));
         */
    }


    /* Performs a check on the user's input to see if
     * they entered an integer when prompted to do so.
     * INPUT: String
     * OUTPUT: True or False
     */
   public boolean testIntegerInput(String userInput){

       boolean validInteger = false;

       try{
           Integer.parseInt(userInput);

           //If successful, input is an integer
            validInteger = true;
       }
       catch (NumberFormatException ex){

           System.out.println("Invalid Input. Not an numerical value.");
       }


       return validInteger;

   }



}
