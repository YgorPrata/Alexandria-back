package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.ArquiteturaDao;
import com.restapp.model.entities.Arquitetura;

public class ArquiteturaDaoJDBC extends DB implements ArquiteturaDao {

	private Connection conn;

	public ArquiteturaDaoJDBC() {

	}

	public ArquiteturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insert(Arquitetura arq, String arqpath) {
		
		boolean sucesso = false;
		String sql = "INSERT INTO arquitetura (nome, categoria, tipo, autor, material, ano, descricao, arq_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, "+arqpath+")";
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, arq.getNome());
			ps.setString(2, arq.getCategoria());
			ps.setString(3, arq.getTipo());
			ps.setString(4, arq.getAutor());
			ps.setString(5, arq.getMaterial());
			//ps.setDate(6, new java.sql.Date(arq.getData().getTime()));
			ps.setInt(6, arq.getAno());
			ps.setString(7, arq.getDescricao());

			ps.executeUpdate();
			sucesso = true;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return sucesso;
	}

	@Override
	public List<Arquitetura> findAll() {
		
		List<Arquitetura> list = new ArrayList<Arquitetura>();
		String sql = "SELECT * FROM arquitetura";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(instantiateArquitetura(rs));
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
	public List<Arquitetura> findByName(String autor) {

		List<Arquitetura> list = new ArrayList<Arquitetura>();
		String sql = "SELECT * FROM arquitetura WHERE autor = ?";

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, autor);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(instantiateArquitetura(rs));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return list;
	}

	private Arquitetura instantiateArquitetura(ResultSet rs) throws SQLException {
		Arquitetura arq = new Arquitetura();
		arq.setId_arq(rs.getInt("id_arq"));
		arq.setAutor(rs.getString("autor"));
		arq.setNome(rs.getString("nome"));
		arq.setDescricao(rs.getString("descricao"));
		//arq.setData(rs.getDate("data"));
		arq.setAno(rs.getInt("ano"));
		arq.setCategoria(rs.getString("categoria"));
		arq.setMaterial(rs.getString("material"));
		arq.setTipo(rs.getString("tipo"));
		return arq;
	}
}
