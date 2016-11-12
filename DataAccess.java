import java.sql.*;

public class DataAccess{

	private static Connection connection = null;

    DataAccess() {
        connectDatabase();
    }

    private static void connectDatabase() {

		//This will check if there's postgresql driver is included when compile
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return;
		}

        //Connect to cecs.pdx.edu database. 
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
            System.out.println("Successfully added a new member");
            return true;

        } catch(SQLException e) {
            System.out.println("Fail adding new member");
            e.printStackTrace();
        }
        return false;
    }

	public String directoryLookUp() {
        String query = "SELECT * FROM Provider_directory";
        StringBuilder directory = new StringBuilder();
		try{
	        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			boolean status = stmt.execute(query);

			if(status){
                //Get the result from query. This does not contains columns name.
	            ResultSet results = stmt.getResultSet();

                //We're using reseultSetMeta to get the columns name
	            ResultSetMetaData meta = results.getMetaData();

                //print columns name
				int columns = meta.getColumnCount();
				for(int i = 1; i <= columns; i++){
                    directory.append(meta.getColumnLabel(i) + " | ");
				}
                directory.append("\n");

				while(results.next()){
					for(int i = 1; i<= columns; i++){
						directory.append(results.getObject(i) + " | ");
					}
                    directory.append("\n");
				}
			}
            return directory.toString();
		}catch(SQLException e){
			e.printStackTrace();
		}
        return null;
	}


/* Code no longer work due to the changed of the database. Use for examples
    public String verifyUser(int userId) {

        //query we are going to execute
        String query = "SELECT * FROM member WHERE memberID = ?";

        try {
            //initialize statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //replace the first occurance of '?' to userID
            preparedStatement.setInt(1, userId); 

            //Execute the query onto the database
            ResultSet results = preparedStatement.executeQuery();

            //Check if the results is not empty
            if(results.next()) {

                //If it is not empty, get the data column memberName, status
                String name = results.getString("memberName");
                String status = results.getString("status");

                //print out name
                System.out.print(name + "\n");

                //Notice: status is either "Member" or "Expired". This data is corresponding to the userID
                return status;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        //If the result is empty, this will return null
        return null;
    }

    public void addUser(int userId, String name, String status) {

        //query we are going to execute
        String query = "INSERT INTO member (memberID, memberName, status) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //replace the first occurance of '?' with argument userID
            preparedStatement.setInt(1, userId); 
            //replace the second occurance of '?' with argument name
            preparedStatement.setString(2, name); 
            //replace the third occurance of '?' with argument status
            preparedStatement.setString(3, status); 

            //Execute query. Notice, we are updating data to database and its not going
            //to return anyhting. Therefore, we're not using preparedStatement.executeQuery(). Instead
            //we're going to use executeUpdate()
            preparedStatement.executeUpdate();
            System.out.println("Successful added a new member");
        } catch(SQLException e) {
            System.out.println("Fail adding new member");
            e.printStackTrace();
        }
    }


    //this case is a little bit more advance. We're going to print out all of the data and including the columnds name.
*/
}

