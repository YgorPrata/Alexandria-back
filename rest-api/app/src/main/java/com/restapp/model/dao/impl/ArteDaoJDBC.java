package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public Arte insert(Arte arte, String arqnome) {

		String sql = ("SELECT arquitetura.id_arq, arquitetura.nome " + "FROM arquitetura " + "INNER JOIN arte "
				+ "WHERE arquitetura.nome=? " + "AND arquitetura.id_arq = arte.id_arq;");
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, arqnome);
			ResultSet rsarq = ps.executeQuery();

			if (rsarq.next()) {
				rsarq.getInt("arquitetura.id_arq");
				rsarq.getString("arquitetura.nome");
				System.out
						.println("A arquitetura " + rsarq.getString("arquitetura.nome") + " existe na base, e o id é: "
								+ rsarq.getInt("arquitetura.id_arq") + " . Adicionando a nova obra na base de dados");

				try {
					conn = DB.getConnection();
					ps = conn.prepareStatement(
							"INSERT INTO arte (categoria, titulo, autor, tipo, material, "
							+ " tecnica, data, descricao, id_arq) "
							+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, "+ rsarq.getInt("arquitetura.id_arq") + ")",
							st.RETURN_GENERATED_KEYS);

					ps.setString(1, arte.getCategoria());
					ps.setString(2, arte.getTitulo());
					ps.setString(3, arte.getAutor());
					ps.setString(4, arte.getTipo());
					ps.setString(5, arte.getMaterial());
					ps.setString(6, arte.getTecnica());
					ps.setDate(7, new java.sql.Date(arte.getData().getTime()));
					ps.setString(8, arte.getDescricao());

					ps.executeUpdate();

					rs = ps.getGeneratedKeys();

				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				} finally {
					DB.closeResultSet(rs);
					DB.closeStatement(ps);
				}
			}

			else if (rsarq.next() == false) {
				System.out.println("Esta obra não está em nenhuma arquitetura.\nObra não cadastrada.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
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
		arte.setId_arte(rs.getInt("id_arte"));
		arte.setAutor(rs.getString("autor"));
		arte.setTitulo(rs.getString("titulo"));
		arte.setDescricao(rs.getString("descricao"));
		arte.setData(rs.getDate("data"));
		arte.setCategoria(rs.getString("categoria"));
		arte.setMaterial(rs.getString("material"));
		arte.setTipo(rs.getString("tipo"));
		return arte;
	}
}
