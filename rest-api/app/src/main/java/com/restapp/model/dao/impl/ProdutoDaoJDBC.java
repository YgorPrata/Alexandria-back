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
import com.restapp.model.dao.ProdutoDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Produto;

public class ProdutoDaoJDBC extends DB implements ProdutoDao{
	
	private Connection conn;
	
	public ProdutoDaoJDBC(){
		
	}
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Produto> getProdNoFiltro(String query, Integer limit){
		String sql = "SELECT * FROM produto AS p LEFT JOIN arquitetura AS a ON p.id_prod = a.id_prod "
				+ "LEFT JOIN livro AS l ON p.id_prod = l.id_prod " 
				+ "LEFT JOIN arte AS ar ON p.id_prod = ar.id_prod "
				+ "LEFT JOIN img_path AS i ON p.id_prod = i.id_prod " 
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') OR p.descricao LIKE CONCAT( '%',?,'%') "
				+ "OR p.tipo LIKE CONCAT( '%',?,'%') OR p.localidade LIKE CONCAT( '%',?,'%') "
				+ "OR p.ano LIKE CONCAT( '%',?,'%') OR a.curador LIKE CONCAT( '%',?,'%') "
				+ "OR l.editora LIKE CONCAT( '%',?,'%') OR l.biografia LIKE CONCAT( '%',?,'%') "
				+ "OR ar.tecnica LIKE CONCAT( '%',?,'%') ORDER BY RAND() LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, query);
			ps.setString(2, query);
			ps.setString(3, query);
			ps.setString(4, query);
			ps.setString(5, query);
			ps.setString(6, query);
			ps.setString(7, query);
			ps.setString(8, query);
			ps.setString(9, query);
			ps.setString(10, query);
			ps.setInt(11, limit);
			rs = ps.executeQuery();
			
			List<Produto> list = new ArrayList<>();
			Map<Integer, Produto> map = new HashMap<Integer, Produto>();
 			Img img;
 			
			while(rs.next()) {			
				Produto prod = map.get(rs.getInt("p.id_prod"));
				
				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(prod == null) {
					prod = instanciaProdSimp(rs, img);
					list.add(prod);
					map.put(rs.getInt("p.id_prod"), prod);					
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
	public List<Produto> getNovidades(Integer limit) {
		String sql = "SELECT * FROM produto AS p INNER JOIN img_path AS i ON p.id_prod = i.id_prod GROUP BY p.id_prod ORDER BY p.id_prod DESC LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit);
			
			rs = ps.executeQuery();
			
			List<Produto> list = new ArrayList<>();	
			Map<Integer, Produto> map = new HashMap<Integer, Produto>();
 			Img img;
 			
			while(rs.next()) {			
				Produto arq = map.get(rs.getInt("p.id_prod"));
				
				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(arq == null) {
					arq = instanciaProdSimp(rs, img);					
					list.add(arq);
					map.put(rs.getInt("p.id_prod"), arq);					
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
	public List<Produto> getProdTipo(String titulo, String autor, String localidade, Integer limit){
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.descricao, p.localidade, p.categoria, i.id_img, " 
				+ " i.path_img, i.desc_img, i.id_prod FROM produto AS p INNER JOIN img_path AS i ON p.id_prod = i.id_prod "
				+ "WHERE p.titulo LIKE CONCAT( '%',?,'%') OR p.autor LIKE CONCAT( '%',?,'%') "
				+ "OR p.localidade LIKE CONCAT( '%',?,'%') ORDER BY RAND() LIMIT ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, titulo);
			ps.setString(2, autor);
			ps.setString(3, localidade);
			ps.setInt(4, limit);
			rs = ps.executeQuery();
			
			List<Produto> list = new ArrayList<>();
			Map<Integer, Produto> map = new HashMap<Integer, Produto>();
 			Img img;
 			
			while(rs.next()) {			
				Produto prod = map.get(rs.getInt("p.id_prod"));
				
				img = new Img(rs.getInt("i.id_img"), rs.getString("i.path_img"), rs.getString("i.desc_img"));
											
				if(prod == null) {
					prod = instanciaProdSimp(rs, img);
					list.add(prod);
					map.put(rs.getInt("p.id_prod"), prod);					
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
	public Integer getProdCount() {
		String sql = "SELECT COUNT(*) AS total FROM produto";
		try {
			ps = conn.prepareStatement(sql);			
			rs = ps.executeQuery();
			
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("total");
			}
			
			return count;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}
	
	private Produto instanciaProdSimp(ResultSet rs, Img img) throws SQLException {
		Produto prod = new Produto();
		
		prod.setId_prod(rs.getInt("p.id_prod"));
		prod.setTitulo(rs.getString("p.titulo"));
		prod.setAutor(rs.getString("p.autor"));
		prod.setLocalidade(rs.getString("p.localidade"));
		prod.setDescricao(rs.getString("p.descricao"));
		prod.setCategoria(rs.getString("p.categoria"));	
		prod.setImg(img);
		
		return prod;
	}
	
	private List<Produto> instanciaTudo(ResultSet rs, List<Img> img) throws SQLException{
		Produto prod = new Produto();
		Arquitetura arq = new Arquitetura();
		Arte arte = new Arte();
		Livro livro = new Livro();
		List<Produto> list = new ArrayList<Produto>();
		prod.setId_prod(rs.getInt("p.id_prod"));
		prod.setTitulo(rs.getString("p.titulo"));
		prod.setAutor(rs.getString("p.autor"));
		prod.setDescricao(rs.getString("p.descricao"));
		prod.setCategoria(rs.getString("p.categoria"));
		prod.setTipo(rs.getString("p.tipo"));
		prod.setLocalidade(rs.getString("p.localidade"));
		prod.setAno(rs.getInt("p.ano"));
		arq.setCurador(rs.getString("a.curador"));
		arq.setArea(rs.getDouble("a.area"));
		livro.setBiografia(rs.getString("l.biografia"));
		livro.setEdicao(rs.getInt("l.edicao"));
		livro.setEditora(rs.getString("l.editora"));
		arte.setTecnica(rs.getString("ar.tecnica"));
		prod.setListImg(img);
		
		list.add(prod);
		list.add(arq);
		list.add(livro);
		list.add(arte);
		return list;
	}
		
}
