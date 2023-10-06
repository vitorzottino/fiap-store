package br.com.sirius.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sirius.connection.ConnectionFactory;
import br.com.sirius.dao.PedidoDAO;
import br.com.sirius.model.ItemCarrinho;
import br.com.sirius.model.Pedido;
import br.com.sirius.model.User;

@WebServlet("/finalizar-pedido")
public class FinalizarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = new Date();

			ArrayList<ItemCarrinho> carrinho = (ArrayList<ItemCarrinho>) request.getSession()
					.getAttribute("lista-carrinho");
			User user = (User) request.getSession().getAttribute("auth");
			if (!carrinho.isEmpty() && user != null) {
				for (ItemCarrinho i : carrinho) {
					Pedido pedido = new Pedido();
					pedido.setId(i.getId());
					pedido.setIdUser(user.getId());
					pedido.setQuantidade(i.getQuantidade());
					pedido.setData(sdf.format(data));
					
					PedidoDAO dao = new PedidoDAO(ConnectionFactory.conectar());
					boolean result = dao.fazerPedido(pedido);
					if(!result) 
						break;
					
				}
				carrinho.clear();
				response.sendRedirect("pedidos.jsp");

			} else {
				if (user == null)
					response.sendRedirect("login.jsp");
				response.sendRedirect("carrinho.jsp");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
