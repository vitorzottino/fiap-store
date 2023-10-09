package br.com.sirius.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sirius.connection.ConnectionFactory;
import br.com.sirius.dao.UserDAO;
import br.com.sirius.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String email = request.getParameter("login-email");
			String senha = request.getParameter("login-password");
			
			

			try {
				
				UserDAO dao = new UserDAO(ConnectionFactory.conectar());
				User user = dao.userLogin(email, senha);

				if (user != null) {
					
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");
				} else {
				
					response.sendRedirect("login.jsp");
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
