public class Provider extends User{

    Provider(int id) {
        super(id);
    }

    /*Name: promptID, Input: String, Output: int
    Description: Prompts for an ID and returns a valid reponse.
    */
    protected int promptID(String name) {
        System.out.print("Enter the " + name + " ID: ");
        int idNumber = Integer.parseInt(System.console().readLine());
        if(idNumber == 0 || String.valueOf(idNumber).length() != 9) {
            System.out.print("Invalid ID format. Please enter a 9 digit ID number");
            idNumber = Integer.parseInt(System.console().readLine());
        }
        return idNumber;
    }

    public boolean createBill() {
        return true;
    }
}
