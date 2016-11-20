import sun.misc.Cleaner;
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

    // Generates a random ID number for a new member and runs addOrganization
    public boolean addMember() {
        int newID = randomDigitsID();
        return addOrganization("member", newID);
    }

    // Generates a random ID number for a new provider and runs addOrganization
    public boolean addProvider() {
        int newID = randomDigitsID();
        return addOrganization("provider", newID);
    }

    // Prompts the manager for basic organization information before adding to the database
    public boolean addOrganization(String status, int newID) {
        System.out.print(status + " name: ");
        String tempName = input.next();
        input.nextLine();

        System.out.print(status + " address: ");
        String tempAddress = input.next();
        input.nextLine();

        System.out.print(status + " city: ");
        String tempCity = input.next();
        input.nextLine();

        System.out.print(status + " state: ");
        String tempState = input.next();
        input.nextLine();

        System.out.print(status + " zipCode: ");
        int tempZip = input.nextInt();

        boolean queryStatus = data.addOrganization(newID, tempName, tempAddress, tempCity, tempState, tempZip, status);
        return queryStatus;
    }

    // Method to suspend a member
    public boolean suspendMember(int memberID) {
        // Success captures the return value of the suspendMember function
        boolean success = data.suspendMember(memberID);
        // If success is true the member was successfully suspended
        if (success) {
            System.out.println("Member successfully suspended!");
            return true;
        }
        // Otherwise the member wasn't suspended successfully
        else {
            System.out.println("Couldn't suspend member..");
            return false;
        }
    }

    // Method to unsuspend a member
    public boolean unsuspendMember(int memberID) {
        // Success captures the return value of the unsuspendMember function
        boolean success = data.unsuspendMember(memberID);
        // If success is true the member was successfully suspended
        if (success) {
            System.out.println("Member successfully unsuspended");
            return true;
        }
        // Otherwise the member wasn't suspended successfully
        else {
            System.out.println("Couldn't unsuspend member..");
            return false;
        }
    }

    // Lets a manager suspend or unsuspend a member in the database
    public boolean changeMemberStanding() {
        // Verify whether the member exists in the database
        int member;
        do {
            // Prompt for the memberID
            System.out.print("Please enter the member's ID number: ");
            member = input.nextInt();
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
            input.nextLine();

            // Run data access function corresponding to managers choice
            if (choice.equalsIgnoreCase("suspend"))
                return suspendMember(member);
            else if (choice.equalsIgnoreCase("unsuspend"))
                return unsuspendMember(member);
            else System.out.println("Please enter a valid choice!");
        } while (!choice.equalsIgnoreCase("suspend") || choice.equalsIgnoreCase("unsuspend"));
        return false; // Fail flag
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

    /* This function scans the database for a valid MEMBER (for suspension purposes)
     * This function searches the database for a member specifically. The reason for this
     * is... if we just use isIDTaken(id) for the scan it searches ALL people in the database
     * including managers/providers and we don't want to suspend or unsuspend them.
     */
    public boolean isValidMember(int memberID) {
        String verify = DataAccess.userVerification(memberID);
        if (verify.equals("member") || verify.equals("suspended"))
            return true;
        else return false;
    }

    // Displays all of the manager menu options to keep the run function shorter and sweeter
    public int menuPrompt() {
        int menuChoice = 0; // it will hold the value of the user input
        do {
            // Print menu options to the screen
            System.out.println("###########################################################");
            System.out.println("## Manager Menu Options:                                 ##");
            System.out.println("##\t (1) Add Member                                      ##");
            System.out.println("##\t (2) Add Provider                                    ##");
            System.out.println("##\t (3) Suspend/Unsuspend Member                        ##");
            System.out.println("##\t (4) Quit                                            ##"); // We need such option
            System.out.println("###########################################################");
            System.out.print("Enter your choice (1-4): "); // Prompt manager for a choice
            menuChoice = input.nextInt();
            if(menuChoice <= 0 || menuChoice > 4) {
                //clearScreen();
                System.out.println(" \033[0;31m Please make a valid choice! \033[0m");
            }
        } while (menuChoice <= 0 || menuChoice > 4);
        return menuChoice;
    }

    /*********  Driver  **********/
    // This function is used as the menu function when a manager is allocated
    public void run() {
        // Integer to hold manager's menu choice
        int menuChoice;
        //clearScreen();                            // Clear the screen when the program starts
        do {                                        // Loop to test input against valid choices
            menuChoice = menuPrompt();             // Display the menu options to the screen and get the selected option
            //clearScreen();
            // If cases handle running the appropriate method based on manager choice
            if (menuChoice == 1)
                addMember();
            else if (menuChoice == 2)
                addProvider();
            else if (menuChoice == 3)
                changeMemberStanding();
            //waitForEnter();
        } while (menuChoice != 4);
    }
}
