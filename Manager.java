import java.util.Random;

/**
 * This class contains all the Manager method should contain
 */
public class Manager extends User {

    public Manager(int userID) {
        super(userID);
    }

    // Generates a random 9-digit provider/member
    public int randomDigitsID() {
        boolean isValidID;
        int newMemberID;
        /*
        * Instead of having the Manager enter a 9 digit number when creating a Provider
        * this will generate a random 9 digit number for the Provider that is in the range
        * 100000000 - 999999999.
        */
        do {
            Random rand = new Random();
            newMemberID = 100000000 + rand.nextInt(899999999);
            isValidID = isIDTaken(newMemberID);
        } while(!isValidID);
        return newMemberID;
    }

    /* Verifies whether the entered ID is already in use or not
     *      1. If the entered ID is taken, verify will not be set to "invalid". Return will be false
     *         to signify the ID is already in the database
     *      2. If the entered ID isn't in the database, verify will be set to "invalid". This means
     *         that the ID is not currently in use. idIDTaken returns true to signify it is not taken
     */
    public boolean isIDTaken(int id) {
        String verify = DataAccess.userVerification(id);
        return verify.equals("invalid");                //CHANGED FROM verify.equals("invalid")? true:false; (redundant)
    }

    /** This function scans the database for a valid MEMBER (for suspension purposes)
     * This function searches the database for a member specifically. The reason for this
     * is... if we just use isIDTaken(id) for the scan it searches ALL people in the database
     * including managers/providers and we don't want to suspend or unsuspend them.
     **/
    public boolean isValidMember(int memberID) {
        String verify = DataAccess.userVerification(memberID);
        if (verify.equals("member") || verify.equals("suspended"))
            return true;
        else return false;
    }

    // Prompts for a string input
    public String promptString(String user, String info) {
        System.out.print(user + " " + info + ": ");
        String tempString = input.nextLine();
        return tempString;
    }

    /*
     * Prompts for an integer and checks to make sure it is of the proper input type.
     */
    public int promptInt(String user, String info) {
        int tempInt;
        do {
            System.out.print(user + " " + info + ": ");
            //If/Else block to verify input is an integer
            String testInput = input.next();
            if (testIntegerInput(testInput)) {
                tempInt = Integer.parseInt(testInput);
            } else {
                System.out.print("Please enter a numerical zip code.");
                tempInt = -1;
            }
        } while (tempInt < 0);
        return tempInt;
    }

    //TODO MB: We need to implement these methods

    // Prompts the manager for basic organization information before adding to the database
    public boolean addOrganization(String user) {
        // Declare organization info variables
        String tempName, tempAddress, tempCity, tempState;
        int newID, tempZip;
        // Loop while the information entered isn't correct
        do {
            tempName = promptString(user, "name");           // Get the organization name
            tempAddress = promptString(user, "address");     // Get the organization address
            tempCity = promptString(user, "city");           // Get the organization city
            tempState = promptString(user, "state");         // Get the organization state
            newID = randomDigitsID();                        // Generate an ID number for the organization
            tempZip = promptInt(user, "zip code");           // Get the organization zip code

            clearScreen();
            System.out.println(user + " name: " + tempName);
            System.out.println(user + " address: " + tempAddress);
            System.out.println(user + " city: " + tempCity);
            System.out.println(user + " state: " + tempState);
            System.out.println(user + " zip code: " + tempZip);
        } while (checkAnswer("Is this information correct?: ") != 1);

        boolean queryStatus = data.addOrganization(newID, tempName, tempAddress, tempCity, tempState, tempZip, user);
        return queryStatus;
    }

    /*
     * This function removes a specific organization from the database. The argument passed
     * in is either "member" or "provider" dependant on the menu the user chose to enter.
     */
    // CURRENTLY ENCOUNTERS ERROR WHEN REMOVING
    public boolean removeOrganization(String user) {
        // Prompt the manager for the ID number for the organization they would like to remove
        int remove;                 // Holds the ID number for the provider or member to be removed
        boolean valid;              // Holds the bool value for if the ID is in use or not
        // Do the ID verification while the ID entered is not valid
        do {
            System.out.print("Please enter the " + user + " ID for the " + user + " you would like to remove: ");
            remove = input.nextInt();
            input.nextLine();
            valid = isIDTaken(remove);
            // If the ID entered isn't in the database
            if (valid)
                System.out.println("Not a valid " + user + " ID. Please enter a valid "+ user + " ID.");
        } while (valid);

        // Holds the bool value from the deleteOrganization function to signify success or failure of removal
        boolean delete = data.removeOrganization(remove);
        return delete;
    }

    /*
     * This function updates a specific organizations information. The argument passed in
     * is either "member" or "provider" dependant on the menu that user chose to enter.
     */
    // CURRENTLY ENCOUNTERS AN ERROR UPDATING
    public boolean updateOrganization(String user) {
        // Prompt manager for ID of organization they would like to update
        int organizationID;
        boolean valid;
        do {
            System.out.print("Enter the ID number for the " + user + " you would like to update: ");
            organizationID = input.nextInt();
            input.nextLine();
            valid = isIDTaken(organizationID);
            if (valid)
                System.out.println("Not a valid " + user + " ID. Please try again.");
        } while (valid);

        String tempName, tempAddress, tempCity, tempState;
        int tempZip;
        do {
            tempName = promptString(user, "name");
            tempAddress = promptString(user, "address");
            tempCity = promptString(user, "city");
            tempState = promptString(user, "state");
            tempZip = promptInt(user, "zip code");

            System.out.println(user + " name: " + tempName);
            System.out.println(user + " address: " + tempAddress);
            System.out.println(user + " city: " + tempCity);
            System.out.println(user + " state: " + tempState);
            System.out.println(user + " zip code: " + tempZip);
        } while(checkAnswer("Is this information correct?: ") != 1);

        boolean update = data.updateOrganization(organizationID, tempName, tempAddress, tempCity, tempState, tempZip, user);
        return update;
    }

