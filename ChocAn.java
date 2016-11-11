public class ChocAn{

	public static void main(String[] argv){
        DataAccess dataAccess = new DataAccess();

        System.out.println(DataAccess.userVerification(123213));
        System.out.println(DataAccess.userVerification(768921693));
        System.out.println(DataAccess.userVerification(587857871));
        System.out.println(DataAccess.userVerification(454419772));
        System.out.println(DataAccess.userVerification(433671813));
    


/*  Code no longer work due to the changed of database. Use these as example.
        System.out.println("\nloooking up id 1123132....");
        if(dataCenter.verifyUser(1123132) == null) {
            System.out.println("Invalid ID number");
        }


        System.out.print("\n\n\n\n\n");
    
        System.out.println("loooking up id 860394119....");
        if(dataCenter.verifyUser(860394119) == null) {
            System.out.println("Invalid ID number");
        }

        System.out.print("\n\n\n\n\n");

        System.out.println("provider directory look up...");
        dataCenter.directoryLookUp();
        //dataCenter.addUser(3, "Mike", "100");
*/

	}
}
