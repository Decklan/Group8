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
            System.out.print("Invalid ID format. Please enter a " + length + "-digit ID number: ");
            idNumber = Integer.parseInt(System.console().readLine());
        }
        return idNumber;
    }

    private String promptString(String prompt, int maxLength) {
        System.out.print(prompt);
        String buffer = System.console().readLine();
        return buffer.substring(0, Math.min(maxLength, buffer.length()));
    }

    //Prompts the provider to create the bill
    public boolean createBill() {
        Date currentDate = new Date(System.currentTimeMillis());
        int serviceID = promptID("service", 6);
        String comment = promptString("Enter a comment (100 max characters): ", 100);
        data.createBill(887320551, 502891894, serviceID, 1, currentDate, comment); 
        return true;
    }

/*********  Driver  **********/
    public void run() {
        //memberVerification()
        System.out.println("Provider driver");

    }
}
