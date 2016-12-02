/**
 * This class is the abstract base class for the manager/provider hierarchy. The run
 * function acts as the menu for the provider/manager that gets allocated.
 */

public abstract class User extends Utility {
    protected int userID;            // Holds provider/manager ID
    protected DataAccess data;       // Data variable for communication with the database

    // Class constructor
    User(int userID) {
        super();                     // Call base Utility class constructor
        this.userID = userID;        // Set the userID
        data = new DataAccess();     // Allocate the data access object
    }

    // Abstract run method used as menu for control flow
    public abstract void run();
}
