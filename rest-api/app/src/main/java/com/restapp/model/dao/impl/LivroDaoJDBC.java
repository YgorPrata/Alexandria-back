package com.restapp.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.restapp.db.DB;
import com.restapp.db.DbException;
import com.restapp.model.dao.LivroDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Txt;

public class LivroDaoJDBC extends DB implements LivroDao {

	private Connection conn;

	public LivroDaoJDBC() {

	}

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insert(Livro liv, List<Img> list, Txt txt) {
		boolean sucesso = false;
		int id = 0;
	
		try {
			ps = conn.prepareStatement("INSERT INTO produto (titulo, autor, descricao, tipo, "
					+ " categoria, localidade, ano) VALUES (?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, liv.getTitulo());
			ps.setString(2, liv.getAutor());
			ps.setString(3, liv.getDescricao());
			ps.setString(4, liv.getTipo());
			ps.setString(5, liv.getCategoria());
			ps.setString(6, liv.getLocalidade());
			ps.setInt(7, liv.getAno());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
						
			String sql = "INSERT INTO livro (editora, edicao, biografia, id_prod) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, liv.getEditora());
			ps.setInt(2, liv.getEdicao());
			ps.setString(3, liv.getBiografia());
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
	public Livro getById(Integer id_arq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livro> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livro> getArqSimp(List<String> query) {
		// TODO Auto-generated method stub
		return null;
	}

	private Livro instanciaLivSimp(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
		livro.setId_livro(rs.getInt("id_livro"));
		livro.setAutor(rs.getString("autor"));
		livro.setTitulo(rs.getString("titulo"));
		livro.setDescricao(rs.getString("descricao"));
		livro.setAno(rs.getInt("ano"));
		livro.setCategoria(rs.getString("categoria"));
		livro.setBiografia(rs.getString("biografia"));
		livro.setEditora(rs.getString("editora"));
		livro.setTipo(rs.getString("tipo"));
		return livro;
	}


}
