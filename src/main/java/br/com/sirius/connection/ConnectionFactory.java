package br.com.sirius.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	//RM93345
	//071103
	private static Connection connection = null;


	public static Connection conectar() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "rm93360", "100903");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		if (connection == null) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "rm93360", "100903");
			
		}
		return connection;
	}

}
