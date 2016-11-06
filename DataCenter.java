import java.sql.*;

class DataCenter{

	private static Connection connection = null;

    DataCenter() {
        connectDatabase();
    }

    private void connectDatabase() {

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
				"jdbc:postgresql://db.cecs.pdx.edu:5432/cuonngo", "cuonngo", "p#n4rP9wpe");
		}catch(SQLException e){
            System.out.println("Unable to connect to database. Check credentials and host.");
            System.exit(1);
		}
    }

    public String verifyUser(int userId) {
        String query = "SELECT * FROM member WHERE memberID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId); 
            ResultSet results = preparedStatement.executeQuery();

            while(results.next()) {
                String name = results.getString("memberName");
                String status = results.getString("status");
                System.out.print(name + "\n");
                return status;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(int userId, String name, String status) {
        String query = "INSERT INTO member (memberID, memberName, status) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId); 
            preparedStatement.setString(2, name); 
            preparedStatement.setString(3, status); 
            preparedStatement.executeUpdate();
            System.out.println("Successful added a new member");
        } catch(SQLException e) {
            System.out.println("Fail adding new member");
            e.printStackTrace();
        }
    }


	public static void directoryLookUp()
	{
		try{
	        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			boolean status = stmt.execute("SELECT * FROM Provider_directory");
			if(status){
	            ResultSet results = stmt.getResultSet();
	            ResultSetMetaData meta = results.getMetaData();

				int columns = meta.getColumnCount();
				for(int i = 1; i <= columns; i++){
					String columnName = meta.getColumnLabel(i);
					System.out.print(columnName + " ");
				}
				System.out.println();
				while(results.next()){
					for(int i = 1; i<= columns; i++){
						Object val = results.getObject(i);
						System.out.print(val + " ");
					}
					System.out.println();
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static void displayAllUser()
	{
		try{
	        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			boolean status = stmt.execute("SELECT * FROM member");
			if(status){
	            ResultSet results = stmt.getResultSet();
	            ResultSetMetaData meta = results.getMetaData();

				int columns = meta.getColumnCount();
				for(int i = 1; i <= columns; i++){
					String columnName = meta.getColumnLabel(i);
					System.out.print(columnName + " ");
				}
				System.out.println();
				while(results.next()){
					for(int i = 1; i<= columns; i++){
						Object val = results.getObject(i);
						System.out.print(val + " ");
					}
					System.out.println();
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}


}

