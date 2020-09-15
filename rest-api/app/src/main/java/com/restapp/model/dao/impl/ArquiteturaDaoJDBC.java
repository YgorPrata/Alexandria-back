package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.ArquiteturaDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Artigos;

public class ArquiteturaDaoJDBC extends DB implements ArquiteturaDao {

	private Connection conn;

	public ArquiteturaDaoJDBC() {

	}

	public ArquiteturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Arquitetura insert(Arquitetura arq) {
		return arq;

	}

	@Override
	public Arquitetura update(Arquitetura arq) {
		return null;
	}

	@Override
	public Arquitetura deleteById(Integer id) {
		return null;
	}

	@Override
	public Arquitetura findById(Integer id) {
		return null;
		
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
		return null;
		
	}

	private Arquitetura instantiateArquitetura(ResultSet rs) throws SQLException {
		Arquitetura arq = new Arquitetura();
		arq.setId_arq(rs.getInt("id_arq"));
		arq.setAutor(rs.getString("autor"));
		arq.setNome(rs.getString("nome"));
		arq.setDescricao(rs.getString("descricao"));
		arq.setData(rs.getDate("data"));
		arq.setCategoria(rs.getString("categoria"));
		arq.setMaterial(rs.getString("material"));
		arq.setTipo(rs.getString("tipo"));
		return arq;
	}
}
