package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.LivroDao;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.User;


public class LivroDaoJDBC extends DB implements LivroDao {

	private Connection conn;

	public LivroDaoJDBC() {

	}

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Livro> getAll(){
		String sql = "SELECT * FROM produto AS p INNER JOIN livro AS l ON p.id_prod = l.id_prod "
				+ "INNER JOIN img_path AS i ON p.id_prod = i.id_prod INNER JOIN usuario AS u ON p.id_user = u.id_user";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			List<Livro> list = new ArrayList<>();	
			Map<Integer, Livro> map = new HashMap<Integer, Livro>();
 			Img img;
 			User user;
 			
			while(rs.next()) {			
				Livro livro = map.get(rs.getInt("l.id_prod"));
				
				user = new User(rs.getString("u.nome"));

				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(livro == null) {
					livro = instanciaLivSimp(rs, img, user);					
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
	public Livro getById(Integer id_livro){
		String sql = "SELECT * FROM produto AS p INNER JOIN livro AS l ON p.id_prod = l.id_prod INNER JOIN "
				+ "img_path AS i ON p.id_prod = i.id_prod INNER JOIN usuario AS u ON p.id_user = u.id_user "
				+ "WHERE p.id_prod = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_livro);
			rs = ps.executeQuery();
			
			Livro livro = new Livro();
			Map<Integer, Livro> map = new HashMap<Integer, Livro>();
 			List<Img> listimg = new ArrayList<>();
 			User user;
  			int cont = 0;
  			int chave = 0;
 			
			while(rs.next()) {
				cont++;				
				livro = map.get(rs.getInt("l.id_prod"));
				
				user = new User(rs.getString("u.nome"));
				
				if(chave == rs.getInt("i.id_prod") || cont == 1) {
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
				
				if(chave != rs.getInt("i.id_prod") && cont != 1) {
					listimg = new ArrayList<>();
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
								
				if(livro == null) {
					livro = instanciaTudo(rs, listimg, user);										
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
				+ " i.path_img, i.desc_img, u.nome FROM produto AS p INNER JOIN "
				+ "livro AS l ON p.id_prod = l.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod "
				+ "INNER JOIN usuario AS u ON p.id_user = u.id_user WHERE p.titulo LIKE CONCAT( '%',?,'%') "
				+ "OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') "
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
 			User user;
 			
			while(rs.next()) {			
				Livro livro = map.get(rs.getInt("l.id_prod"));
				
				user = new User(rs.getString("u.nome"));

				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(livro == null) {
					livro = instanciaLivSimp(rs, img, user);					
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
		System.out.println("VEIO AQUI"+query);
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, l.id_livro, l.id_prod, i.id_img, " 
				+ "i.path_img, i.desc_img, u.nome FROM produto AS p INNER JOIN " 
				+ "livro AS l ON p.id_prod = l.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod " 
				+ "INNER JOIN usuario AS u ON p.id_user = u.id_user WHERE p.titulo LIKE CONCAT( '%',?,'%') "
				+ "OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') " 
				+ "OR p.descricao LIKE CONCAT( '%',?,'%') OR p.tipo LIKE CONCAT( '%',?,'%') OR l.editora LIKE CONCAT( '%',?,'%') "
				+ "OR l.biografia LIKE CONCAT( '%',?,'%') ORDER BY RAND() LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, query);
			ps.setString(2, query);
			ps.setString(3, query);
			ps.setString(4, query);
			ps.setString(5, query);
			ps.setString(6, query);
			ps.setString(7, query);
			ps.setInt(8, limit);
			rs = ps.executeQuery();
			
			List<Livro> list = new ArrayList<>();	
			Map<Integer, Livro> map = new HashMap<Integer, Livro>();
 			Img img;
 			User user;
 			
			while(rs.next()) {			
				Livro livro = map.get(rs.getInt("l.id_prod"));
				
				user = new User(rs.getString("u.nome"));
				
				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(livro == null) {
					livro = instanciaLivSimp(rs, img, user);					
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
	
	private Livro instanciaLivSimp(ResultSet rs, Img img, User user) throws SQLException {
		Livro livro = new Livro();
		livro.setId_prod(rs.getInt("p.id_prod"));
		livro.setTitulo(rs.getString("p.titulo"));
		livro.setAutor(rs.getString("p.autor"));
		livro.setDescricao(rs.getString("p.descricao"));
		livro.setLocalidade(rs.getString("p.localidade"));
		livro.setCategoria(rs.getString("p.categoria"));
		livro.setId_livro(rs.getInt("l.id_livro"));
		livro.setUser(user);
		livro.setImg(img);
		return livro;
	}

	private Livro instanciaTudo(ResultSet rs, List<Img> listimg, User user) throws SQLException {
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
		livro.setBiografia(rs.getString("l.biografia"));
		livro.setUser(user);
		livro.setListImg(listimg);
		return livro;
	}


}
