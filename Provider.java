/**
 * This class contains all the methods and members a provider should contain.
 */
public class Provider extends User {
    protected String firstName;     // Holds the providers first name
    protected String lastName;      // Holds the providers last name
    protected String address;       // Holds the providers address
    // STILL MORE VARIABLES TO ADD TO THIS CLASS

    // Constructor to instantiate Provider
    public Provider(int ID, String title, String first, String last, String addr) {
        super(ID, title); // Invoke base class constructor
        // Set all the additional variables in Provider not in the base class
        this.firstName = first;
        this.lastName = last;
        this.address = addr;
    }

    /*
     * Implement abstract display function from the base class. We don't really need to use
     * this method for anything other than displaying for testing purposes.
     */
        System.out.println("Provider ID: " + userID);
        System.out.println("Provider Name: " + firstName + " " + lastName);
        System.out.println("Provider Title: " + userTitle);
        System.out.println("Provider Address: " + address);
    }
}
