package com.restapp.model.dao.impl;


import com.restapp.model.dao.DaoFactory;
import com.restapp.model.dao.LoginDao;
import com.restapp.model.entities.User;

public class MainTeste {

	public static void main(String[] args) throws Exception {	
	
			
		//ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
		//ArquiteturaDao arqdao = DaoFactory.criarArquitetura();
		//ProdutoDao prod = DaoFactory.criarProduto();
		//LoginDao user = DaoFactory.autenticaUsuario();
		//System.out.println(arqdao.getById(1));
		//System.out.println(prod.getProdCount());
		
		//User usuario = new User(null, "felipeuser", "user123", "user", "felipe user");
		
		//user.insert(usuario);
		
		//user.validaUsuario(usuario);	
		
		/*CRIPTOGRAFANDO LADO SERVIDOR SHA2 256 bits*/
		/*
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest("admin123".getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (int i: hash) {
            hexString.append(String.format("%02x", 0XFF & i));           
        }
        System.out.println(hexString);
        */
		
		
				
		/*CRIPTOGRANDO LADO SERVIDOR SHA2 256 BITS COM APACHE COMMON CODEC DIGEST UTILS"
		String encrypt = DigestUtils.sha256Hex(password);
		*/
		
		
		
		
      //CRIPTOGRAFANDO LADO SERVIDOR MD5
      		/*MessageDigest m = MessageDigest.getInstance("MD5");
      		String password = "usersenha";
      		m.reset(); // <---- Reseta antes de fazer o password
      		m.update(password.getBytes(), 0, password.length());
      		BigInteger password1 = new BigInteger(1, m.digest());
      		password = String.format("%1$032x", password1);

      		System.out.println(password);*/
		
		
		/*List<Produto> listarq = new ArrayList<>(); 
		List<Img> listimg = new ArrayList<>();
		listimg.add(new Img(2, "c:/imgs/imagem1.jpg", "descricao1", null));
		listimg.add(new Img(3, "c:/imgs/imagem2.jpg", "descricao2", null));
		listimg.add(new Img(4, "c:/imgs/imagem3.jpg", "descricao3", null));
		listimg.add(new Img(5, "c:/imgs/imagem4.jpg", "descricao4", null));		
		listarq.add(new Arquitetura("Titulo1", "Autor1", "desricacao1", "categoria1", "tipo1", "localidad1", 1, listimg ,"curador1", 500.0, null, null));
		for(Produto arq : listarq) {
			System.out.println("Lista de imagem 1: "+arq.getImg());
		}
		
		listimg.clear();
		listarq.clear();
		listimg.add(new Img(6, "c:/imgs/imagem5.jpg", "descricao5", null));
		listimg.add(new Img(7, "c:/imgs/imagem6.jpg", "descricao6", null));
		listimg.add(new Img(8, "c:/imgs/imagem7.jpg", "descricao7", null));
		listimg.add(new Img(9, "c:/imgs/imagem8.jpg", "descricao8", null));
		listarq.add(new Arquitetura("Titulo2", "Autor2", "desricacao2", "categoria2", "tipo2", "localidad2", 2, listimg ,"curador2", 1000.0, null, null));
		
		for(Produto arq : listarq) {
			System.out.println("Lista de imagem 2: "+arq.getImg());
		}*/
		
		
		
		/*Produto prod = new Arquitetura();
		List<Img> listimg = new ArrayList<>();
		listimg.add(new Img(null, "c:/img/img1.jpg", "descricao", null));
		listimg.add(new Img(null, "c:/img/img2.jpg", "descricao2", null));

		prod.setImg(listimg);
		
		System.out.println("PROD IMGS: "+prod.getImg());
		*/
		
		
		
		
		/*Arquitetura arq = new Arquitetura("titulo", "autor", "descricao", null, null, "localidade", null);
		List<Img> list = new ArrayList<Img>();
		
		list.add(new Img("imgs/1234.jpg", "descricao1", arq, null, null));
		list.add(new Img("imgs/123.jpg", "descricao2", null, null, null));
		list.add(new Img("imgs/12.jpg", "descricao3", null, null, null));
		list.add(new Img("imgs/1.jpg", "descricao4", null, null, null));
		
		
		
		for(Img img : list) {
			//System.out.println(img.getArq());
			//System.out.println(img.getId_img());
			//System.out.println(img.getPath_img());
			//System.out.println(img.getDesc_img());	
			
			System.out.println(img);
		}*/
		
		//ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
		
		//System.out.println(arqdao.getArqSimpTitulo("museu"));
		
		
		
		//CRIPTOGRAFANDO LADO SERVIDOR
		/*MessageDigest m = MessageDigest.getInstance("MD5");
		String password = "usersenha";
		m.reset(); // <---- Reseta antes de fazer o password
		m.update(password.getBytes(), 0, password.length());
		BigInteger password1 = new BigInteger(1, m.digest());
		password = String.format("%1$032x", password1);

		System.out.println(password);*/
			
		//List<Produto> list = new ArrayList<>();
		//Arquitetura arq = new Arquitetura();
		//List<Img> listimg = new ArrayList<>();
		/*
		list.add(new Arquitetura("Museu do Amanha", "Prefeitura", "Museu do Rio de Janeiro", "Arquitetura", "Moderno", "Rio de Janeiro", 2015, "Curador", 10455.35, null, null));
		list.add(new Arquitetura("Museu do Exercito", "Exercito", "Museu do Exercito Brasileiro no Rio de Janeiro", "Arquitetura", "Moderno", "Rio de Janeiro", 2000, "Curador", 2250.32, null, null));
		
		for(Produto prod : list) {
			System.out.println("Prod: "+prod+"\nList: "+list);
			System.out.println("\n\n");
		}*/
			
		
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
		
		/*ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
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
		*/
		
		//ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
		
		//System.out.println(arqdao.getArqSimpLocal("rio de janeiro"));
		
		

	}
	
}