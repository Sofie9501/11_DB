package dk.dtu.cdiofinal.server.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	private final String HOST = "localhost";
	private final int PORT = 3306;
	private final String DATABASE = "OperatorDB";		//Skal eventuelt �ndres
	private final String USERNAME = "cdio3";
	private final String PASSWORD = "qwer1234";
	private Connection connection;
	private Statement stm;
	
	public Connector(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			stm = connection.createStatement();
			
		}catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public ResultSet doQuery(String query) throws DALException{
		ResultSet res = null;
		
		try{
			res = stm.executeQuery(query);
	
		}catch(SQLException e){
			DALException d = new DALException(e);
			throw d;
		}
		
		return res;
	}
}

