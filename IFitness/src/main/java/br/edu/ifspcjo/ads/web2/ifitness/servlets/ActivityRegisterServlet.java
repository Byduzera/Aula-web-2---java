package br.edu.ifspcjo.ads.web2.ifitness.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import br.edu.ifspcjo.ads.web2.ifitness.dao.ActivityDao;
import br.edu.ifspcjo.ads.web2.ifitness.dao.UserDao;
import br.edu.ifsocjo.web2.ifitness.model.Activity;
import br.edu.ifsocjo.web2.ifitness.model.ActivityType;
import br.edu.ifsocjo.web2.ifitness.model.User;
import br.edu.ifspcjo.ads.web2.ifitness.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/activityRegister")
public class ActivityRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivityRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ActivityType type = ActivityType.valueOf(req.getParameter("type"));
		LocalDate date = LocalDate.parse(req.getParameter("date"));
		Double distance = Double.parseDouble(req.getParameter("distance"));
		Integer duration = Integer.parseInt(req.getParameter("duration"));

		// buscar User logado
		Optional<User> optional = Optional.empty();
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("loggedUser")) {
					UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
					optional = userDao.getUserByEmail(c.getValue());
				}			}
		}

		RequestDispatcher dispatcher = null;
		
		if(optional.isPresent()) {
			Activity activity = new Activity();
			activity.setType(type);
			activity.setDate(date);
			activity.setDistance(distance);
			activity.setDuration(duration);
			activity.setUser(optional.get());
			ActivityDao activityDao = new ActivityDao(DataSourceSearcher.getInstance().getDataSource());
			if(activityDao.save(activity)) {
				req.setAttribute("result", "registered");
				dispatcher = req.getRequestDispatcher("/activity-register.jsp");
			}
		} else {
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("/activity-register.jsp");
		}

		dispatcher.forward(req, resp);
	}

}


