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
	public boolean insert(Arquitetura arq, List<String> listimg, List<String> listdesc, String arqtxt) {
		
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
		
		try {
			for(int i = 0; i <= listimg.size() - 1; i++) {
				caminho = listimg.get(i);
				descimg = listdesc.get(i);
					if(caminho.endsWith(".jpg") || caminho.endsWith(".png")) {
						String sql = "INSERT INTO img_path (path_img, desc_img, id_arq) VALUES ('" + caminho + "', '" + descimg + "', "+ id +")";
						conn = DB.getConnection();
						ps = conn.prepareStatement(sql);						
						ps.executeUpdate();
				
					}
					else {
						System.out.println("Extensão não aceita.");
					}
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		try {			
			if(arqtxt.endsWith(".pdf") || arqtxt.endsWith(".txt") || arqtxt.endsWith(".docx")) {
				String sql = "INSERT INTO txt_path (path_txt, id_arq) VALUES ('" + arqtxt + "', "+ id +")";
				conn = DB.getConnection();
				ps = conn.prepareStatement(sql);
				
				ps.executeUpdate();
						
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
	public List<Arquitetura> findByAutor(String autor) {

		List<Arquitetura> list = new ArrayList<Arquitetura>();
		String sql = "SELECT * FROM arquitetura WHERE autor = ?";

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, autor);

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
	

	public List<Arquitetura> GetImageByName(String nome) {
		String sql = "SELECT * FROM arquitetura AS arq INNER JOIN img_path AS img ON arq.id_arq = img.id_arq WHERE arq.nome = ?";
		 
		List<Arquitetura> list = new ArrayList<Arquitetura>();
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(instantiateArquitetura(rs));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
		return list;
	}
	
	private Arquitetura instantiateImagens(ResultSet rs) throws SQLException{
		Arquitetura arq = new Arquitetura();
		arq.setId_img(rs.getInt("id_img"));
		arq.setImg_path(rs.getString("path_img"));
		arq.setImg_desc(rs.getString("desc_img"));
		return arq;
	}

	private Arquitetura instantiateArquitetura(ResultSet rs) throws SQLException {
		Arquitetura arq = new Arquitetura();
		arq.setId_arq(rs.getInt("id_arq"));
		arq.setNome(rs.getString("nome"));
		arq.setCategoria(rs.getString("categoria"));
		arq.setTipo(rs.getString("tipo"));
		arq.setAutor(rs.getString("autor"));
		arq.setMaterial(rs.getString("material"));
		arq.setAno(rs.getInt("ano"));
		arq.setDescricao(rs.getString("descricao"));
		arq.setId_img(rs.getInt("id_img"));
		arq.setImg_path(rs.getString("path_img"));
		arq.setImg_desc(rs.getString("desc_img"));
		return arq;
	}
	
}