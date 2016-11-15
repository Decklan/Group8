import java.sql.Date;
/**
 * This class contains all the provider methods should contain.
 */

public class Provider extends User {
    private int memberID;

    public Provider(int userID) {
        super(userID);
    }

    //TODO
    //NOTE: Try and use the promptID function
    private void memberVerification() {
        //Using a do-while loop? ask for member ID and then call
        //dataAccess.userVerification(int id)
        //if invalid, loop again. 
        //do until return "member"
        
        //this.memberID = 
    }

    /* Name: promptID
     * Input: String name,int length 
     * Output: int id
     * Description: Prompts for an ID and returns a valid reponse. */
    private int promptID(String name, int length) {
        System.out.print("Enter the " + name + " ID: ");
        int idNumber = Integer.parseInt(System.console().readLine());
        if(idNumber == 0 || String.valueOf(idNumber).length() != length) {
            System.out.print("Invalid ID format. Please enter a " + length + "-digit ID number");
            idNumber = Integer.parseInt(System.console().readLine());
        }
        return idNumber;
    }

    public boolean createBill() {
        Date currentDate = new Date(System.currentTimeMillis());
        int serviceID = promptID("service", 6);
        return true;
    }

/*********  Driver  **********/
    public void run() {
        //memberVerification()
        System.out.println("Provider driver");

    }
}
