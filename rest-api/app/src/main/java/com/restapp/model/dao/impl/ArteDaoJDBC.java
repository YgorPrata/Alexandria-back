package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.ArteDao;
import com.restapp.model.entities.Arte;

public class ArteDaoJDBC extends DB implements ArteDao {

	private Connection conn;

	public ArteDaoJDBC() {

	}

	public ArteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Arte insert(Arte arte) {
		return arte;

	}

	@Override
	public Arte update(Arte arte) {
		return null;
	}

	@Override
	public Arte deleteById(Integer id) {
		return null;
	}

	@Override
	public Arte findById(Integer id) {
		return null;
		
	}

	@Override
	public List<Arte> findAll() {
		return null;
		
	}

	@Override
	public List<Arte> findByName(String autor) {
		return null;
		
	}

	private Arte instantiateArte(ResultSet rs) throws SQLException {
		Arte arte = new Arte();
		arte.setId_arte(rs.getInt("id"));
		arte.setAutor(rs.getString("autor"));
		arte.setTitulo(rs.getString("titulo"));
		arte.setDescricao(rs.getString("descricao"));
		arte.setAno(rs.getDate("ano"));
		arte.setCategoria(rs.getString("categoria"));
		arte.setMaterial(rs.getString("material"));
		arte.setTipo(rs.getString("tipo"));
		return arte;
	}
}
