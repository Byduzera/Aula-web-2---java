package br.com.exercicio4.web2.ads.servlets;


import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import br.com.exercicio4.web2.ads.model.Task;
import br.com.exercicio4.web2.ads.model.User;
import br.com.exercico4.web2.ads.dao.TaskDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TaskRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

    @Override
    public void init() {
        connection = (Connection) getServletContext().getAttribute("DBConnection");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String description = request.getParameter("description");
        Date date = new Date();

        Task task = new Task(0, description, date, user);
        TaskDao taskDao = new TaskDao(connection);

        try {
            if (taskDao.addTask(task)) {
                response.sendRedirect("registerTask.jsp?success=1");
            } else {
                response.sendRedirect("registerTask.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registerTask.jsp?error=2");
        }
    }
}