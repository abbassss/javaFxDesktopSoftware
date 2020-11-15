package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class for handle database connections
 * */
public class DBConnection {
	
	// Testen
		private static String DB_DRIVER = "org.postgresql.Driver";
		private static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/contactAppDb";
		private static String DB_USER = "postgres";
		private static String DB_PW = "postgres";


		
		// Connection Objekt
		private static Connection con;

		public static void createConnection() throws SQLException, ClassNotFoundException {
				Class.forName(DB_DRIVER);
				
				con = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
				
				
		}

		/**
		 * Initialisiert eine Datenbankverbindung
		 * 
		 * @throws SQLException
		 * @throws ClassNotFoundException 
		 */
		public static Connection getDBConnection() throws SQLException {

//			S.o1("DATENBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANK");
			if (con == null || con.isClosed()) {
				try {
					createConnection();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(!con.isValid(1)) {
				try {
					createConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return con;

		}

		public static void closeDBConnection() {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
