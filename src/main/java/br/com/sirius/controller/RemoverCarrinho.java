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

@WebServlet("/remover-carrinho")
public class RemoverCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if (id != null) {
				ArrayList<ItemCarrinho> carrinho = (ArrayList<ItemCarrinho>) request.getSession()
						.getAttribute("lista-carrinho");
				if (carrinho != null) {
					for (ItemCarrinho i : carrinho) {
						if (i.getId() == Integer.parseInt(id)) {
							carrinho.remove(carrinho.indexOf(i));
							break;
						}
					}
					response.sendRedirect("carrinho.jsp");

				}

			} else {
				response.sendRedirect("carrinho.jsp");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
