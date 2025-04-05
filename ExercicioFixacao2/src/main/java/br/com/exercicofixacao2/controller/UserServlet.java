package br.com.exercicofixacao2.controller;

import java.io.IOException;

import br.com.exerciciofixacao2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/login")
public class UserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String email = req.getParameter("email");
		String senha = req.getParameter("password");
		User user = new User();
		user.setEmail(email);
		user.setPassword(senha);
		
		if (user.getEmail().equals("marcos@hotmail.com") && user.getPassword().equals("123456")) {
			resp.sendRedirect("welcome.jsp");
			
		} else {
			resp.sendRedirect("error.jsp");
		}
	};
	
}
