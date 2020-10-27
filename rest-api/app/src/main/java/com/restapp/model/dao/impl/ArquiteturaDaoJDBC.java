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
import com.restapp.model.dao.ArquiteturaDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Produto;
import com.restapp.model.entities.Txt;

public class ArquiteturaDaoJDBC extends DB implements ArquiteturaDao {

	private Connection conn;
	
	public ArquiteturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insert(Produto arq, List<Img> list, Txt txt) {
		boolean sucesso = false;
		int id = 0;
	
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("INSERT INTO produto (titulo, autor, descricao, tipo, "
					+ " categoria, localidade, ano) VALUES (?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, arq.getTitulo());
			ps.setString(2, arq.getAutor());
			ps.setString(3, arq.getDescricao());
			ps.setString(4, arq.getTipo());
			ps.setString(5, arq.getCategoria());
			ps.setString(6, arq.getLocalidade());
			ps.setInt(7, arq.getAno());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
			
			String sql = "INSERT INTO arquitetura (curador, area, id_prod) VALUES (?, ?, ?)";
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.setString(1, ((Arquitetura) arq).getCurador());
			ps2.setDouble(2, ((Arquitetura) arq).getArea());
			ps2.setInt(3, id);
			ps2.executeUpdate();
			
			sucesso = true;
			
	
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}		
		
		try {
			for(Img img : list) {
				String sql = "INSERT INTO img_path (path_img, desc_img, id_prod) VALUES (?, ?, "+ id +")";
				conn = DB.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, img.getPath_img());
				ps.setString(2, img.getDesc_img());
				ps.executeUpdate();
				sucesso = true;
				
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		try {			
			String sql = "INSERT INTO txt_path (path_txt, id_prod) VALUES (?, "+ id +")";
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, txt.getPath_txt());
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
	public List<Arquitetura> getAll() {
		String sql = "SELECT * FROM produto AS p INNER JOIN arquitetura AS a ON p.id_prod = a.id_arq "
				+ "INNER JOIN img_path AS i ON p.id_prod = i.id_prod";
		try {
			conn = DB.getConnection();
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

	public List<Arquitetura> getById(Integer id_arq) {
		String sql = "SELECT * FROM produto AS p INNER JOIN arquitetura AS a ON p.id_prod = a.id_arq INNER JOIN "
				+ "img_path AS i ON p.id_prod = i.id_prod WHERE p.id_prod = ?";
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_arq);
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
	
	
	@Override
	public List<Arquitetura> getArqSimp(List<String> query) {
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, a.id_arq, a.id_prod, i.id_img,"
				+ " i.path_img, i.desc_img, i.id_prod FROM produto AS p INNER JOIN "
				+ "arquitetura AS a ON p.id_prod = a.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod "
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') "
				+ "ORDER BY RAND() LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, query.get(0));
			ps.setString(2, query.get(1));
			ps.setString(3, query.get(2));
			ps.setInt(4, Integer.parseInt(query.get(3)));
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
		arq.setListImg(img);
		return arq;
	}

}