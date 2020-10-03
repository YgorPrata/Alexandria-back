package com.restapp.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Arte;


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
		
		
		////////////////INSERT LIVRO\\\\\\\\\\\\\		
		/*LivroDaoJDBC livrodao = new LivroDaoJDBC();

		Livro livro = new Livro(2, 2020, "", "editora2", "biografia2", "descricao2", "autor2", "titulo2", "categoria2");
				
		livrodao.insert(livro, "nometeste");
		
		*/
		
		
		////////////////INSERT ARTE\\\\\\\\\\\\\		
		/*ArteDaoJDBC artedao = new ArteDaoJDBC();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Arte arte = new Arte("categoria2", "titulo2", "autor2", "tipo2", "material2", "tecnica2", sdf.parse("01/01/2020"), "descricao2");
		
		artedao.insert(arte, "nometest");*/
		
		
		
		////////////////INSERT ARQUITETURA\\\\\\\\\\\\\		
		/*ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Arquitetura arq = new Arquitetura("categoriateste", "nometeste", "tipoteste", "autorteste", "materialteste", sdf.parse("10/09/2020"), "descricaoteste");

		arqdao.insert(arq);*/
		
		/*ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
		
		System.out.println(arqdao.findByName("nome").size());*/
		
		/*String teste = "c:/temp/texto ok.txt";
		
		System.out.println(teste.endsWith(""));*/
		
		/*List<String> list = new ArrayList<String>();
		String path = "c:/temp/";
		list.add("129381290382093.jpg");
		list.add("1293dasda82093.jpg");
		list.add("129381eqeqwcqq4390382093.jpg");
		
		for(int i = 0; i <= list.size() - 1; i++) {
			String palavras = list.get(i);
			System.out.println(path.concat(palavras));
		
		}*/
		
		
		//random-string
		//System.out.println(UUID.randomUUID().toString());
		
		/*ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
		List<Arquitetura> list = new ArrayList<Arquitetura>();
		
		list = arqdao.GetImageByName("Museu do amanha");
		
		for(Arquitetura arq : list) {
			System.out.println(arq+"\n");
		}*/
		
		ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
		List<Arquitetura> list = new ArrayList<Arquitetura>();
		list = arqdao.GetImageByName("museu do inferno");
		
		Arquitetura aux = new Arquitetura();
		for(int i = 0; i <= list.size() - 1; i++ ) {
			aux = list.get(i);
			
			System.out.println("SAIDA DOS IDS DAS IMG: " +aux.getId_img());
			System.out.println("SAIDA DOS CAMINHOS: "+aux.getImg_path());
			System.out.println("SAIDA DAS DESCRICOES: "+aux.getImg_desc());
		}
		System.out.println("ARQUITETURA: "+aux);
		
	}
	
}