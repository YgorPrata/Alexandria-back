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
	public boolean insert(Arquitetura arq, List<String> listarq, List<String> listdesc, String arqtxt) {
		
		//System.out.println("caminho: "+arqpath);
		boolean sucesso = false;
		int id = 0;
		String caminho;
		String descimg;
	
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("INSERT INTO arquitetura (nome, categoria, tipo,"
					+ " autor, material, ano, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)",
					st.RETURN_GENERATED_KEYS);
			ps.setString(1, arq.getNome());
			ps.setString(2, arq.getCategoria());
			ps.setString(3, arq.getTipo());
			ps.setString(4, arq.getAutor());
			ps.setString(5, arq.getMaterial());
			//ps.setDate(6, new java.sql.Date(arq.getData().getTime()));
			ps.setInt(6, arq.getAno());
			ps.setString(7, arq.getDescricao());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
			sucesso = true;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
		try {
			for(int i = 0; i <= listarq.size() - 1; i++) {
				caminho = listarq.get(i);
				descimg = listdesc.get(i);
					if(caminho.endsWith(".jpg") || caminho.endsWith(".png")) {
						String sql = "INSERT INTO img_path (path_img, descricao, id_arq) VALUES ('" + caminho + "', '" + descimg + "', "+ id +")";
						conn = DB.getConnection();
						ps = conn.prepareStatement(sql);						
						ps.executeUpdate();
						
						System.out.println(caminho);
						System.out.println(descimg);
					
					}
					else {
						System.out.println("Extensão não aceita.");
					}
			}
			
			if(arqtxt.endsWith(".pdf") || arqtxt.endsWith(".txt") || arqtxt.endsWith(".docx")) {
				String sql = "INSERT INTO txt_path (path_txt, id_arq) VALUES ('" + arqtxt + "', "+ id +")";
				conn = DB.getConnection();
				ps = conn.prepareStatement(sql);
				
				ps.executeUpdate();
				
				System.out.println(arqtxt);
			}
			else {
				System.out.println("Extensão não aceita.");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
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
		// arq.setData(rs.getDate("data"));
		arq.setAno(rs.getInt("ano"));
		arq.setCategoria(rs.getString("categoria"));
		arq.setMaterial(rs.getString("material"));
		arq.setTipo(rs.getString("tipo"));
		return arq;
	}
}
