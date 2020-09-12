package com.restapp.model.dao.impl;

import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Livro;

public class MainTeste {

	public static void main(String[] args) throws Exception {
		
		/*Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
				
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from artigos");
			
			while(rs.next()) {
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("titulo"));
				System.out.println(rs.getString("autor"));
				System.out.println(rs.getString("assunto"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}*/
		
		/*List<Artigos> list = new ArrayList<>();
		
		ArtigosDaoJDBC artigosdao = new ArtigosDaoJDBC();
		
		list = artigosdao.findAll();
		
		for(Artigos artigos : list) {
			System.out.println(artigos);
		}
		
		System.out.println("=== TEST 1: seller findById ====");
		artigosdao.findById(1);
		System.out.println(artigosdao);
		
		
		System.out.println("=== TEST 1: seller findByName ====");
		list = artigosdao.findByName("felipe 2");
		for(Artigos artigos : list) {
			System.out.println(artigos);
		}
		*/
		////////////////TESTE NULL EXCEPTION\\\\\\\\\\\\\
		/*ArtigosDaoJDBC artigosdao = new ArtigosDaoJDBC();
		
		artigosdao.findAll();
		System.out.println("Tabela apagada");*/
		
		//ArtigosDaoJDBC artdao = new ArtigosDaoJDBC();
		
		//System.out.println(artdao.findAll());
		
		//testelivroin
		
		LivroDaoJDBC livrodao = new LivroDaoJDBC();

		Livro livro = new Livro(2, 2020, "tipo2", "editora2", "biografia2", "descricao2", "autor2", "titulo2", "categoria2");
				
		livrodao.insert(livro, "nometeste");

	}

}