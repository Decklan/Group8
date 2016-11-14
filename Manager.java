import java.util.Random;

/**
 * This class contains all the Manager method should contain
 */

public class Manager extends User {

    public Manager(int userID) {
        super(userID); 
    }

    // Adds a member to the ChocAn database
    public boolean addMember() {
        /*
        * Instead of having the Manager enter a 9 digit number when creating a Member
        * this will generate a random 9 digit number for the Provider that is in the range
        * 100000000 - 999999999.
        */
        boolean valid;      // Holds return value for isIDTaken(id)
        int newID;          // Holds the ID generated
        do {
            // Allocate random generator
            Random rand = new Random();
            // Get a number between 100000000 - 999999999
            newID = 100000000 + rand.nextInt(899999999);
            // Check to see if the ID is already taken
            valid = isIDTaken(newID);
        } while(!valid); // Repeat process if the ID is already taken

        // Prompt the Manager for a member name
        System.out.print("Provider name: ");
        String tempName = input.next();
        input.nextLine();

        // Prompt the Manager for a member address
        System.out.print("Provider Address: ");
        String tempAddress = input.next();
        input.nextLine();

        // Prompt the Manager for a member city
        System.out.print("Provider city: ");
        String tempCity = input.next();
        input.nextLine();

        // Prompt the Manager for a member state
        System.out.print("Provider State: ");
        String tempState = input.next();
        input.nextLine();

        // Prompt the Manager for a member zip code
        System.out.print("Provider ZipCode: ");
        int tempZip = input.nextInt();

        // Store the member status
        String tempStatus = "member";

        // Add the member to the database with a call to addOrganization()
        data.addOrganization(newID, tempName, tempAddress, tempCity, tempState, tempZip, tempStatus);

        return true;
    }

    // Adds a provider to the ChocAn database
    public boolean addProvider() {
        /*
        * Instead of having the Manager enter a 9 digit number when creating a Provider
        * this will generate a random 9 digit number for the Provider that is in the range
        * 100000000 - 999999999.
        */
        boolean valid;      // Holds return value for isIDTaken(id)
        int newID;          // Holds the ID generated
        do {
            // Allocate random generator
            Random rand = new Random();
            // Get a number between 100000000 - 999999999
            newID = 100000000 + rand.nextInt(899999999);
            // Check to see if the ID is already taken
            valid = isIDTaken(newID);
        } while(!valid); // Repeat process if the ID is already taken

        // Prompt the Manager for a provider name
        System.out.print("Provider name: ");
        String tempName = input.next();
        input.nextLine();

        // Prompt the Manager for a provider address
        System.out.print("Provider Address: ");
        String tempAddress = input.next();
        input.nextLine();

        // Prompt the Manager for a provider city
        System.out.print("Provider city: ");
        String tempCity = input.next();
        input.nextLine();

        // Prompt the Manager for a provider state
        System.out.print("Provider State: ");
        String tempState = input.next();
        input.nextLine();

        // Prompt the Manager for a provider zip code
        System.out.print("Provider ZipCode: ");
        int tempZip = input.nextInt();

        // Store the provider status
        String tempStatus = "provider";

        // Add the provider to the database with a call to addOrganization()
        data.addOrganization(newID, tempName, tempAddress, tempCity, tempState, tempZip, tempStatus);

        return true;
    }

    //  Returns true if the length of the ID passed in is == 9
    public boolean validIDLength(int id) {
        /*
         * Perform a check on the length of the id passed in as an argument.
         *      1. If the ID passed in isn't 9 digits in length return false.
         *      2. Otherwise the length is 9 digits so return true.
         */
        if (Integer.toString(id).length() != 9)
            return false;
        else
            return true;
    }
    
    // Checks the database to see if the ID passed in as an argument is already taken
    public boolean isIDTaken(int id) {
        /*
         * Runs the userVerification function on the ID passed in as an argument. If the user ID
         * gets verified the return value of that function isn't invalid. This means the ID is
         * already taken. If the return value is "invalid" this means that the ID isn't currently
         * in use.
         */
        String verify = data.userVerification(id);

        // If the return value of userVerification is "invalid" this means that the ID isn't in use
        if (verify == "invalid")
            return true;
        else
            return false; // If the user ID is already taken return false
    } 


/*********  Driver  **********/
    public void run() {
        System.out.println("Manager driver");
    }
}
