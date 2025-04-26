package br.edu.ifspcjo.ads.web2.ifitness.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import br.edu.ifsocjo.web2.ifitness.model.Activity;

public class ActivityDao {

	private DataSource dataSource;

	public ActivityDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Boolean save(Activity activity) {
		String sql = "insert into activity (type, activity_date, distance, duration, user_id) values(?,?,?,?,?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, activity.getType().toString());
			ps.setDate(2, Date.valueOf(activity.getDate()));
			ps.setDouble(3, activity.getDistance());
			ps.setInt(4, activity.getDuration());
			ps.setLong(5, activity.getUser().getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao inserir dados", sqlException);
		}
	}
}
