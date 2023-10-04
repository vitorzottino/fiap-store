package br.com.sirius.test;

import java.sql.SQLException;

import br.com.sirius.connection.ConnectionFactory;
import br.com.sirius.dao.UserDAO;

public class ConTest {
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	ConnectionFactory con = new ConnectionFactory();
	
	UserDAO dao = new UserDAO(con.getConnection());
	
	System.out.println(dao.userLogin("admin@sirius.com", "admin"));

}
}
