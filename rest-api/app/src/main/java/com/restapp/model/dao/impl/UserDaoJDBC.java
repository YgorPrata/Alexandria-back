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
import com.restapp.model.dao.UserDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Produto;

public class UserDaoJDBC extends DB implements UserDao{
	
	private Connection conn;
	
	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean insertArq(Arquitetura arq, List<Img> list) {
		boolean sucesso = false;
		int id = 0;
	
		try {
			ps = conn.prepareStatement("INSERT INTO produto (titulo, autor, descricao, tipo, "
					+ " categoria, localidade, ano, id_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, arq.getTitulo());
			ps.setString(2, arq.getAutor());
			ps.setString(3, arq.getDescricao());
			ps.setString(4, arq.getTipo());
			ps.setString(5, arq.getCategoria());
			ps.setString(6, arq.getLocalidade());
			ps.setInt(7, arq.getAno());
			ps.setInt(8, arq.getUser().getId_user());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
			
			String sql = "INSERT INTO arquitetura (curador, area, id_prod) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, arq.getCurador());
			ps.setDouble(2, arq.getArea());
			ps.setInt(3, id);
			ps.executeUpdate();		
			sucesso = true;
			
	
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}		
		
		try {
			for(Img img : list) {
				String sql = "INSERT INTO img_path (path_img, desc_img, id_prod) VALUES (?, ?, ?)";
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
		
		
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}			
		return sucesso;
	}
	
	@Override
	public boolean insertArte(Arte arte, List<Img> list) {
		boolean sucesso = false;
		int id = 0;
	
		try {
			ps = conn.prepareStatement("INSERT INTO produto (titulo, autor, descricao, tipo, "
					+ " categoria, localidade, ano, id_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, arte.getTitulo());
			ps.setString(2, arte.getAutor());
			ps.setString(3, arte.getDescricao());
			ps.setString(4, arte.getTipo());
			ps.setString(5, arte.getCategoria());
			ps.setString(6, arte.getLocalidade());
			ps.setInt(7, arte.getAno());
			ps.setInt(8, arte.getUser().getId_user());
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
		
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}			
		return sucesso;
	}
	
	@Override
	public boolean insertLivro(Livro livro, List<Img> list) {
		boolean sucesso = false;
		int id = 0;
	
		try {
			ps = conn.prepareStatement("INSERT INTO produto (titulo, autor, descricao, tipo, "
					+ " categoria, localidade, ano, id_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, livro.getTitulo());
			ps.setString(2, livro.getAutor());
			ps.setString(3, livro.getDescricao());
			ps.setString(4, livro.getTipo());
			ps.setString(5, livro.getCategoria());
			ps.setString(6, livro.getLocalidade());
			ps.setInt(7, livro.getAno());
			ps.setInt(8, livro.getUser().getId_user());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
						
			String sql = "INSERT INTO livro (editora, edicao, biografia, id_prod) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, livro.getEditora());
			ps.setInt(2, livro.getEdicao());
			ps.setString(3, livro.getBiografia());
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
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}			
		return sucesso;
	}

	@Override
	public List<Produto> displayUserProdsSimp(Integer id_user) {
		String sql = "SELECT p.id_prod, p.titulo, p.autor, p.localidade, p.descricao, p.categoria, i.id_img, i.path_img, "
				+ "i.desc_img FROM produto AS p INNER JOIN img_path AS i ON p.id_prod = i.id_prod WHERE p.id_user = ?";
				 		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_user);

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
	public Produto getProdById(Integer id_prod, Integer id_user) {		
		Produto prod = new Produto();
		try {
			String sql = "SELECT * FROM produto AS p INNER JOIN arquitetura AS a ON p.id_prod = a.id_prod INNER JOIN "
					+ "img_path AS i ON p.id_prod = i.id_prod WHERE p.id_prod = ? AND p.id_user = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_prod);
			ps.setInt(2, id_user);
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
					arq = instanciaTudoArq(rs, listimg);										
					map.put(rs.getInt("a.id_prod"), arq);
					for (Map.Entry<Integer, Arquitetura> entry : map.entrySet()) {
					    chave = entry.getKey();					    
					}
				}
				prod = arq;
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
		try {
			String sql = "SELECT * FROM produto AS p INNER JOIN livro AS l ON p.id_prod = l.id_prod INNER JOIN "
					+ "img_path AS i ON p.id_prod = i.id_prod WHERE p.id_prod = ? AND p.id_user = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_prod);
			ps.setInt(2, id_user);
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
					livro = instanciaTudoLivro(rs, listimg);										
					map.put(rs.getInt("l.id_prod"), livro);
					for (Map.Entry<Integer, Livro> entry : map.entrySet()) {
					    chave = entry.getKey();					    
					}
				}
				prod = livro;
			}			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
			
		try {
			String sql = "SELECT * FROM produto AS p INNER JOIN arte AS ar ON p.id_prod = ar.id_prod INNER JOIN "
					+ "img_path AS i ON p.id_prod = i.id_prod WHERE p.id_prod = ? AND p.id_user = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_prod);
			ps.setInt(2,  id_user);
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
					arte = instanciaTudoArte(rs, listimg);										
					map.put(rs.getInt("ar.id_prod"), arte);
					for (Map.Entry<Integer, Arte> entry : map.entrySet()) {
					    chave = entry.getKey();					    
					}
				}
				prod = arte;
			}			
		}
		 catch (SQLException e) {
			throw new DbException(e.getMessage());
		 }
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}	
		return prod;		
	}

	@Override
	public Produto updateUserArqProd(Integer id_user, Arquitetura arq) {
		String sql = "UPDATE produto p, arquitetura a, SET p.titulo = ?, p.autor = ?, "
					+ "p.localidade = ?, p.descricao = ?, p.tipo = ?, p.ano = ?, a.curador = ?,"
					+ " a.area = ? WHERE p.id_prod = ? AND p.id_user = ?";
		try {
            ps = conn.prepareStatement("sql");
            ps.setString(1, arq.getTitulo());
            ps.setString(2, arq.getAutor());
            ps.setString(3, arq.getLocalidade());
            ps.setString(4, arq.getDescricao());
            ps.setString(5, arq.getTipo());
            ps.setInt(6, arq.getAno());
            ps.setString(7, arq.getCurador());
            ps.setDouble(8, arq.getArea());
            ps.setInt(9, arq.getId_prod());
            ps.setInt(10, id_user);
            ps.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
        return arq;
	}

	@Override
	public boolean deleteUserProd(Integer id_prod) {
		// TODO Auto-generated method stub
		return false;
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
	
	private Arquitetura instanciaTudoArq(ResultSet rs, List<Img> img) throws SQLException{
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
	
	private Arte instanciaTudoArte(ResultSet rs, List<Img> img) throws SQLException{
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
	
	private Livro instanciaTudoLivro(ResultSet rs, List<Img> listimg) throws SQLException {
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
