package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.LivroDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Artigos;
import com.restapp.model.entities.Livro;

public class LivroDaoJDBC extends DB implements LivroDao {

	private Connection conn;

	public LivroDaoJDBC() {

	}

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Livro insert(Livro livro, String tituloarq) {
		String selectsql = "SELECT id_arq from arquitetura WHERE titulo=?";
		Arquitetura arq1 = new Arquitetura();
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(selectsql);
			ps.setString(1, tituloarq);
			ResultSet rsarqid = ps.executeQuery();
			if (rsarqid.next()) {
				arq1.setId_arq(rsarqid.getInt("id_arq"));
			}

			String insertsql = "INSERT INTO livro (categoria, tipo, autor, "
					+ "editora, edicao, biografia, descricao, "
					+ "titulo, ano, id_arq) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ? ,? ,?) st.RETURN_GENERATED_KEYS";
			if (rsarqid != null) {
				try {
					ps = conn.prepareStatement("INSERT INTO livro (categoria, tipo, autor, "
							+ "editora, edicao, biografia, descricao, "
							+ "titulo, ano, id_arq) "
							+ "VALUES(?, ?, ?, ?, ?, ?, ?, ? ,? , id_arq)", st.RETURN_GENERATED_KEYS);
					ps.setString(1, livro.getCategoria());
					ps.setString(2, livro.getTipo());
					ps.setString(3, livro.getAutor());
					ps.setString(4, livro.getEditora());
					ps.setInt(5, livro.getEdicao());
					ps.setString(6, livro.getBiografia());
					ps.setString(7, livro.getDescricao());
					ps.setString(8, livro.getTitulo());
					ps.setInt(9, livro.getAno());
					ps.setInt(10, livro.getId_arq());

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
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return livro;

	}

	@Override
	public Livro update(Livro livro) {
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
		livro.setId_livro(rs.getInt("id_livro"));
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
