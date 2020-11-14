package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.ArquiteturaDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Img;

public class ArquiteturaDaoJDBC extends DB implements ArquiteturaDao {

	private Connection conn;
	
	public ArquiteturaDaoJDBC() {
		
	}
	
	public ArquiteturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Arquitetura> getAll() {
		String sql = "SELECT * FROM produto AS p INNER JOIN arquitetura AS a ON p.id_prod = a.id_prod "
				+ "INNER JOIN img_path AS i ON p.id_prod = i.id_prod";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			List<Arquitetura> list = new ArrayList<>();	
			Map<Integer, Arquitetura> map = new HashMap<Integer, Arquitetura>();
 			List<Img> listimg = new ArrayList<>();
  			int cont = 0;
  			int chave = 0;
 			
			while(rs.next()) {
				cont++;				
				Arquitetura arq = map.get(rs.getInt("a.id_prod"));
				
				if(chave == rs.getInt("i.id_prod") || cont == 1) {
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
				
				if(chave != rs.getInt("i.id_prod") && cont != 1) {
					listimg = new ArrayList<>();
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
								
				if(arq == null) {
					arq = instanciaTudo(rs, listimg);					
					list.add(arq);
					map.put(rs.getInt("a.id_prod"), arq);
					for (Map.Entry<Integer, Arquitetura> entry : map.entrySet()) {
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

	public Arquitetura getById(Integer id_arq) {
		String sql = "SELECT * FROM produto AS p INNER JOIN arquitetura AS a ON p.id_prod = a.id_prod INNER JOIN "
				+ "img_path AS i ON p.id_prod = i.id_prod WHERE p.id_prod = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_arq);
			rs = ps.executeQuery();
			
			Arquitetura arq = new Arquitetura();	
			Map<Integer, Arquitetura> map = new HashMap<Integer, Arquitetura>();
 			List<Img> listimg = new ArrayList<>();
  			int cont = 0;
  			int chave = 0;
 			
  			
			while(rs.next()) {
				cont++;				
				arq = map.get(rs.getInt("a.id_prod"));
				
				if(chave == rs.getInt("i.id_prod") || cont == 1) {
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
				
				if(chave != rs.getInt("i.id_prod") && cont != 1) {
					listimg = new ArrayList<>();
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
								
				if(arq == null) {
					arq = instanciaTudo(rs, listimg);										
					map.put(rs.getInt("a.id_prod"), arq);
					for (Map.Entry<Integer, Arquitetura> entry : map.entrySet()) {
					    chave = entry.getKey();					    
					}
				}				
			}		
			return arq;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);			
		}
	}
	
	
	@Override
	public List<Arquitetura> getArqTipo(String titulo, String autor, String localidade, Integer limit) {
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, a.id_arq, a.id_prod, i.id_img,"
				+ " i.path_img, i.desc_img, i.id_prod FROM produto AS p INNER JOIN "
				+ "arquitetura AS a ON p.id_prod = a.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod "
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') "
				+ "ORDER BY RAND() LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, titulo);
			ps.setString(2, autor);
			ps.setString(3, localidade);
			ps.setInt(4, limit);
			rs = ps.executeQuery();
			
			List<Arquitetura> list = new ArrayList<>();	
			Map<Integer, Arquitetura> map = new HashMap<Integer, Arquitetura>();
 			Img img;
 			
			while(rs.next()) {			
				Arquitetura arq = map.get(rs.getInt("a.id_prod"));

				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(arq == null) {
					arq = instanciaArqSimp(rs, img);					
					list.add(arq);
					map.put(rs.getInt("a.id_prod"), arq);					
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
	public List<Arquitetura> getArqCategoria(String query, Integer limit){
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, a.id_arq, a.id_prod, i.id_img, " 
				+ "i.path_img, i.desc_img, i.id_prod FROM produto AS p INNER JOIN " 
				+ "arquitetura AS a ON p.id_prod = a.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod " 
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') " 
				+ "OR p.descricao LIKE CONCAT( '%',?,'%') OR a.curador LIKE CONCAT( '%',?,'%') "
				+ "OR a.area LIKE CONCAT( '%',?,'%') ORDER BY RAND() LIMIT ?";
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
			
			List<Arquitetura> list = new ArrayList<>();	
			Map<Integer, Arquitetura> map = new HashMap<Integer, Arquitetura>();
 			Img img;
 			
			while(rs.next()) {			
				Arquitetura arq = map.get(rs.getInt("a.id_prod"));
				
				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(arq == null) {
					arq = instanciaArqSimp(rs, img);					
					list.add(arq);
					map.put(rs.getInt("a.id_prod"), arq);					
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
	
	
	

	private Arquitetura instanciaArqSimp(ResultSet rs, Img img) throws SQLException {
		Arquitetura arq = new Arquitetura();
		arq.setId_prod(rs.getInt("p.id_prod"));
		arq.setTitulo(rs.getString("p.titulo"));
		arq.setAutor(rs.getString("p.autor"));
		arq.setDescricao(rs.getString("p.descricao"));
		arq.setLocalidade(rs.getString("p.localidade"));
		arq.setCategoria(rs.getString("p.categoria"));
		arq.setId_arq(rs.getInt("a.id_arq"));
		arq.setImg(img);
		return arq;
	}
	
	private Arquitetura instanciaTudo(ResultSet rs, List<Img> img) throws SQLException{
		Arquitetura arq = new Arquitetura();
		arq.setId_prod(rs.getInt("p.id_prod"));
		arq.setTitulo(rs.getString("p.titulo"));
		arq.setAutor(rs.getString("p.autor"));
		arq.setDescricao(rs.getString("p.descricao"));
		arq.setCategoria(rs.getString("p.categoria"));
		arq.setTipo(rs.getString("p.tipo"));
		arq.setLocalidade(rs.getString("p.localidade"));
		arq.setAno(rs.getInt("p.ano"));
		arq.setId_arq(rs.getInt("a.id_arq"));
		arq.setCurador(rs.getString("a.curador"));
		arq.setArea(rs.getDouble("a.area"));
		arq.setListImg(img);
		return arq;
	}

}