<%@page import="br.com.sirius.model.User"%>
<%@include file="includes/IncludeCarrinho.jsp"%>
<%@include file="includes/auth.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style/StyleCarrinho.css">

<title>Carrinho</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/IncludeNavbar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Valor Final: R$${0+valor}</h3>
			<a class="mx-3 btn btn-primary" href="finalizar-pedido">Finalizar Pedido</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Categoria
					<th scope="col">Preço</th>
					<th scope="col">Comprar Agora</th>
					<th scope="col">Cancelar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (carrinho != null) {
					for (ItemCarrinho i : lc) {
				%>
				<tr>
					<td><%=i.getNome() %></td>
					<td><%=i.getCategoria() %></td>
					<td>R$<%=i.getPreco() %></td>
					<td>
						<form action="comprar-agora" method="post" class="form-inline w-50">
							<input type="hidden" name="id" value="<%=i.getId() %>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre" href="alterar-quantidade?action=dec&id=<%=i.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantidade" class="form-control form-control-sm" value="<%= i.getQuantidade() %>"
									readonly> <a class="btn btn-sm btn-incre" href="alterar-quantidade?action=inc&id=<%=i.getId()%>">
									<i class="fas fa-plus-square"></i>
								</a>
								<button type="submit" class="btn btn-primary btn-sm">Comprar</button>
							</div>
							
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger" href="remover-carrinho?id=<%= i.getId()%>">Remover</a></td>
				</tr>

				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>


	<%@include file="includes/footer.jsp"%>
</body>
</html>
