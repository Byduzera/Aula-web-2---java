package br.edu.ifspcjo.ads.web2.ifitness.servlets;

import java.io.IOException;
import java.util.Optional;

import br.edu.ifspcjo.ads.web2.ifitness.dao.UserDao;
import br.edu.ifsocjo.web2.ifitness.model.User;
import br.edu.ifspcjo.ads.web2.ifitness.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
		Optional<User> optional = userDao.getUserByEmailAndPassword(email, password);
		RequestDispatcher dispatcher = null;
		if(optional.isPresent()) {
			// armazenar o cookie
			Cookie cookie = new Cookie("loggedUser", email);
			cookie.setMaxAge(60*60*24); //em segundos
			resp.addCookie(cookie);
			
			req.setAttribute("name", optional.get().getName());
			dispatcher = req.getRequestDispatcher("/home.jsp");
		}else {
			// remover o cookie
			Cookie[] cookies = req.getCookies();
			if(cookies != null) {
				for(Cookie c: cookies) {
					if(c.getName().equals("loggedUser")) {
					  c.setMaxAge(0);
					  resp.addCookie(c);
					}
				}
			}
			req.setAttribute("result", "loginError");
			dispatcher = req.getRequestDispatcher("/login.jsp");
		}
		dispatcher.forward(req, resp);
	}

}
