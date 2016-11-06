public class ChocAn{

	public static void main(String[] argv){
        
        DataCenter dataCenter = new DataCenter();

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

        System.out.println("Displaying all members");
        dataCenter.displayAllUser();
        //dataCenter.addUser(3, "Mike", "100");
        

        System.out.print("\n\n\n\n\n");

        System.out.println("provider directory look up...");
        dataCenter.directoryLookUp();
        //dataCenter.addUser(3, "Mike", "100");

	}
}
