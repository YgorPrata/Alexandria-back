package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.ArteDao;
import com.restapp.model.entities.Arquitetura;
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
		String sql =("SELECT arq.id_arq, arq.titulo FROM arquitetura AS arq INNER JOIN arte"
				+ " ON arq.id_arq = arte.id_arq WHERE arq.titulo=?");
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, arqnome);
			ResultSet rsarq = ps.executeQuery();

			if (rsarq.next()) {
				rsarq.getInt("arq.id_arq");
				rsarq.getString("arq.titulo");
				System.out.println("A arquitetura " + rsarq.getString("arq.titulo") + 
						" existe na base, e o id é: "+ rsarq.getInt("arq.id_arq") + ""
								+ " . Adicionando a nova obra na base de dados");

				try {
					conn = DB.getConnection();
					ps = conn.prepareStatement("INSERT INTO arte (titulo, autor, descricao, categoria, tipo "
							+ " tecnica, ano, id_arq) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? "
							+ rsarq.getInt("arq.id_arq") + ")", st.RETURN_GENERATED_KEYS);

					ps.setString(1, arte.getTitulo());
					ps.setString(2, arte.getAutor());
					ps.setString(3, arte.getDescricao());
					ps.setString(4, arte.getCategoria());					
					ps.setString(5, arte.getTipo());					
					ps.setString(6, arte.getTecnica());
					ps.setInt(7, arte.getAno());
					
					ps.executeUpdate();

					rs = ps.getGeneratedKeys();

					int insert = ps.executeUpdate();

					if (insert > 0) {
						rs = ps.getGeneratedKeys();

						if (rs.next()) {
							int id = rs.getInt(1);
							arte.setId_arte(id);
						}
					} else {
						throw new DbException("Erro. A conclusão do cadastro não foi efetuada.");
					}

				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				} finally {
					DB.closeResultSet(rsarq);
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
	public List<Arte> findAll() {
		List<Arte> list = new ArrayList<Arte>();
		String sql = "SELECT * FROM arte";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(instantiateArte(rs));
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
	public List<Arte> findByName(String autor) {

		List<Arte> list = new ArrayList<Arte>();
		String sql = "SELECT * FROM arte WHERE autor = ?";

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, autor);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(instantiateArte(rs));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return list;
	}

	private Arte instantiateArte(ResultSet rs) throws SQLException {
		Arte arte = new Arte();
		Arquitetura arq = new Arquitetura();

		arte.setId_arte(rs.getInt("id_arte"));
		arte.setAutor(rs.getString("autor"));
		arte.setDescricao(rs.getString("descricao"));
		arte.setCategoria(rs.getString("categoria"));
		arte.setTipo(rs.getString("tipo"));
		arte.setTecnica(rs.getString("tecnica"));		
		arte.setAno(rs.getInt("ano"));		
		arq.setId_arq(rs.getInt("id_arq"));		
		return arte;
	}
}
