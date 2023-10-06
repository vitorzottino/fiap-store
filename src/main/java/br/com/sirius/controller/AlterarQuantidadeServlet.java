package br.com.sirius.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sirius.model.ItemCarrinho;

@WebServlet("/alterar-quantidade")
public class AlterarQuantidadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter();) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));

			ArrayList<ItemCarrinho> carrinho = (ArrayList<ItemCarrinho>) request.getSession()
					.getAttribute("lista-carrinho");

			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (ItemCarrinho i : carrinho) {
						if (i.getId() == id) {
							int quantidade = i.getQuantidade();
							quantidade++;
							i.setQuantidade(quantidade);
							response.sendRedirect("carrinho.jsp");

						}
					}
				}
			}

			if (action != null && id >= 1) {
				if (action.equals("dec")) {
					for (ItemCarrinho i : carrinho) {
						if (i.getId() == id && i.getQuantidade() > 1) {
							int quantidade = i.getQuantidade();
							quantidade--;
							i.setQuantidade(quantidade);
							break;

						}
					}
					response.sendRedirect("carrinho.jsp");
				}
			} else {
				response.sendRedirect("carrinho.jsp");

			}
		}

	}

}
