/**
 * This class is the abstract base class for the manager/provider hierarchy.
 */

public abstract class User {
    protected int userID;            // holds provider/manager ID

    User(int userID) {
        this.userID = userID;
    }

    abstract public void run();


}
