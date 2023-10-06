package br.com.sirius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.sirius.model.*;

public class PedidoDAO {

	private Connection con;
	private String query;
	private PreparedStatement stmt;
	private ResultSet rs;

	public PedidoDAO(Connection con) {
		this.con = con;
	}

	public boolean fazerPedido(Pedido pedido) {
		
		boolean result = false;
		try {
			query = "INSERT INTO TB_PEDIDOS_SS (ID_PRODUTO, ID_USUARIO, QUANTIDADE, DT_PEDIDO)VALUES( ?, ?, ?, ?)";
			stmt = this.con.prepareStatement(query);
			stmt.setInt(1, pedido.getId());
			stmt.setInt(2, pedido.getIdUser());
			stmt.setInt(3, pedido.getQuantidade());
			stmt.setString(4, pedido.getData());
			stmt.execute();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*public List<Produto> getPedido(User user) {
		
	}*/

}
