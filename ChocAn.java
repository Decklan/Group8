/**
 *	This file (ChocAn.java) includes the implementation of the ChocAn class.
 * The ChocAn object will be used to assign the role of the user based on the
 * login results; it has been designed to make the calls to the appropriate methods
 * that follow the flow of execution described in the Specification documentation
 * as well as the contract agreed upon by ChocAn.
*/




public class ChocAn extends Utility {

	//Fields
	protected String checkUserStatus;	//Holds the user status while their status remains unverified by the Database.
	protected String userStatus;    	//Holds the user status verified by the userVerification method (DataAccess).
	protected String inputUserID;		//Holds the input entered by the user when prompted at login.
	protected int userIDisInteger;		//Holds the 9 digit user ID number entered by the user.
	protected User endUser;         	//Reference to the abstract base class


	//Constructor
	public ChocAn() {

		//Field Initialization
		checkUserStatus = null;
		userStatus = null;
		endUser = null;
		inputUserID = null;
		userIDisInteger = 0;
	}



	/*11.11.16 	 - work on error checking for number vs. string
				 	+ convert case toLower
				 	+ see if ther is an isInt function
				 	+ set user status = to userVerification
				 - create loop for this prompt
	 */



	/* Prompts the user to enter their ID number and calls on the
	 * userVerification function to verify whether the user is a
	 * Manager or Provider
	 */
public String userLogin() {

			//Boolean for loop control (true: continue false: break)
			boolean again = true;

			do {
				System.out.print("Please Enter Your ID Number:  ");
				inputUserID = input.nextLine();

				//Test that the user's input is an integer.
				if (testIntegerInput(inputUserID)) {
					userIDisInteger = Integer.parseInt(inputUserID);

					/*Testing output
					System.out.println(DataAccess.userVerification(userIDisInteger));
                    */

					checkUserStatus = DataAccess.userVerification(userIDisInteger);

						/* If the number is an integer & the user enters an invalid ID number or by chance
						 * enters a member's ID number, they must be denied by the system and informed that
						 * the number they have entered will not gain them access to the system.
						 */
					if(checkUserStatus.equalsIgnoreCase("invalid") || checkUserStatus.equalsIgnoreCase("suspended")
                        || checkUserStatus.equalsIgnoreCase("valid"))
						System.out.println("This is not a registered access ID in the system.");
				}

				else {
					checkUserStatus = "invalid";
				}

				//If the user's input is invalid, allow them to enter their information again
				if (checkUserStatus.equalsIgnoreCase("invalid")){
					System.out.print("Would you like to try again? [y/n]:  ");
					again = checkAnswer();
				}

				else{
					again = false;
				}

				}while(again);

			return checkUserStatus;
	}


	public void runChocAn (){

    //Prompt the User for their password
    System.out.println("++++ WELCOME ++++\n\n");
	userStatus = userLogin();

    /*Test return value for userStatus
    System.out.println(userStatus);
    */


	}










	public static void main(String[] argv){

		/*Create new ChocAn client
		ChocAn chocAnClient = new ChocAn();


		chocAnClient.runChocAn();
		*/	

		/*Test login function
		chocAnClient.loginTest();
		*/









		/*
         TEST userverificaiton
        System.out.println(DataAccess.userVerification(123213));
        System.out.println(DataAccess.userVerification(768921693));
        System.out.println(DataAccess.userVerification(587857871));
        System.out.println(DataAccess.userVerification(454419772));
        System.out.println(DataAccess.userVerification(433671813));
		*/

        /* TEST addingOrganization
        DataAccess dataAccess = new DataAccess();
        boolean a = dataAccess.addOrganization(274419773, "Mike Brown" , "3992 Highland Drive",
                                   "Milwaukee", "WI", 53202, "member");
        */

        /* TEST Directory LOOK UP
        DataAccess dataAccess = new DataAccess();
        String directory = dataAccess.directoryLookUp();
        System.out.println(directory);
        */
        

	}
}
