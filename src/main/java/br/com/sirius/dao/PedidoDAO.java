package br.com.sirius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

	public List<Pedido> getPedido(int id) {
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {

			query = "select * from tb_pedidos_ss where id_usuario=? order by id desc";
			stmt = this.con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Pedido pedido = new Pedido();
				ProdutoDAO dao = new ProdutoDAO(this.con);
				Produto produto = dao.getProduto(rs.getInt("id_produto"));

				pedido.setIdPedido(rs.getInt("id"));
				pedido.setId(rs.getInt("id_produto"));
				pedido.setNome(produto.getNome());
				pedido.setCategoria(produto.getCategoria());
				pedido.setPreco(produto.getPreco()*rs.getInt("quantidade"));
				pedido.setQuantidade(rs.getInt("quantidade"));
				pedido.setData(rs.getString("dt_pedido"));
				pedidos.add(pedido);
		
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}

}
