package br.com.sirius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sirius.model.ItemCarrinho;
import br.com.sirius.model.Produto;

public class ProdutoDAO {

	private Connection con;
	private String query;
	private PreparedStatement stmt;
	private ResultSet rs;

	public ProdutoDAO(Connection con) {
		this.con = con;
	}

	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<Produto>();

		try {
			query = "SELECT * FROM tb_produtos_ss";
			stmt = this.con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setImg(rs.getString("img"));
				produtos.add(produto);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return produtos;

	}

	public List<ItemCarrinho> listarCarrinho(ArrayList<ItemCarrinho> carrinho) {

		List<ItemCarrinho> produtos = new ArrayList<ItemCarrinho>();
		try {
			if (carrinho.size() > 0) {
				for (ItemCarrinho i : carrinho) {
					query = "SELECT * FROM tb_produtos_ss where id=?";
					stmt = this.con.prepareStatement(query);
					stmt.setInt(1, i.getId());
					rs = stmt.executeQuery();
					while (rs.next()) {
						ItemCarrinho it = new ItemCarrinho();
						it.setId(rs.getInt("id"));
						it.setNome(rs.getString("nome"));
						it.setCategoria(rs.getString("categoria"));
						it.setPreco(rs.getInt("preco") * i.getQuantidade());
						it.setQuantidade(i.getQuantidade());
						produtos.add(it);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;

	}

	public double getValor(ArrayList<ItemCarrinho> carrinho) {

		double valor = 0;
		try {
			if (!carrinho.isEmpty()) {
				for (ItemCarrinho i : carrinho) {
					query = "SELECT preco FROM tb_produtos_ss where id=?";
					stmt = this.con.prepareStatement(query);
					stmt.setInt(1, i.getId());
					rs = stmt.executeQuery();
					while (rs.next()) {
						valor += rs.getDouble("preco") * i.getQuantidade();

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return valor;
	}
}
