/**
 * This class contains all the provider methods should contain.
 */

public class Provider extends User {
    private int memberID;

    public Provider(int userID) {
        super(userID);
    }

    //TODO
    private void memberVerification() {
        //Using a do-while loop? ask for member ID and then call
        //dataAccess.userVerification(int id)
        //if invalid, loop again. 
        //do until return "member"
        
        //this.memberID = 
    }



/*********  Driver  **********/
    public void run() {
        //memberVerification()
        System.out.println("Provider driver");

    }

    /*Name: promptID, Input: String, Output: int
    Description: Prompts for an ID and returns a valid reponse.
    */
    protected int promptID(String name) {
        System.out.print("Enter the " + name + " ID: ");
        int idNumber = Integer.parseInt(System.console().readLine());
        if(idNumber == 0 || String.valueOf(idNumber).length() != 9) {
            System.out.print("Invalid ID format. Please enter a 9 digit ID number");
            idNumber = Integer.parseInt(System.console().readLine());
        }
        return idNumber;
    }

    public boolean createBill() {
        return true;
    }
}
