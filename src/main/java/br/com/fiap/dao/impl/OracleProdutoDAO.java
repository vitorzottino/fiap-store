package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.connection.ConnectionManager;
import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Produto;

public class OracleProdutoDAO implements ProdutoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Produto produto) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO PRODUTOS (NOME, QUANTIDADE, VALOR, DT_FABRICACAO, CATEGORIA) VALUES (?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setDouble(3, produto.getValor());
			java.sql.Date data = new java.sql.Date(produto.getDataFabricacao().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, produto.getCategoria().getCodigo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Produto produto) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE PRODUTOS SET NOME = ?, QUANTIDADE = ?, VALOR = ?, DT_FABRICACAO = ?, CATEGORIA = ? WHERE CODIGO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getQuantidade());
			stmt.setDouble(3, produto.getValor());
			java.sql.Date data = new java.sql.Date(produto.getDataFabricacao().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, produto.getCategoria().getCodigo());
			stmt.setInt(6, produto.getCodigo());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM PRODUTOS WHERE CODIGO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Produto buscar(int id) {
		Produto produto = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM PRODUTOS INNER JOIN TB_CATEGORIA ON PRODUTOS.CATEGORIA = TB_CATEGORIA.CODIGO WHERE PRODUTOS.CODIGO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt("CODIGO");
				String nome = rs.getString("NOME");
				int qtd = rs.getInt("QUANTIDADE");
				double valor = rs.getDouble("VALOR");
				java.sql.Date data = rs.getDate("DT_FABRICACAO");
				Calendar dataFabricacao = Calendar.getInstance();
				dataFabricacao.setTimeInMillis(data.getTime());
				int codigoCategoria = rs.getInt("CATEGORIA");
				String nomeCategoria = rs.getString("TB_CATEGORIA.NOME");

				produto = new Produto(codigo, nome, valor, dataFabricacao, qtd);
				Categoria categoria = new Categoria(codigoCategoria, nomeCategoria);
				produto.setCategoria(categoria);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return produto;
	}

	@Override
	public List<Produto> listar() {
		List<Produto> lista = new ArrayList<Produto>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement(
					"SELECT * FROM PRODUTOS INNER JOIN TB_CATEGORIA ON PRODUTOS.CATEGORIA = TB_CATEGORIA.CODIGO");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo = rs.getInt("CODIGO");
				String nome = rs.getString("NOME");
				int qtd = rs.getInt("QUANTIDADE");
				double valor = rs.getDouble("VALOR");
				java.sql.Date data = rs.getDate("DT_FABRICACAO");
				Calendar dataFabricacao = Calendar.getInstance();
				dataFabricacao.setTimeInMillis(data.getTime());
				int codigoCategoria = rs.getInt("CATEGORIA");
				String nomeCategoria = rs.getString("TB_CATEGORIA.NOME");

				Produto produto = new Produto(codigo, nome, valor, dataFabricacao, qtd);
				Categoria categoria = new Categoria(codigoCategoria, nomeCategoria);
				produto.setCategoria(categoria);
				lista.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

}
