package br.edu.ifspcjo.ads.web2.ifitness.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import br.edu.ifspcjo.ads.web2.ifitness.dao.ActivityDao;
import br.edu.ifspcjo.ads.web2.ifitness.dao.UserDao;
import br.edu.ifsocjo.web2.ifitness.model.Activity;
import br.edu.ifsocjo.web2.ifitness.model.User;
import br.edu.ifspcjo.ads.web2.ifitness.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// buscar User logado
		Optional<User> optional = Optional.empty();
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("loggedUser")) {
					UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
					optional = userDao.getUserByEmail(c.getValue());
				}
			}
		}

		RequestDispatcher dispatcher = null;

		if (optional.isPresent()) {
			ActivityDao activityDao = new ActivityDao(DataSourceSearcher.getInstance().getDataSource());
			List<Activity> userActivities = activityDao.getActivitiesByUser(optional.get());
			req.setAttribute("userActivities", userActivities);
			req.setAttribute("name", optional.get().getName());
			dispatcher = req.getRequestDispatcher("/home.jsp");
		} else {
			dispatcher = req.getRequestDispatcher("/login.jsp");
		}

		dispatcher.forward(req, resp);
	}
}