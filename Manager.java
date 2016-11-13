/**
 * This class contains all the Manager method should contain
 */

public class Manager extends User {

    public Manager(int userID) {
        super(userID); 
    }

    //TODO
    private boolean addMember() {
    // check if id is 9 digits. validIDLength(int id)
    //
    // check if isIDTaken(int id)
    //
    // 
    // Call dataAcess.addOrganization(int userID, String name, String street, 
    //                  String city, String state, int zipcode,
    //                  status='provider')
    //
        return true;
    }

    //TODO
    private boolean addProvider() {
    // check if id is 9 digits. validIDLength(int id)
    //
    // check if isIDTaken(int id)
    //
    // 
    // Call dataAcess.addOrganization(int userID, String name, String street, 
    //                  String city, String state, int zipcode,
    //                  status='provider')
    //
        return true;
    }

    //TODO
    private boolean validIDLength(int id) {
        return true;
    }
    
    //TODO
    private boolean isIDTaken(int id) {
    // Call dataAccess.userVerification(int userID)
    // to check if userID is taken. we would want 'invalid' return
        return true;
    } 


/*********  Driver  **********/
    public void run() {
        System.out.println("Manager driver");

    }
}
