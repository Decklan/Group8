/**
 * This class contains all the methods and members that a Manager should contain
 */
public class Manager extends User {

    // Constructor to instantiate Manager
    public Manager(int ID, String title) {
        super(ID, title); // Invoke base class constructor
    }

    /*
     * Implement abstract display function from the base class. We don't have to use
     * this method for anything other than displaying for testing purposes.
     */
        System.out.println("Manager ID: " + userID);
        System.out.println("Manager Title: " + userTitle);
    }
}
