package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.ArquiteturaDao;
import com.restapp.model.entities.Arquitetura;

public class ArquiteturaDaoJDBC extends DB implements ArquiteturaDao {

	private Connection conn;

	public ArquiteturaDaoJDBC() {

	}

	public ArquiteturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insert(Arquitetura arq, List<String> listimg, List<String> listdesc, String arqtxt) {
		
		boolean sucesso = false;
		int id = 0;
		String caminho;
		String descimg;
	
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("INSERT INTO arquitetura (titulo, autor, descricao, tipo, "
					+ " categoria, localidade, ano) VALUES (?, ?, ?, ?, ?, ?, ?)",st.RETURN_GENERATED_KEYS);
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
			for(int i = 0; i <= listimg.size() - 1; i++) {
				caminho = listimg.get(i);
				descimg = listdesc.get(i);
					if(caminho.endsWith(".jpg") || caminho.endsWith(".png")) {
						String sql = "INSERT INTO img_path (path_img, desc_img, id_arq) VALUES ('" + caminho + "', '" + descimg + "', "+ id +")";
						conn = DB.getConnection();
						ps = conn.prepareStatement(sql);						
						ps.executeUpdate();
						sucesso = true;
					}
					else {
						System.out.println("Extensão não aceita.");
					}
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		try {			
			if(arqtxt.endsWith(".pdf") || arqtxt.endsWith(".txt") || arqtxt.endsWith(".docx")) {
				String sql = "INSERT INTO txt_path (path_txt, id_arq) VALUES ('" + arqtxt + "', "+ id +")";
				conn = DB.getConnection();
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				sucesso = true;		
			}
			else {
				System.out.println("Extensão não aceita.");
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
	public List<Arquitetura> getArqSimpTitulo(String titulo) {
		List<Arquitetura> list = new ArrayList<Arquitetura>();
		String sql = "SELECT arq.id_arq, arq.titulo, arq.autor, arq.descricao, arq.localidade, "
				+ " img.id_img, img.path_img, img.desc_img, img.id_arq FROM arquitetura AS arq "
				+ "INNER JOIN img_path AS img ON arq.id_arq = img.id_arq WHERE arq.titulo LIKE CONCAT( '%',?,'%')";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, titulo);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(instanciaArqSimp(rs));
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
		List<Arquitetura> list = new ArrayList<Arquitetura>();
		String sql = "SELECT arq.id_arq, arq.titulo, arq.autor, arq.descricao, arq.localidade, "
				+ " img.id_img, img.path_img, img.desc_img, img.id_arq FROM arquitetura AS arq INNER JOIN img_path AS img "
				+ "ON arq.id_arq = img.id_arq WHERE arq.autor LIKE CONCAT( '%',?,'%')";

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, autor);

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(instanciaArqSimp(rs));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

		return list;
	}
	
	@Override
	public List<Arquitetura> getArqSimpLocal(String local){
		List<Arquitetura> list = new ArrayList<Arquitetura>();
		String sql = "SELECT arq.id_arq, arq.titulo, arq.autor, arq.descricao, arq.localidade, "
				+ " img.id_img, img.path_img, img.desc_img, img.id_arq FROM arquitetura AS arq INNER JOIN img_path AS img "
				+ "ON arq.id_arq = img.id_arq WHERE arq.localidade LIKE CONCAT( '%',?,'%')";
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, local);

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(instanciaArqSimp(rs));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
		return list;
	}

	
	private Arquitetura instanciaImagens(ResultSet rs) throws SQLException{
		Arquitetura arq = new Arquitetura();
		arq.setId_img(rs.getInt("id_img"));
		arq.setImg_path(rs.getString("path_img"));
		arq.setImg_desc(rs.getString("desc_img"));
		arq.setId_arq(rs.getInt("id_arq"));
		return arq;
	}

	private Arquitetura instanciaArqSimp(ResultSet rs) throws SQLException {
		Arquitetura arq = new Arquitetura();
		arq.setId_arq(rs.getInt("id_arq"));
		arq.setTitulo(rs.getString("titulo"));
		arq.setAutor(rs.getString("autor"));
		arq.setDescricao(rs.getString("descricao"));
		arq.setLocalidade(rs.getString("localidade"));
		arq.setId_img(rs.getInt("id_img"));
		arq.setImg_path(rs.getString("path_img"));
		arq.setImg_desc(rs.getString("desc_img"));
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
		arq.setId_img(rs.getInt("id_img"));
		arq.setImg_path(rs.getString("path_img"));
		arq.setImg_desc(rs.getString("desc_img"));
		arq.setTxt_path(rs.getString("path_txt"));
		return arq;
	}

}