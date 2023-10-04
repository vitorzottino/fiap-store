package br.com.sirius.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sirius.model.ItemCarrinho;

@WebServlet("/adicionar-carrinho")
public class AdicionarCarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			List<ItemCarrinho> carrinho = new ArrayList<>();

			int id = Integer.parseInt(request.getParameter("id"));
			ItemCarrinho item = new ItemCarrinho();
			item.setId(id);
			item.setQuantidade(1);

			HttpSession sessao = request.getSession();
			ArrayList<ItemCarrinho> carrinhoSessao = (ArrayList) sessao.getAttribute("lista-carrinho");

			if (carrinhoSessao == null) {
				carrinho.add(item);
				sessao.setAttribute("lista-carrinho", carrinho);
				response.sendRedirect("index.jsp");
				

			} else {
				carrinho = carrinhoSessao;
				boolean existe = false;
				for (ItemCarrinho i : carrinhoSessao) {
					if (i.getId() == id) {
						existe = true;
						out.print("<h3 style='text-align:center'>Produto ja esta no carrinho<a href='carrinho.jsp'>Ir para o carrinho</a></h3>");
					}
				
				}
				
				if(!existe) {
					carrinho.add(item);
					response.sendRedirect("index.jsp");
					
				}

			}
			
			

		}

	}

}
