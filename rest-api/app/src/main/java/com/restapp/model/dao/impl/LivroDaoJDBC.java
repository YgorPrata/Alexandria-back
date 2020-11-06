package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.LivroDao;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Txt;

public class LivroDaoJDBC extends DB implements LivroDao {

	private Connection conn;

	public LivroDaoJDBC() {

	}

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insert(Livro liv, List<Img> list, Txt txt) {
		boolean sucesso = false;
		int id = 0;
	
		try {
			ps = conn.prepareStatement("INSERT INTO produto (titulo, autor, descricao, tipo, "
					+ " categoria, localidade, ano) VALUES (?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, liv.getTitulo());
			ps.setString(2, liv.getAutor());
			ps.setString(3, liv.getDescricao());
			ps.setString(4, liv.getTipo());
			ps.setString(5, liv.getCategoria());
			ps.setString(6, liv.getLocalidade());
			ps.setInt(7, liv.getAno());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
						
			String sql = "INSERT INTO livro (editora, edicao, biografia, id_prod) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, liv.getEditora());
			ps.setInt(2, liv.getEdicao());
			ps.setString(3, liv.getBiografia());
			ps.setInt(4, id);
			ps.executeUpdate();			
			sucesso = true;
			
	
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}		
		
		try {
			for(Img img : list) {
				String sql = "INSERT INTO img_path (path_img, desc_img, id_prod) VALUES (?, ?, ?)";
				conn = DB.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, img.getPath_img());
				ps.setString(2, img.getDesc_img());
				ps.setInt(3, id);
				ps.executeUpdate();
				sucesso = true;
				
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		try {			
			String sql = "INSERT INTO txt_path (path_txt, id_prod) VALUES (?, ?)";
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, txt.getPath_txt());
			ps.setInt(2, id);
			ps.executeUpdate();
			sucesso = true;		
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
	public List<Livro> getAll(){
		String sql = "SELECT * FROM produto AS p INNER JOIN livro AS l ON p.id_prod = l.id_prod "
				+ "INNER JOIN img_path AS i ON p.id_prod = i.id_prod";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			List<Livro> list = new ArrayList<>();	
			Map<Integer, Livro> map = new HashMap<Integer, Livro>();
 			List<Img> listimg = new ArrayList<>();
  			int cont = 0;
  			int chave = 0;
 			
			while(rs.next()) {
				cont++;				
				Livro livro = map.get(rs.getInt("l.id_prod"));
				
				if(chave == rs.getInt("i.id_prod") || cont == 1) {
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
				
				if(chave != rs.getInt("i.id_prod") && cont != 1) {
					listimg = new ArrayList<>();
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
								
				if(livro == null) {
					livro = instanciaTudo(rs, listimg);					
					list.add(livro);
					map.put(rs.getInt("l.id_prod"), livro);
					for (Map.Entry<Integer, Livro> entry : map.entrySet()) {
					    chave = entry.getKey();					    
					}
				}				
			}
		
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}	
	
	@Override
	public Livro getById(Integer id_livro){
		String sql = "SELECT * FROM produto AS p INNER JOIN livro AS a ON p.id_prod = l.id_prod INNER JOIN "
				+ "img_path AS i ON p.id_prod = i.id_prod WHERE p.id_prod = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_livro);
			rs = ps.executeQuery();
			
			Livro livro = new Livro();	
			Map<Integer, Livro> map = new HashMap<Integer, Livro>();
 			List<Img> listimg = new ArrayList<>();
  			int cont = 0;
  			int chave = 0;
 			
			while(rs.next()) {
				cont++;				
				livro = map.get(rs.getInt("l.id_prod"));
				
				if(chave == rs.getInt("i.id_prod") || cont == 1) {
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
				
				if(chave != rs.getInt("i.id_prod") && cont != 1) {
					listimg = new ArrayList<>();
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
								
				if(livro == null) {
					livro = instanciaTudo(rs, listimg);										
					map.put(rs.getInt("l.id_prod"), livro);
					for (Map.Entry<Integer, Livro> entry : map.entrySet()) {
					    chave = entry.getKey();					    
					}
				}				
			}
		
			return livro;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);			
		}
	}
	
	@Override
	public List<Livro> getLivroTipo(String titulo, String autor, String localidade, Integer limit) {
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, l.id_livro, l.id_prod, i.id_img,"
				+ " i.path_img, i.desc_img, i.id_prod FROM produto AS p INNER JOIN "
				+ "livro AS l ON p.id_prod = l.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod "
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') "
				+ "ORDER BY RAND() LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, titulo);
			ps.setString(2, autor);
			ps.setString(3, localidade);
			ps.setInt(4, limit);
			rs = ps.executeQuery();
			
			List<Livro> list = new ArrayList<>();	
			Map<Integer, Livro> map = new HashMap<Integer, Livro>();
 			Img img;
 			
			while(rs.next()) {			
				Livro livro = map.get(rs.getInt("l.id_prod"));

				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(livro == null) {
					livro = instanciaLivSimp(rs, img);					
					list.add(livro);
					map.put(rs.getInt("l.id_prod"), livro);					
				}				
			}
		
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}		
	}
	
	@Override
	public List<Livro> getLivroCategoria(String query, Integer limit){
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, l.id_livro, l.id_prod, i.id_img, " 
				+ "i.path_img, i.desc_img, i.id_prod FROM produto AS p INNER JOIN " 
				+ "livro AS l ON p.id_prod = l.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod " 
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') " 
				+ "OR p.descricao LIKE CONCAT( '%',?,'%') OR l.editora LIKE CONCAT( '%',?,'%') "
				+ "OR a.bibliografia LIKE CONCAT( '%',?,'%') ORDER BY RAND() LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, query);
			ps.setString(2, query);
			ps.setString(3, query);
			ps.setString(4, query);
			ps.setString(5, query);
			ps.setString(6, query);
			ps.setInt(7, limit);
			rs = ps.executeQuery();
			
			List<Livro> list = new ArrayList<>();	
			Map<Integer, Livro> map = new HashMap<Integer, Livro>();
 			Img img;
 			
			while(rs.next()) {			
				Livro livro = map.get(rs.getInt("a.id_prod"));
				
				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(livro == null) {
					livro = instanciaLivSimp(rs, img);					
					list.add(livro);
					map.put(rs.getInt("a.id_prod"), livro);					
				}				
			}
		
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}
	
	private Livro instanciaLivSimp(ResultSet rs, Img img) throws SQLException {
		Livro livro = new Livro();
		livro.setId_prod(rs.getInt("p.id_prod"));
		livro.setTitulo(rs.getString("p.titulo"));
		livro.setAutor(rs.getString("p.autor"));
		livro.setDescricao(rs.getString("p.descricao"));
		livro.setLocalidade(rs.getString("p.localidade"));
		livro.setCategoria(rs.getString("p.categoria"));
		livro.setImg(img);
		return livro;
	}

	private Livro instanciaTudo(ResultSet rs, List<Img> listimg) throws SQLException {
		Livro livro = new Livro();
		livro.setId_prod(rs.getInt("p.id_prod"));
		livro.setTitulo(rs.getString("p.titulo"));
		livro.setAutor(rs.getString("p.autor"));
		livro.setDescricao(rs.getString("p.descricao"));
		livro.setCategoria(rs.getString("p.categoria"));
		livro.setTipo(rs.getString("p.tipo"));
		livro.setLocalidade(rs.getString("p.localidade"));
		livro.setAno(rs.getInt("p.ano"));	
		livro.setId_livro(rs.getInt("l.id_livro"));		
		livro.setEditora(rs.getString("l.editora"));
		livro.setEdicao(rs.getInt("l.edicao"));
		livro.setBiografia(rs.getString("biografia"));
		livro.setListImg(listimg);
		return livro;
	}


}
