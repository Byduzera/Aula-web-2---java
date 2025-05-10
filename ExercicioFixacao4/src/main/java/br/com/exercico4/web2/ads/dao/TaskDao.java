package br.com.exercico4.web2.ads.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.exercicio4.web2.ads.model.Task;

public class TaskDao {
    private Connection connection;

    public TaskDao(Connection connection) {
        this.connection = connection;
    }

    public boolean addTask(Task task) throws SQLException {
        String query = "INSERT INTO tasks (description, date, user_id) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, task.getDescription());
        stmt.setDate(2, new java.sql.Date(task.getDate().getTime()));
        stmt.setInt(3, task.getUser().getId());
        return stmt.executeUpdate() > 0;
    }

    public List<Task> getTasksByUser(int userId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE user_id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Task task = new Task(
                rs.getInt("id"),
                rs.getString("description"),
                rs.getDate("date"),
                null // O usuário pode ser preenchido após
            );
            tasks.add(task);
        }
        return tasks;
    }
}