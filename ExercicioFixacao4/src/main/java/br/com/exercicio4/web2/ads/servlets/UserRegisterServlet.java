package br.com.exercicio4.web2.ads.servlets;

import java.io.IOException;
import java.sql.Connection;

import br.com.exercicio4.web2.ads.model.User;
import br.com.exercico4.web2.ads.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

    @Override
    public void init() {
        connection = (Connection) getServletContext().getAttribute("DBConnection");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(0, name, email, password);
        UserDao userDao = new UserDao(connection);

        try {
            if (userDao.registerUser(user)) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("registerUser.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registerUser.jsp?error=2");
        }
    }
}