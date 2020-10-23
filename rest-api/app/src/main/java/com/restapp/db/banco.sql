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
    descricao varchar(1000),	
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
values("tituloteste", "autorteste", "descricaoteste", "catgoriateste", "tipoteste", "localidadeteste", 2020, 1);

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