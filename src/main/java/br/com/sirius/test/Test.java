package br.com.sirius.test;

import java.sql.SQLException;
import java.util.List;

import br.com.sirius.connection.ConnectionFactory;
import br.com.sirius.dao.PedidoDAO;
import br.com.sirius.dao.ProdutoDAO;
import br.com.sirius.dao.UserDAO;
import br.com.sirius.model.Pedido;
import br.com.sirius.model.Produto;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ConnectionFactory con = new ConnectionFactory();

		List<Pedido> pedidos =  new PedidoDAO(con.conectar()).getPedido(1);
		for (Pedido pedido : pedidos) {
			System.out.println(pedido);
		}
	
		Produto produto = new ProdutoDAO(ConnectionFactory.conectar()).getProduto(1);
		System.out.println(produto);

		
		
		
		
	}
}
