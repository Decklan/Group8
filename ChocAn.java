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




	/* Prompts the user to enter their ID number and calls on the
	 * userVerification function to verify whether the user is a
	 * Manager or Provider
	 */
	public String userLogin() {

			//Boolean for loop control (true: continue false: break)
			int again;

			do {
				System.out.print("Please Enter Your ID Number:  ");
				inputUserID = input.findInLine(".{9}");

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
                        || checkUserStatus.equalsIgnoreCase("member")) {
						System.out.println("This is not a registered access ID in the system.");
						checkUserStatus = "invalid";
					}

				}

				else {
					checkUserStatus = "invalid";
				}

				//If the user's input is invalid, allow them to enter their information again
				if (checkUserStatus.equalsIgnoreCase("invalid")) {
					do {
						System.out.print("Would you like to try again? [y/n]:  ");
						again = checkAnswer();
					}while(again == 2);


				}
				else{
					again = 0;
				}

				}while(again == 1);

			return checkUserStatus;
	}

	/* Takes the verified user and defines the role to allow the program to
	 * proceed to menu screen that corresponds to that user.
	 * INPUT: NONE
	 * OUTPUT: True or False
	 */
	public void userDefineRole(){

		//Grab that first character to inform the switch statement
		char statusInformSwitch = userStatus.charAt(0);

		//Handles the user's role
		switch(statusInformSwitch) {

			//If the user is a manager
			case 'm':

				endUser = new Manager(userIDisInteger);
				endUser.run();
				break;

			//If the user is a provider
			case 'p':

				endUser = new Provider(userIDisInteger);
				endUser.run();
				break;

			//If they're something else at this point, it's time to go buddy
			default:
				System.out.println("Level of authorization could not be verified.\nForcing Software Exit.");
				break;
		}

		System.out.println("Thank You for using ChocAn.\nGoodbye.");

	}






	public void runChocAn (){

    System.out.println("++++ WELCOME ++++\n\n");

    //Prompt the User for their password
	userStatus = userLogin();

    /*Test return value for userStatus
    System.out.println(userStatus);
	*/

	//Define the user's role and proceed to the appropriate menu.
	userDefineRole();



	}
}
