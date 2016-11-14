/**
 * This class is the abstract base class for the manager/provider hierarchy.
 */

public abstract class User extends Utility {
    protected int userID;            // holds provider/manager ID
    protected DataAccess data;       // Data variable for communication with the database

    User(int userID) {
        super();                     // Call base Utility class constructor
        this.userID = userID;        // Set the userID
        data = new DataAccess();     // Allocate the data access object
    }

    public abstract void run();
}
