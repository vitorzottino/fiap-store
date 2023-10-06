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

@WebServlet("/comprar-agora")
public class ComprarAgoraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = new Date();
			User user = (User) request.getSession().getAttribute("auth");
			if (user != null) {

				String idProduto = request.getParameter("id");
				int quantidade = Integer.parseInt(request.getParameter("quantidade"));

				if (quantidade < 0) {
					quantidade = 1;
				}

				Pedido pedido = new Pedido();
				pedido.setId(Integer.parseInt(idProduto));
				pedido.setIdUser(user.getId());
				pedido.setQuantidade(quantidade);
				pedido.setData(sdf.format(data));

				PedidoDAO dao = new PedidoDAO(ConnectionFactory.conectar());
				boolean result = dao.fazerPedido(pedido);
				if (result) {
					response.sendRedirect("pedidos.jsp");
					ArrayList<ItemCarrinho> carrinho = (ArrayList<ItemCarrinho>) request.getSession()
							.getAttribute("lista-carrinho");
					if (carrinho != null) {
						for (ItemCarrinho i : carrinho) {
							if (i.getId() == Integer.parseInt(idProduto)) {
								carrinho.remove(carrinho.indexOf(i));
								break;
							}
						}
					}

				} else {
					// Falha ao fazer pedido
					response.sendRedirect("pedidos.jsp");
				}

			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
