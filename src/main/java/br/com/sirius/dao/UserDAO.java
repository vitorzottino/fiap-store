package br.com.sirius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sirius.model.User;

public class UserDAO {

	private Connection con;
	private String query;
	private PreparedStatement stmt;
	private ResultSet rs;

	public UserDAO(Connection con) {
		this.con = con;
	}

	public User userLogin(String email, String senha) {
		User user = null;
		try {
			query = "SELECT * FROM tb_user_ss WHERE email = ? AND senha=?";
			stmt = this.con.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
			
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		return user;
	}

}
