import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deckl on 11/27/2016.
 */
public class ChocAnMember {
    /*
     * These variables make up all the relevant information required to export
     * to the external file for the email requirement
     */
    String name, address, city, state;
    int memberNumber, zipCode;
    List<ServiceReport> servicesReceived;

    // Constructor for ChocAnMember
    public ChocAnMember(String name, String address, String city, String state, int memberNumber, int zipCode) {
        this.name = name;                                           // Set the name
        this.address = address;                                     // Set the address
        this.city = city;                                           // Set the city
        this.state = state;                                         // Set the state
        this.memberNumber = memberNumber;                           // Set the member number
        this.zipCode = zipCode;                                     // Set the zip code
        servicesReceived = new ArrayList<>();          // Allocate the list of services received
    }

    // Function to add a service report to the members list of services
    public void addServiceReport(String providerName, String serviceName, String serviceDate) {
    }

    public boolean exportMemberToFile() {
        /*
         * I believe this function should do a few things.
         *      1. Use a String variable to capture the concatenation of the member name and current date
         *      2. Use a File variable to create a new file
         *          * The file name should be the String variable we created
         *          * The file should be stored in a folder inside our projects root file
         *      3. Use a BufferedWriter variable to write the identifying information for the
         *         member to the external file as well as the service information for that member.
         */
        return true;
    }
}
