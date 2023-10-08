<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="br.com.sirius.connection.ConnectionFactory"%>
<%@page import="br.com.sirius.dao.PedidoDAO"%>
<%@page import="br.com.sirius.model.*"%>
<%@page import="java.text.DecimalFormat"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%

DecimalFormat df = new DecimalFormat("#.##");
request.setAttribute("df", df);
List<Pedido> pedidos = null;
User auth = (User)request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
	pedidos = new PedidoDAO(ConnectionFactory.conectar()).getPedido(auth.getId());

}
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pedidos</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/IncludeNavbar.jsp"%>


	<div clas="container">
		<div class="card-header my-3>">Todos Pedidos <%=auth.getEmail()%></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Categoria
					<th scope="col">Preco</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Data</th>
					<th scope="col">Cancelar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (!pedidos.isEmpty()) {
					for (Pedido p : pedidos) {
				%>
				<tr>
				<td><%=p.getNome()%></td>
				<td><%=p.getCategoria()%></td>
				<td><%=p.getPreco()%></td>
				<td><%=p.getQuantidade()%></td>
				<td><%=p.getData()%></td>
				<td>
					<a class="btn btn-sm btn-danger" href="cancelar-pedido?id="
						<%=p.getIdPedido()%>>Cancelar</a>
				</td>
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
