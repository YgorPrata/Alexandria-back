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
					prod = instanciaArqNoFiltro(rs, img);
					prod = instanciaLivroNoFiltro(rs, img);
					prod = instanciaArteNoFiltro(rs, img);
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
	
	
	private Arquitetura instanciaArqNoFiltro(ResultSet rs, Img img) throws SQLException {
		Arquitetura arq = new Arquitetura();
		
		arq.setTitulo(rs.getString("p.titulo"));
		arq.setAutor(rs.getString("p.autor"));
		arq.setDescricao(rs.getString("p.descricao"));
		arq.setLocalidade(rs.getString("p.localidade"));
		arq.setCategoria(rs.getString("p.categoria"));	
		arq.setId_arq(rs.getInt("a.id_arq"));
		arq.setId_prod(rs.getInt("a.id_prod"));
		arq.setCurador(rs.getString("a.curador"));
		arq.setArea(rs.getDouble("a.area"));
		arq.setImg(img);
		
		return arq;
	}
	
	private Livro instanciaLivroNoFiltro(ResultSet rs, Img img) throws SQLException{
		Livro livro = new Livro();
		
		livro.setTitulo(rs.getString("p.titulo"));
		livro.setAutor(rs.getString("p.autor"));
		livro.setDescricao(rs.getString("p.descricao"));
		livro.setLocalidade(rs.getString("p.localidade"));
		livro.setCategoria(rs.getString("p.categoria"));
		livro.setId_livro(rs.getInt("l.id_livro"));
		livro.setEditora(rs.getString("l.editora"));
		livro.setEdicao(rs.getInt("l.edicao"));
		livro.setBiografia(rs.getString("l.biografia"));
		livro.setId_prod(rs.getInt("l.id_prod"));
		livro.setImg(img);

		return livro;
	}
	
	private Arte instanciaArteNoFiltro(ResultSet rs, Img img) throws SQLException{
		Arte arte = new Arte();
		
		arte.setTitulo(rs.getString("p.titulo"));
		arte.setAutor(rs.getString("p.autor"));
		arte.setDescricao(rs.getString("p.descricao"));
		arte.setLocalidade(rs.getString("p.localidade"));
		arte.setCategoria(rs.getString("p.categoria"));
		arte.setId_arte(rs.getInt("ar.id_arte"));
		arte.setTecnica(rs.getString("ar.tecnica"));
		arte.setId_prod(rs.getInt("ar.id_prod"));
		arte.setImg(img);

		return arte;
	}
	
}
