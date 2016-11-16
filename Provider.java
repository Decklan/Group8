import java.sql.Date;
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
		
        if(providerDirectory != null) {		
            System.out.println(providerDirectory);		
        }		
        else {		
            System.out.println("Provider Directory Is Empty");		
        }		
		
    }
    
    
    /**
     *Input : memberID number to verify status
     *Output: True for member and false for suspended
     *This function will also set memberID to verifyMember if the user is a member
    */
    public boolean memberVerification(int verifyMember) {
        String memberType;

        //returns member, provider, manager or suspended 
        memberType = DataAccess.userVerification(verifyMember);

        if(memberType.equals("member")){
            memberID = verifyMember;
            System.out.println("member");
            return true;
        }
        else if(memberType.equals("suspended")){
            System.out.println("Member suspended.");
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
        int serviceID = getIntAnswer("Enter the service id: ", 6);

        while(data.serviceVerification(serviceID) == false) {
            System.out.println("Invalid service ID!");
            serviceID = getIntAnswer("Enter the service id: ", 6);
        }

        String comment = getStringAnswer("Enter a comment (100 max characters): ", 100);
        data.createBill(memberID, userID, serviceID, 1, currentDate, comment); 
        return true;
    }

/*********  Driver  **********/
    public void run() {
        System.out.println("Provider Terminal Mode");

        int tempID;
        do {
            tempID = getIntAnswer("Please enter member ID: ", 9);
        } while(!memberVerification(tempID));
    }
}
