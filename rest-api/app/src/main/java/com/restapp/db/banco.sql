drop database if exists alexandria;
create database alexandria;
use alexandria;

create table usuario(
	id_user int not null primary key auto_increment,
	login varchar(20) not null,
	senha varchar(100) not null
);

create table produto(
	id_prod int not null primary key auto_increment,
	titulo varchar(50),
	autor varchar(20),
    descricao varchar(10000),	
    categoria varchar(20),
	tipo varchar(20),
    localidade varchar(20),
	ano int,
	id_user int,
	
	CONSTRAINT fk_produtouser FOREIGN KEY (id_user) REFERENCES usuario(id_user)
);

create table Arquitetura(
	id_arq int not null primary key auto_increment,
	curador varchar(20),
    area double,
	id_prod int,
	
	CONSTRAINT fk_arqprod FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
    
);
	
create table Arte(
	id_arte int not null primary key auto_increment,
	tecnica varchar(20),
	id_prod int,
	
	CONSTRAINT fk_arteprod FOREIGN KEY (id_prod) REFERENCES produto(id_prod)	
);

create table Livro(
	id_livro int not null primary key auto_increment,
	editora varchar(20),
	edicao int,
	biografia varchar(200),
	id_prod int,
	
	CONSTRAINT fk_livroprod FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
);

create table arq_tem_livro(
	id_arq int,
	id_livro int,
	
	CONSTRAINT fk_arqlivro FOREIGN KEY (id_arq) REFERENCES arquitetura(id_arq),
	CONSTRAINT fk_livroarq FOREIGN KEY (id_livro) REFERENCES livro(id_livro)
);

create table arq_tem_arte(
	id_arq int,
	id_arte int,
	
	CONSTRAINT fk_arqarte FOREIGN KEY (id_arq) REFERENCES arquitetura(id_arq),
	CONSTRAINT fk_artearq FOREIGN KEY (id_arte) REFERENCES arte(id_arte)
);

create table img_path(
	id_img int not null primary key auto_increment,
	path_img varchar (1000),
	desc_img varchar(50),
	id_prod int,
	
	CONSTRAINT fk_imgproduto FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
	
);

create table txt_path(
	id_txt int not null primary key auto_increment,
	path_txt varchar(1000),
	id_prod int,
	
	CONSTRAINT fk_txtproduto FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
	
);

insert into usuario(login, senha)
values("userteste", MD5("usersenha"));

insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Museu do Amanhã", "Santiago Calatrava", "O Museu do Amanhã é um museu construído no município do Rio de Janeiro, no Brasil.
 O prédio, projeto do arquiteto espanhol Santiago Calatrava, 
 foi erguido ao lado da Praça Mauá, na zona portuária (mais precisamente no Píer Mauá).
 Sua construção teve o apoio da Fundação Roberto Marinho e teve o custo total de cerca de 230 milhões de reais.
 O edifício foi inaugurado em 17 de dezembro de 2015 com a presença da então presidente do Brasil Dilma Rousseff
 e recebeu cerca de 25 mil visitantes em seu primeiro final de semana de funcionamento.
 O antigo píer desativado passou a abrigar uma construção pós-moderna, orgânica e sustentável que, atualmente,
 é um ícone da identidade local e cultural da cidade do Rio de Janeiro.
 A proposta da instituição é ser um museu de artes e ciências,
 além de contar com mostras que alertam sobre os perigos das mudanças climáticas,
 da degradação ambiental e do colapso social. O edifício conta com espinhas solares que se movem ao longo da claraboia,
 projetadas para adaptar-se às mudanças das condições ambientais.
 A exposição principal é majoritariamente digital e foca em ideias ao invés de objetos.
 O museu tem parcerias com importantes universidades brasileiras e instituições científicas
 globais e coleta de dados em tempo real sobre o clima e a população de agências espaciais e das Nações Unidas.
 A instituição também tem consultores de várias áreas, como astronautas, cientistas sociais e climatologistas.
 ", "arquitetura", "construção moderna", "Rio de Janeiro", 2020, 1);

insert into arquitetura(curador, area, id_prod)
values("curadorteste", 10000.50, 1);

insert into arte(tecnica, id_prod)
values("tecnica", 1);

insert into livro(editora, edicao, biografia, id_prod)
values("editorateste", 1, "biografiateste", 1);

insert into arq_tem_arte(id_arq, id_arte)
values(1, 1);

insert into arq_tem_livro(id_arq, id_livro)
values(1, 1);

insert into img_path(path_img, desc_img, id_prod)
values("imgs/imagem.jpg","descricao", 1);

insert into txt_path(path_txt, id_prod)
values("txt/texto.txt", 1);