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
import com.restapp.model.entities.Arte;

public class ArquiteturaDaoJDBC extends DB implements ArquiteturaDao {

	private Connection conn;

	public ArquiteturaDaoJDBC() {

	}

	public ArquiteturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Arquitetura insert(Arquitetura arq) {

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("INSERT INTO arquitetura (nome, categoria, tipo, autor, "
					+ "material, data, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)", st.RETURN_GENERATED_KEYS);
			ps.setString(1, arq.getNome());
			ps.setString(2, arq.getCategoria());
			ps.setString(3, arq.getTipo());
			ps.setString(4, arq.getAutor());
			ps.setString(5, arq.getMaterial());
			ps.setDate(6, new java.sql.Date(arq.getData().getTime()));
			ps.setString(7, arq.getDescricao());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			int insert = ps.executeUpdate();

			if (insert > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					arq.setId_arq(id);
				}
			}

			else {
				throw new DbException("Erro incomum. A conclusão do cadastro não foi efetuada.");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return arq;
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
		arq.setData(rs.getDate("data"));
		arq.setCategoria(rs.getString("categoria"));
		arq.setMaterial(rs.getString("material"));
		arq.setTipo(rs.getString("tipo"));
		return arq;
	}
}
