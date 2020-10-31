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
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Produto;

public class ProdutoDaoJDBC extends DB implements ProdutoDao{
	
	private Connection conn;
	
	public ProdutoDaoJDBC(){
		
	}
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Produto> getProdNoFiltro(String query, String limit){
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
			ps.setInt(11, Integer.parseInt(limit));
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
	
	
	
	private Produto instanciaProdSimp(ResultSet rs, Img img) throws SQLException {
		Produto prod = new Produto();
		
		prod.setTitulo(rs.getString("p.titulo"));
		prod.setAutor(rs.getString("p.autor"));
		prod.setLocalidade(rs.getString("p.localidade"));
		prod.setCategoria(rs.getString("p.categoria"));	
		prod.setImg(img);
		
		return prod;
	}

		
		
}