package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.ArtigosDao;
import com.restapp.model.entities.Artigos;

public class ArtigosDaoJDBC extends DB implements ArtigosDao {

	private Connection conn;

	public ArtigosDaoJDBC() {

	}

	public ArtigosDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Artigos insert(Artigos art) {

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("INSERT INTO artigos (titulo, autor, assunto) VALUES (?, ?, ?)",
					st.RETURN_GENERATED_KEYS);
			ps.setString(1, art.getTitulo());
			ps.setString(2, art.getAutor());
			ps.setString(3, art.getAssunto());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			while (rs.next()) {
				int id = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return art;
	}

	@Override
	public Artigos update(Artigos art) {
		return null;
	}

	@Override
	public Artigos deleteById(Integer id) {
		return null;
	}

	@Override
	public Artigos findById(Integer id) {
		String sql = "SELECT * FROM artigos WHERE id = ?";
		Artigos artigos = new Artigos();
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				artigos = instantiateArtigos(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return artigos;
	}

	@Override
	public List<Artigos> findAll() {
		List<Artigos> list = new ArrayList<Artigos>();
		String sql = "SELECT * FROM artigos";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(instantiateArtigos(rs));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return list;
	}

	@Override
	public List<Artigos> findByName(String autor) {
		List<Artigos> list = new ArrayList<Artigos>();
		Artigos artigos = new Artigos();
		String sql = "SELECT * FROM artigos WHERE autor = ?";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, autor);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(instantiateArtigos(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return list;
	}
		
	public List<Artigos> dynamicSearch(String autor){		
		List<Artigos> list = new ArrayList<Artigos>();
		Artigos artigos = new Artigos();
		String sql = "SELECT * FROM artigos WHERE autor LIKE '?%'";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, autor);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(instantiateArtigos(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return list;
	}

	private Artigos instantiateArtigos(ResultSet rs) throws SQLException {
		Artigos art = new Artigos();
		art.setId(rs.getInt("id"));
		art.setAutor(rs.getString("autor"));
		art.setTitulo(rs.getString("titulo"));
		art.setAssunto(rs.getString("assunto"));
		return art;
	}
}
