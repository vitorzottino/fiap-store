package br.com.sirius.model;

public class Pedido extends Produto {

	private int idPedido;
	private int idUser;
	private int quantidade;
	private String data;

	public Pedido() {
		super();
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getData() {
		return data;
	}
   
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", idUser=" + idUser + ", quantidade=" + quantidade + ", data=" + data
				+ "Nome produto= " + this.getNome()  +"]";
	}

}
