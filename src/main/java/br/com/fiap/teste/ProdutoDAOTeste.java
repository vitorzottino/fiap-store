package br.com.fiap.teste;

import java.util.List;

import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Produto;

public class ProdutoDAOTeste {

	public static void main(String[] args) {

		ProdutoDAO dao = DAOFactory.getProdutoDAO();

		// Listar os produtos
		List<Produto> lista = dao.listar();
		for (Produto item : lista) {
			System.out.println(item.getNome() + " " + item.getQuantidade() + " " + item.getValor());
		}

	}
}