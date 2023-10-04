<%@page import="br.com.sirius.model.ItemCarrinho"%>
<%@page import="br.com.sirius.dao.ProdutoDAO"%>
<%@page import="br.com.sirius.connection.ConnectionFactory"%>
<%@page import="java.util.*"%>
<%
	ArrayList<ItemCarrinho> carrinho = (ArrayList)session.getAttribute("lista-carrinho");
	List<ItemCarrinho> lc = null;
	if(carrinho != null){
		ProdutoDAO dao = new ProdutoDAO(ConnectionFactory.getConnection());
		lc = dao.listarCarrinho(carrinho);
		double valor = dao.getValor(carrinho);
		request.setAttribute("carrinho", carrinho);
		request.setAttribute("valor", valor);
		
	}

%>