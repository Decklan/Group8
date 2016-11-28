import java.sql.Date;
import sun.misc.Cleaner;
import java.util.Random;
/**
 * This class contains all the provider methods should contain.
 */

public class Provider extends User {
    private int memberID;

    public Provider(int userID) {
        super(userID);
    }

    //This method displays the Provider Directory.
    ////The directory stores service names, service codes, and service fees.		
    public void providerDirectoryDisplay() {
        String providerDirectory = data.directoryLookUp();

        clearScreen();
        if (providerDirectory != null) {
            System.out.println(providerDirectory);
        } else {
            warningMessage("Provider Directory Is Empty");
        }

    }


    /**
     * Input : memberID number to verify status
     * Output: True for member and false for suspended
     * This function will also set memberID to verifyMember if the user is a member
     */
    public boolean memberVerification(int verifyMember) {
        String memberType;

        //returns member, provider, manager or suspended 
        memberType = DataAccess.userVerification(verifyMember);

        if (memberType.equals("member")) {
            memberID = verifyMember;
            clearScreen();
            successMessage("member is valid");
            return true;
        } else if (memberType.equals("suspended")) {
            clearScreen();
            successMessage("Member suspended.");
            return false;
        }
        clearScreen();
        errorMessage("Invalid member ID. Unable to verify member. Try Again.");
        return false;
    }

    /* Name: createBill
     * Input: None
     * Output: boolean
     * Description: Prompts for information required in bill and passes data as args for data access.*/

    public boolean createBill() {
        Date currentDate = new Date(System.currentTimeMillis());
        int serviceID;
        int again;

        clearScreen();
        //Get membership ID number while checking if it's valid or not.
        do {
            memberID = readInt("Enter the member id: ","Please enter a numerical id");
        }while(!memberVerification(memberID));

        //Get service ID number while checking if it's valid or not.
        serviceID = readInt("Enter the service id: ","Please enter a numerical id");
        while (!data.serviceVerification(serviceID)) {
            errorMessage("Invalid service ID!");
            serviceID = readInt("Enter the service id: ","Please enter a numerical id");
        }

        clearScreen();
        //Get comment from provider about service
        String comment = readString("Enter a comment, up to 100 characters: ");
        data.createBill(memberID, userID, serviceID, 1, currentDate, comment);

        clearScreen();
        successMessage("Bill successfuly saved.");
        return true;
    }


    public int menuDisplay() {
        int MenuChoice = 0;     //stores user's response when making Menu selections

        System.out.println("Welcome to the Provider Menu.");

        do {
            //Print out menu options to screen
            System.out.println("###########################################################");
            System.out.println("## Provider Menu Options:                                 ##");
            System.out.println("##\t (1) Validate Member                                  ##");
            System.out.println("##\t (2) Create Bill for Member                           ##");
            System.out.println("##\t (3) Display Provider Directory                       ##");
            System.out.println("##\t (4) Exit Provider menu                               ##");
            System.out.println("###########################################################");

            System.out.print("Enter your choice (1-4): ");
           MenuChoice = readInt("","Enter your choice (1-4): ");

            if(MenuChoice <= 0 || MenuChoice > 4) {
                clearScreen();
                errorMessage("\n Please make a valid choice!");
            }
        } while (MenuChoice <= 0 || MenuChoice > 4);
        return MenuChoice;
    }





    /*********  Driver  **********/
    public void run() {
        int Providerchoice;
        do {
            Providerchoice = menuDisplay();

            if (Providerchoice == 1) {       //Provider chose to Validate Member
                clearScreen();
                memberID = readInt("Enter the member ID number: ","Please enter a numerical id");
                memberVerification(memberID);
            }
            if (Providerchoice == 2) {       //Provider chose to Create Bill for Member
                createBill();
            }
            if (Providerchoice == 3) {        //Provider choseto Display Provider directory
                providerDirectoryDisplay();
            }
            if (Providerchoice == 4){
                return;
            }
        }while(Providerchoice != 0);
        return;
    }
}