    // Lets a manager suspend or unsuspend a member in the database
    public boolean changeMemberStanding() {
        // Verify whether the member exists in the database
        int member;
        do {
            // Prompt for the memberID
            System.out.print("Please enter the member's ID number: ");

            //If/Else block to verify input is an integer
            String testInput = input.next();
            if (testIntegerInput(testInput)) {
                member = Integer.parseInt(testInput);
            }
            else{
                member = 0;
            }

            // Prompt if the ID isn't in the database
            if (!isValidMember(member))
                System.out.println("The entered member ID was not found. Please enter a valid member ID.");
        } while (!isValidMember(member));

        // Loop to make sure choice is entered properly
        String choice; // Holds whether the manager wants to suspend or unsuspend the member
        do {
            // Prompt manager for suspension or unsuspension of a member
            System.out.print("Do you wish to suspend or unsuspend this member?: ");
            choice = input.next();

            // Run data access function corresponding to managers choice
            if (choice.equalsIgnoreCase("suspend"))
                return data.suspendMember(member);
            else if (choice.equalsIgnoreCase("unsuspend"))
                return data.unsuspendMember(member);
            else System.out.println("Please enter a valid choice!");
        } while (!choice.equalsIgnoreCase("suspend") || choice.equalsIgnoreCase("unsuspend"));
        return false; // Fail flag
    }

    // Displays all of the manager menu options to keep the run function shorter and sweeter
    public int menuPrompt() {
        int menuChoice = 0; // it will hold the value of the user input
        do {
            // Print menu options to the screen
            System.out.println("###########################################################");
            System.out.println("## Manager Menu Options:                                 ##");
            System.out.println("##\t (1) Edit Members                                    ##");
            System.out.println("##\t (2) Edit Providers                                  ##");
            System.out.println("##\t (3) Suspend/Unsuspend Member                        ##");
            System.out.println("##\t (4) Quit                                            ##"); // We need such option
            System.out.println("###########################################################");
            System.out.print("Enter your choice (1-4): "); // Prompt manager for a choice

            //If/Else block to verify input is an integer
            String testInput = input.next();
            if (testIntegerInput(testInput)) {
                menuChoice = Integer.parseInt(testInput);
                input.nextLine();
            }
            else{
                menuChoice = 0;
            }

            if(menuChoice <= 0 || menuChoice > 4) {
                clearScreen();
                System.out.println(" \033[0;31m Please make a valid choice! \033[0m");
            }
        } while (menuChoice <= 0 || menuChoice > 4);
        return menuChoice;
    }

    /**
     * This will function will display the submenu options for the manager to
     * take actions members and providers
     * @param user the user the manager will edit(Provider or Member)
     * @return the function will return the option chosen by the manger
     */
    public int editSubmenu(String user){
        int menuChoice = 0; // it will hold the value of the user input
        do {
            // Print menu options to the screen
            System.out.println("###########################################################");
            System.out.println(user +"s Edit Menu Options:");
            System.out.println("\t (1) Add "+ user);
            System.out.println("\t (2) Remove "+ user);
            System.out.println("\t (3) Update "+ user);
            System.out.println("\t (4) Return"); // We need such option
            System.out.println("###########################################################");
            System.out.print("Enter your choice (1-4): "); // Prompt manager for a choice

            //If/Else block to verify input is an integer
            String testInput = input.next();
            if (testIntegerInput(testInput)) {
                menuChoice = Integer.parseInt(testInput);
                input.nextLine();
            }
            else{
                menuChoice = 0;
            }

            if(menuChoice <= 0 || menuChoice > 4) {
                clearScreen();
                System.out.println(" \033[0;31m Please make a valid choice! \033[0m");
            }
        } while (menuChoice <= 0 || menuChoice > 4);
        return menuChoice;
    }

    //This function will control actions in the submenu
    public void submenuRun(String user) {

        int menuChoice = 0;
        do {         // Loop to test input against valid choices
            menuChoice = editSubmenu(user); // Display the menu options to the screen and get the selected option
            clearScreen();

            // If cases handle running the appropriate method based on manager choice
            if (menuChoice == 1) {
                addOrganization(user);
                waitForEnter();  //Wait for the user to press something then move on
            }
            else if (menuChoice == 2) {
                removeOrganization(user);
                waitForEnter();
            }
            else if (menuChoice == 3){
                updateOrganization(user);
                waitForEnter();
            }
        } while (menuChoice != 4);
        return; // Return to the main menu
    }

    /*********  Driver  **********/
    // This function is used as the menu function when a manager is allocated
    public void run() {
        // Integer to hold manager's menu choice
        int menuChoice = 0;

        do {         // Loop to test input against valid choices
            menuChoice = menuPrompt(); // Display the menu options to the screen and get the selected option
            clearScreen();

            //If cases handle running the appropriate method based on manager choice
            if (menuChoice == 1) {
                submenuRun("member");
                waitForEnter();  //Wait for the user to press something then move on
            }
            else if (menuChoice == 2) {
                submenuRun("provider");
                waitForEnter();
            }
            else if (menuChoice == 3){
                boolean change = changeMemberStanding();
                if (change)
                    System.out.println("Member standing successfully changed.");
                else
                    System.out.println("There was a problem changing the member's standing.");
                waitForEnter();
            }
        } while (menuChoice != 4);
    }
}
