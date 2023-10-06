<%@page import="java.util.List"%>
<%@page import="br.com.sirius.dao.ProdutoDAO"%>
<%@include file="includes/auth.jsp"%>
<%@include file="includes/IncludeCarrinho.jsp"%>
<%@page import="br.com.sirius.connection.ConnectionFactory"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
ProdutoDAO dao = new ProdutoDAO(ConnectionFactory.conectar());
List<Produto> produtos = dao.listarTodos();
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sirius Store</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/IncludeNavbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">Produtos</div>
		<div class="row">
			<%
			if (!produtos.isEmpty()) {
				for (Produto p : produtos) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top object-fit: cover;" src="img/<%=p.getImg() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getNome()%></h5>
						<h6 class="price">
							Preço: R$<%=p.getPreco()%></h6>
						<h6 class="category">
							Categoria:
							<%=p.getCategoria()%></h6>
						<div class="mt-3 d-flex justify-content-between gap-4">
						
						<%if(auth != null){%>
						<a href="adicionar-carrinho?id=<%=p.getId()%>" 
						class="btn btn-dark ">Adicionar ao carrinho</a> 
						<a href="comprar-agora?quantidade=1&id=<%=p.getId() %>" class="btn btn-primary">Comprar Agora</a>
						<%}else{%>
						<a href="login.jsp" 
						class="btn btn-dark ">Adicionar ao carrinho</a> 
						<a href="login.jsp" class="btn btn-primary">Comprar Agora</a>
						<%}%>
						
						</div>

					</div>
				</div>
			</div>

			<%
			}
			}
			%>


		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>
