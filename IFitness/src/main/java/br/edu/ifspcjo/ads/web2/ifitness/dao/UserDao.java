package br.edu.ifspcjo.ads.web2.ifitness.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsocjo.web2.ifitness.model.User;

public class UserDao {

	private DataSource dataSource;

	public UserDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
		
	public Boolean save(User user){
	
		String sql = "insert into user (name, email, password, "
				+ "birth_date, gender, active) values (?,?,?,?,?,?)";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setDate(4, Date.valueOf(user.getDateOfBirth()));
			ps.setString(5, user.getGender().toString());
			ps.setBoolean(6, true);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}
		return true;
	}
}