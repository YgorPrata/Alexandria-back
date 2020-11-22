package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.LoginDao;
import com.restapp.model.entities.User;

public class LoginDaoJDBC extends DB implements LoginDao{
	
	public Connection conn;
	
	public LoginDaoJDBC() {
		
	}

	public LoginDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insertUser(User usuario) {
		String sql = "SELECT u.login, u.senha FROM usuario AS u WHERE u.login = ?";  
		String user = usuario.getUser();
		boolean sucesso = false;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				sucesso = false;
				System.out.println("login existente. usuario nao cadastrado");
			}
			else {
				try {			
					ps = conn.prepareStatement("INSERT INTO usuario (login, senha, role, nome) VALUES (?, SHA2(?, 256), ?, ?)");
					ps.setString(1, usuario.getUser());
					ps.setString(2, usuario.getPassword());
					ps.setString(3,  usuario.getRole());
					ps.setString(4, usuario.getUser_name());
					ps.executeUpdate();
					sucesso = true;
					System.out.println("usuario cadastado");
				}
					
				catch(SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
			return sucesso;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
		
	}


	@Override
	public User validaUser(String user, String password) {
		String sql = "SELECT * FROM usuario AS u WHERE u.login = ? AND u.senha = ?";  
		String encryptpw = DigestUtils.sha256Hex(password);
		User userinfo;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, encryptpw);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				userinfo = new User(rs.getInt("u.id_user"), rs.getString("u.login"), null, rs.getString("u.role"), rs.getString("u.nome"));
				return userinfo;
			}
			else {
				return null;
			}
	
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	@Override
	public String validaRoleUser(String user) {
		String sql = "SELECT u.role FROM usuario AS u WHERE u.login = ?";  

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			
			String role = null;
			
			if(rs.next()) {
				role = rs.getString("u.role");
				return role;
			}
			
			return role;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}
	
	

}
