import java.sql.*;

public class DataAccess{

    private static Connection connection = null;

    DataAccess() {
        connectDatabase();
    }

    private static void connectDatabase() {

        //Check if there's postgresql driver is included when compile
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return;
        }

        //Connect to database
        try{
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://107.170.244.89:5432/datacenter", "datacenter", "thereisnopassword");
        }catch(SQLException e){
            System.out.println("Unable to connect to database. Check credentials and host.");
            System.exit(1);
        }
    }

    //Return "invalid", "provider", member", "suspended", "manager"
    public static String userVerification(int userID) {
        connectDatabase();
        String query = "SELECT * FROM organization WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID); 
            ResultSet results = preparedStatement.executeQuery();

            if(results.next()) {
                String status = results.getString("status");
                return status;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "invalid";
    }

    public boolean serviceVerification(int serviceID) {
        String query = "SELECT * FROM provider_directory WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, serviceID); 
            ResultSet results = preparedStatement.executeQuery();

            if(results.next())
                return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suspendMember(int memberID) {
        String query = "UPDATE organization set status = 'suspended' where id= ? and status = 'member'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID); 
            preparedStatement.executeUpdate();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unsuspendMember(int memberID) {
        String query = "UPDATE organization set status = 'member' where id= ? and status = 'suspended'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID); 
            preparedStatement.executeUpdate();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //Query that will add a new member or provider
    public boolean addOrganization(int userID, String name, String street, 
                                   String city, String state, int zipcode, String status) {
        String query = "INSERT INTO organization (id, name, street, city, state, zipcode, status)"
                        +" VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID); 
            preparedStatement.setString(2, name); 
            preparedStatement.setString(3, street); 
            preparedStatement.setString(4, city); 
            preparedStatement.setString(5, state); 
            preparedStatement.setInt(6, zipcode); 
            preparedStatement.setString(7, status); 

            preparedStatement.executeUpdate();
            System.out.println("Successfully added a new " + status);
            return true;

        } catch(SQLException e) {
            System.out.println("Fail adding new member");
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateOrganization(int userID, String name, String street, 
                                   String city, String state, int zipcode, String status) {
        String query = "UPDATE organization SET name = ?, street = ?, city = ?, state = ?, zipcode = ?, status = ?)"
                        +" WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name); 
            preparedStatement.setString(2, street); 
            preparedStatement.setString(3, city); 
            preparedStatement.setString(4, state); 
            preparedStatement.setInt(5, zipcode); 
            preparedStatement.setString(6, status); 
            preparedStatement.setInt(7, userID); 

            preparedStatement.executeUpdate();
            return true;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //A query that will create bill for member
    public boolean createBill(int memberID, int providerID, int serviceID, int nuConsultation, Date provideddate, String comment) {
        String query = "INSERT INTO report (memberid, providerid, serviceid, numberconsultation, provideddate, comment)"
                        + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID); 
            preparedStatement.setInt(2, providerID); 
            preparedStatement.setInt(3, serviceID); 
            preparedStatement.setInt(4, nuConsultation); 
            preparedStatement.setDate(5, provideddate); 
            preparedStatement.setString(6, comment); 

            preparedStatement.executeUpdate();
            System.out.println("Successfully added a report");
            return true;

        } catch(SQLException e) {
            System.out.println("Fail adding report");
            e.printStackTrace();
        }
        return false;
    }

    //Query Provider directory and return it as a String
    public String directoryLookUp() {
        String query = "SELECT * FROM Provider_directory";
        StringBuilder directory = new StringBuilder();

        try { 
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet results = preparedStatement.executeQuery();

            while(results.next()) {
                directory.append("\n");
                directory.append("id: " + results.getInt("id") + "\n");
                directory.append("name: " + results.getString("name") + "\n");
                directory.append("fee: $" + results.getInt("fee") + "\n");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return directory.toString();
	}

    //query that will return member bill as a string
    public String getMemberBill(int memberID) {
        String query = "SELECT r.provideddate, r.providerid, o.name, r.memberid, r.serviceid, pd.fee, r.comment "+
                        "FROM organization o JOIN report r ON o.id = r.memberid "+
                        "JOIN provider_directory pd ON r.serviceid = pd.id WHERE r.memberid = ?";

        StringBuilder directory = new StringBuilder();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID); 
            ResultSet results = preparedStatement.executeQuery();

            if(results.next()) {
                directory.append("Date service was provided: " + 
                                results.getDate("provideddate") + "\n");
                directory.append("Provider number: " + 
                                results.getInt("providerid") + "\n");
                directory.append("Member name: " + results.getString("name") + "\n");
                directory.append("Member number: " + results.getInt("memberid") + "\n");
                directory.append("Service code: " + results.getInt("serviceid") + "\n");
                directory.append("Fee to be paid: $" + results.getInt("fee") + "\n");
                directory.append("Comments: " + results.getString("comment") + "\n");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return directory.toString();
    }

    public boolean deleteAllMemberReport(int memberID) {
        String query = "DELETE FROM report WHERE memberID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberID); 
            preparedStatement.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAllProviderReport(int providerID) {
        String query = "DELETE FROM report WHERE providerID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, providerID); 
            preparedStatement.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOrganization(int id, String status) {
        String query = "DELETE FROM organization WHERE id= ? and status=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id); 
            preparedStatement.setString(2, status); 
            preparedStatement.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
