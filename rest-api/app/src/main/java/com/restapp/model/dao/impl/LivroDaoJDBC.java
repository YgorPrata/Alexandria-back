package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.LivroDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Livro;

public class LivroDaoJDBC extends DB implements LivroDao {

	private Connection conn;

	public LivroDaoJDBC() {

	}

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Livro insert(Livro livro, String arqnome) {
		String sql = ("SELECT arquitetura.id_arq " + "FROM arquitetura " + "INNER JOIN livro "
				+ "WHERE arquitetura.nome=? " + "AND arquitetura.id_arq = livro.id_arq;");
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, arqnome);
			ResultSet rsarqid = ps.executeQuery();

			if (rsarqid.next()) {
				rsarqid.getInt("arquitetura.id_arq");
				System.out.println("Obra existente, id: " + rsarqid.getInt("arquitetura.id_arq")
						+ ". Adicionando a nova obra na base de dados");

				try {
					conn = DB.getConnection();
					ps = conn.prepareStatement("INSERT INTO livro (categoria, tipo, autor, "
							+ "editora, edicao, biografia, descricao, " + "titulo, ano, id_arq) "
							+ "VALUES(?, ?, ?, ?, ?, ?, ?, ? ,? , " + rsarqid.getInt("arquitetura.id_arq") + ")",
							st.RETURN_GENERATED_KEYS);
					ps.setString(1, livro.getCategoria());
					ps.setString(2, livro.getTipo());
					ps.setString(3, livro.getAutor());
					ps.setString(4, livro.getEditora());
					ps.setInt(5, livro.getEdicao());
					ps.setString(6, livro.getBiografia());
					ps.setString(7, livro.getDescricao());
					ps.setString(8, livro.getTitulo());
					ps.setInt(9, livro.getAno());

					int insert = ps.executeUpdate();

					if (insert > 0) {
						rs = ps.getGeneratedKeys();
						if (rs.next()) {
							int id = rs.getInt(1);
							livro.setId_livro(id);
						}
					} else {
						throw new DbException("Erro incomum. A conclusão do cadastro não foi efetuada.");
					}

				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				} finally {
					DB.closeResultSet(rs);
					DB.closeStatement(ps);
				}
			}

			else if (rsarqid.next() == false) {
				System.out.println("Esta obra não está em nenhuma arquitetura.\nObra não cadastrada.");
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
