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

        if (providerDirectory != null) {
            System.out.println(providerDirectory);
        } else {
            System.out.println("Provider Directory Is Empty");
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
            System.out.println("member is valid");
            return true;
        } else if (memberType.equals("suspended")) {
            System.out.println("Member suspended.");
            return false;
        }
        System.out.println("Invalid member ID. Unable to verify member. Try Again.");
        return false;
    }

    /* Name: createBill
     * Input: None
     * Output: boolean
     * Description: Prompts for information required in bill and passes data as args for data access.*/

    public boolean createBill() {
        Date currentDate = new Date(System.currentTimeMillis());
        int serviceID;

        //Get membership ID number while checking if it's valid or not.
        do {
            System.out.println("Enter the member id: ");
            memberID = input.nextInt();     //CHECK INPUT

        }while(!memberVerification(memberID));           //What if a Provider discoves a member is invalid? do we need an exit statement to avoid getting stuck?

        //Get service ID number while checking if it's valid or not.
        System.out.println("Enter the service id: ");
        serviceID = input.nextInt();        //CHECK INPUT

        while (data.serviceVerification(serviceID) == false) {
            System.out.println("Invalid service ID!");
            System.out.println("Enter the service id:");
            serviceID = input.nextInt();        //CHECK INPUT

        }
        System.out.println("Enter a comment, up to 100 characters");
        String comment = input.next();      //CHECK INPUT
        data.createBill(memberID, userID, serviceID, 1, currentDate, comment);
        return true;
    }


    public int menudisplay() {
        int MenuChoice = 0;     //stores user's response when making Menu selections
        System.out.println("Welcome to the Provider Menu.");

        do {
            //Print out menu options to screen
            System.out.println("###########################################################");
            System.out.println("## Provider Menu Options:                                 ##");
            System.out.println("##\t (1) Validate Member                                  ##");
            System.out.println("##\t (2) Create Bill for Member                           ##");
            System.out.println("##\t (3) Display Provider Directory                       ##");
            System.out.println("##\t (4) Exit Provider menu                               ##"); // Not implemented(?)
            System.out.println("###########################################################");

            System.out.print("Enter your choice (1-4): "); // Prompt manager for a choice

            MenuChoice = input.nextInt();   //Input Provider's choice   //CHECK INPUT

                if(MenuChoice <= 0 || MenuChoice > 4) {

                System.out.println("\n Please make a valid choice!");
            }
        } while (MenuChoice <= 0 || MenuChoice > 4);
        return MenuChoice;
    }





    /*********  Driver  **********/
    public void run() {
        int Providerchoice;
        do {
            Providerchoice = menudisplay();

            if (Providerchoice == 1) {       //Provider chose to Validate Member
                System.out.println("Enter the member ID number: ");     //Get the Member's ID number
                memberID = input.nextInt();         //CHECK INPUT
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