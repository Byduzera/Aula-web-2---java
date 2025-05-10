package br.com.exercicio4.web2.ads.servlets;

import java.io.IOException;
import java.sql.Connection;

import br.com.exercicio4.web2.ads.model.User;
import br.com.exercico4.web2.ads.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

    @Override
    public void init() {
        connection = (Connection) getServletContext().getAttribute("DBConnection");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao(connection);

        try {
            User user = userDao.loginUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("registerTask.jsp");
            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=2");
        }
    }
}