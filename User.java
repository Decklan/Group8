/**
 * This class is the abstract base class for the manager/provider hierarchy.
 */
public abstract class User {
    protected int userID;            // Holds the user ID for the person using the ChocAn system
    protected String userTitle;      // Holds the user title (manager/provider) for the person using the ChocAn system

    // Plain old default constructor
    User() {}

    // Constructor to initialize protected variables in the base class
    User(int ID, String title) {
        this.userID = ID;
        this.userTitle = title;
    }

    // Test display function
}
