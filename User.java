/**
 * This class is the abstract base class for the manager/provider hierarchy. The run
 * function acts as the menu for the provider/manager that gets allocated.
 */

public abstract class User extends Utility {
    protected int userID;            // holds provider/manager ID
    protected DataAccess data;       // Data variable for communication with the database

    User(int userID) {
        super();                     // Call base Utility class constructor
        this.userID = userID;        // Set the userID
        data = new DataAccess();     // Allocate the data access object
    }



    protected String getStringAnswer(String question, int maxLength) {
        return promptString(question, maxLength);
    }

    protected int getIntAnswer(String question, int maxLength) {
        while(true) {
            try {
                return Integer.parseInt(promptString(question, maxLength));
            } catch (NumberFormatException ne) {
                System.out.println("\tPlease enter a number.");
            }
        }
    }

    /* Name: promptString
     * Input: String prompt, int maxLength
     * Output: String
     * Description: Prompts for a message and truncates input by length passed as argument.*/
    private String promptString(String prompt, int maxLength) {
        do {
            System.out.print(prompt);
            String buffer = System.console().readLine();
            
            if(buffer.length() <= maxLength)
                return buffer;
            else
                System.out.println("Invalid Length. Try Again.");
        } while (true);

    }

    public abstract void run();
}
