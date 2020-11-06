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
import com.restapp.model.dao.ArteDao;
import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Txt;

public class ArteDaoJDBC extends DB implements ArteDao {

	private Connection conn;

	public ArteDaoJDBC() {

	}

	public ArteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insert(Arte arte, List<Img> list, Txt txt) {
		boolean sucesso = false;
		int id = 0;
	
		try {
			ps = conn.prepareStatement("INSERT INTO produto (titulo, autor, descricao, tipo, "
					+ " categoria, localidade, ano) VALUES (?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, arte.getTitulo());
			ps.setString(2, arte.getAutor());
			ps.setString(3, arte.getDescricao());
			ps.setString(4, arte.getTipo());
			ps.setString(5, arte.getCategoria());
			ps.setString(6, arte.getLocalidade());
			ps.setInt(7, arte.getAno());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
						
			String sql = "INSERT INTO arte (tecnica, id_prod) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, arte.getTecnica());			
			ps.setInt(2, id);
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
	public List<Arte> getAll() {
		String sql = "SELECT * FROM produto AS p INNER JOIN arte AS ar ON p.id_prod = ar.id_prod "
				+ "INNER JOIN img_path AS i ON p.id_prod = i.id_prod";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			List<Arte> list = new ArrayList<>();	
			Map<Integer, Arte> map = new HashMap<Integer, Arte>();
 			List<Img> listimg = new ArrayList<>();
  			int cont = 0;
  			int chave = 0;
 			
			while(rs.next()) {
				cont++;				
				Arte arte = map.get(rs.getInt("ar.id_prod"));
				
				if(chave == rs.getInt("i.id_prod") || cont == 1) {
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
				
				if(chave != rs.getInt("i.id_prod") && cont != 1) {
					listimg = new ArrayList<>();
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
								
				if(arte == null) {
					arte = instanciaTudo(rs, listimg);					
					list.add(arte);
					map.put(rs.getInt("ar.id_prod"), arte);
					for (Map.Entry<Integer, Arte> entry : map.entrySet()) {
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

	public Arte getById(Integer id_arte) {
		String sql = "SELECT * FROM produto AS p INNER JOIN arte AS ar ON p.id_prod = ar.id_prod INNER JOIN "
				+ "img_path AS i ON p.id_prod = i.id_prod WHERE p.id_prod = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_arte);
			rs = ps.executeQuery();
			
			Arte arte = new Arte();	
			Map<Integer, Arte> map = new HashMap<Integer, Arte>();
 			List<Img> listimg = new ArrayList<>();
  			int cont = 0;
  			int chave = 0;
 			
			while(rs.next()) {
				cont++;				
				arte = map.get(rs.getInt("ar.id_prod"));
				
				if(chave == rs.getInt("i.id_prod") || cont == 1) {
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
				
				if(chave != rs.getInt("i.id_prod") && cont != 1) {
					listimg = new ArrayList<>();
					listimg.add(new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img")));					
				}
								
				if(arte == null) {
					arte = instanciaTudo(rs, listimg);										
					map.put(rs.getInt("ar.id_prod"), arte);
					for (Map.Entry<Integer, Arte> entry : map.entrySet()) {
					    chave = entry.getKey();					    
					}
				}				
			}
		
			return arte;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);			
		}
	}
	
	
	@Override
	public List<Arte> getArteTipo(String titulo, String autor, String localidade, Integer limit) {
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, ar.id_arte, ar.id_prod, i.id_img,"
				+ " i.path_img, i.desc_img, i.id_prod FROM produto AS p INNER JOIN "
				+ "arte AS ar ON p.id_prod = ar.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod "
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') "
				+ "ORDER BY RAND() LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, titulo);
			ps.setString(2, autor);
			ps.setString(3, localidade);
			ps.setInt(4, limit);
			rs = ps.executeQuery();
			
			List<Arte> list = new ArrayList<>();	
			Map<Integer, Arte> map = new HashMap<Integer, Arte>();
 			Img img;
 			
			while(rs.next()) {			
				Arte arte = map.get(rs.getInt("ar.id_prod"));

				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(arte == null) {
					arte = instanciaArqSimp(rs, img);					
					list.add(arte);
					map.put(rs.getInt("ar.id_prod"), arte);					
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
	public List<Arte> getArteCategoria(String query, Integer limit){
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, ar.id_arte, ar.id_prod, i.id_img, " 
				+ "i.path_img, i.desc_img, i.id_prod FROM produto AS p INNER JOIN " 
				+ "arte AS ar ON p.id_prod = ar.id_prod INNER JOIN img_path AS i ON p.id_prod = i.id_prod " 
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') " 
				+ "OR p.descricao LIKE CONCAT( '%',?,'%') OR ar.tecnica LIKE CONCAT( '%',?,'%') "
				+ "ORDER BY RAND() LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, query);
			ps.setString(2, query);
			ps.setString(3, query);
			ps.setString(4, query);
			ps.setString(5, query);
			ps.setInt(6, limit);
			rs = ps.executeQuery();
			
			List<Arte> list = new ArrayList<>();	
			Map<Integer, Arte> map = new HashMap<Integer, Arte>();
 			Img img;
 			
			while(rs.next()) {			
				Arte arte = map.get(rs.getInt("ar.id_prod"));
				
				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(arte == null) {
					arte = instanciaArqSimp(rs, img);					
					list.add(arte);
					map.put(rs.getInt("ar.id_prod"), arte);					
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
	
	
	

	private Arte instanciaArqSimp(ResultSet rs, Img img) throws SQLException {
		Arte arte = new Arte();
		arte.setId_prod(rs.getInt("p.id_prod"));
		arte.setTitulo(rs.getString("p.titulo"));
		arte.setAutor(rs.getString("p.autor"));
		arte.setDescricao(rs.getString("p.descricao"));
		arte.setLocalidade(rs.getString("p.localidade"));
		arte.setCategoria(rs.getString("p.categoria"));
		arte.setId_arte(rs.getInt("ar.id_arte"));
		arte.setImg(img);
		return arte;
	}
	
	private Arte instanciaTudo(ResultSet rs, List<Img> img) throws SQLException{
		Arte arte = new Arte();
		arte.setId_prod(rs.getInt("p.id_prod"));
		arte.setTitulo(rs.getString("p.titulo"));
		arte.setAutor(rs.getString("p.autor"));
		arte.setDescricao(rs.getString("p.descricao"));
		arte.setCategoria(rs.getString("p.categoria"));
		arte.setTipo(rs.getString("p.tipo"));
		arte.setLocalidade(rs.getString("p.localidade"));
		arte.setAno(rs.getInt("p.ano"));
		arte.setId_arte(rs.getInt("ar.id_arte"));
		arte.setTecnica(rs.getString("ar.tecnica"));
		arte.setListImg(img);
		return arte;
	}
}
