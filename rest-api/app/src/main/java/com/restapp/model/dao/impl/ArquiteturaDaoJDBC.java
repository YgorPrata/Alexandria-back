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
import com.restapp.model.entities.Txt;

public class ArquiteturaDaoJDBC extends DB implements ArquiteturaDao {

	private Connection conn;

	public ArquiteturaDaoJDBC() {

	}

	public ArquiteturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insert(Arquitetura arq, List<Img> list, Txt txt) {
		
		boolean sucesso = false;
		int id = 0;
	
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("INSERT INTO arquitetura (titulo, autor, descricao, tipo, "
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
			
			sucesso = true;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		try {
			for(Img img : list) {
				String sql = "INSERT INTO img_path (path_img, desc_img, id_arq) VALUES (?, ?, "+ id +")";
				conn = DB.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, img.getPath_img());
				ps.setString(2,  img.getDesc_img());
				ps.executeUpdate();
				sucesso = true;					
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		try {			
			String sql = "INSERT INTO txt_path (path_txt, id_arq) VALUES (?, "+ id +")";
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

		List<Arquitetura> list = new ArrayList<Arquitetura>();
		String sql = "SELECT * FROM arquitetura";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(instanciaTudo(rs));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return list;
	}

	public List<Arquitetura> getById(Integer id_arq) {
		String sql = "SELECT * FROM arquitetura AS arq INNER JOIN img_path AS img ON arq.id_arq = img.id_arq WHERE arq.id_arq = ?";
		 
		List<Arquitetura> list = new ArrayList<Arquitetura>();
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id_arq);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(instanciaTudo(rs));
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
	
	
	@Override
	public List<Arquitetura> getArqSimpAutor(String autor) {		
		String sql = "SELECT arq.id_arq, arq.titulo, arq.autor, arq.descricao, arq.localidade, "
				+ " img.id_img, img.path_img, img.desc_img, img.id_arq FROM arquitetura AS arq INNER JOIN img_path AS img "
				+ "ON arq.id_arq = img.id_arq WHERE arq.autor LIKE CONCAT( '%',?,'%')";

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, autor);

			rs = ps.executeQuery();
			List<Arquitetura> list = new ArrayList<Arquitetura>();
			
			while (rs.next()) {
				list.add(instanciaArqSimp(rs));
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}
	
	@Override
	public List<Arquitetura> getArqSimpLocal(String local){		
		String sql = "SELECT arq.id_arq, arq.titulo, arq.autor, arq.descricao, arq.localidade, "
				+ " img.id_img, img.path_img, img.desc_img, img.id_arq FROM arquitetura AS arq INNER JOIN img_path AS img "
				+ "ON arq.id_arq = img.id_arq WHERE arq.localidade LIKE CONCAT( '%',?,'%')";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, local);

			rs = ps.executeQuery();
			List<Arquitetura> list = new ArrayList<Arquitetura>();
			
			while (rs.next()) {
				list.add(instanciaArqSimp(rs));
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}
	
	@Override
	public List<Img> getArqSimpTitulo(String titulo) {
		String sql = "SELECT arq.id_arq, arq.titulo, arq.autor, arq.descricao, arq.localidade, img.id_arq, "
				+ " img.id_img, img.path_img, img.desc_img FROM arquitetura AS arq "
				+ "INNER JOIN img_path AS img ON arq.id_arq = img.id_arq WHERE arq.titulo LIKE CONCAT( '%',?,'%')";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, titulo);
			rs = ps.executeQuery();			
			
	
			List<Img> list = new ArrayList<Img>();
			Map<Integer, Arquitetura> map = new HashMap<Integer, Arquitetura>();
 			
			while(rs.next()) {
				
				Arquitetura arq = map.get(rs.getInt("arq.id_arq"));		
				Img img;
				if(arq == null) {
					arq = instanciaArqSimp(rs);
					img = instanciaImg(rs, arq);
					list.add(img);
					map.put(rs.getInt("arq.id_arq"), arq);
					
				}	
				else {
					img = instanciaImg(rs, null);
					list.add(img);
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
	
	
	private Img instanciaImg(ResultSet rs, Arquitetura arq) throws SQLException{
		Img img = new Img();		
		img.setId_img(rs.getInt("id_img"));
		img.setPath_img(rs.getString("path_img"));
		img.setDesc_img(rs.getString("desc_img"));
		img.setArq(arq);
		return img;
	}


	private Arquitetura instanciaArqSimp(ResultSet rs) throws SQLException {
		Arquitetura arq = new Arquitetura();
		arq.setId_arq(rs.getInt("id_arq"));
		arq.setTitulo(rs.getString("titulo"));
		arq.setAutor(rs.getString("autor"));
		arq.setDescricao(rs.getString("descricao"));
		arq.setLocalidade(rs.getString("localidade"));		
		return arq;
	}
	
	private Arquitetura instanciaTudo(ResultSet rs) throws SQLException{
		Arquitetura arq = new Arquitetura();
		arq.setId_arq(rs.getInt("id_arq"));
		arq.setTitulo(rs.getString("titulo"));
		arq.setAutor(rs.getString("autor"));
		arq.setDescricao(rs.getString("descricao"));
		arq.setCategoria(rs.getString("categoria"));
		arq.setTipo(rs.getString("tipo"));
		arq.setLocalidade(rs.getString("localidade"));
		arq.setAno(rs.getInt("ano"));		
		return arq;
	}

}