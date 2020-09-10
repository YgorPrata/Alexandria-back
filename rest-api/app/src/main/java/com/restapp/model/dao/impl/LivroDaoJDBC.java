package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.LivroDao;
import com.restapp.model.entities.Livro;

public class LivroDaoJDBC extends DB implements LivroDao {

	private Connection conn;

	public LivroDaoJDBC() {

	}

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Livro insert(Livro livro) {
		return livro;

	}

	@Override
	public Livro update(Livro livro) {
		return null;
	}

	@Override
	public Livro deleteById(Integer id) {
		return null;
	}

	@Override
	public Livro findById(Integer id) {
		return null;
		
	}

	@Override
	public List<Livro> findAll() {
		return null;
		
	}

	@Override
	public List<Livro> findByName(String autor) {
		return null;
		
	}

	private Livro instantiateLivro(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
		livro.setId(rs.getInt("id"));
		livro.setAutor(rs.getString("autor"));
		livro.setTitulo(rs.getString("titulo"));
		livro.setDescricao(rs.getString("descricao"));
		livro.setAno(rs.getInt("ano"));
		livro.setCategoria(rs.getString("categoria"));
		livro.setBiografia(rs.getString("biografia"));
		livro.setEditora(rs.getString("editora"));
		livro.setTipo(rs.getString("tipo"));
		return livro;
	}
}
