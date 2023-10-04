package br.com.sirius.model;

public class ItemCarrinho extends Produto{
	private int quantidade;

	public ItemCarrinho() {
		super();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
