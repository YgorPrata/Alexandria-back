drop database if exists alexandria;
create database alexandria;
use alexandria;

create table usuario(
	id_user int not null primary key auto_increment,
	nome varchar (20),
	login varchar(20) not null,
	senha varchar(1000) not null,
	role varchar(10) not null
);

create table produto(
	id_prod int not null primary key auto_increment,
	titulo varchar(50),
	autor varchar(50),
    descricao varchar(10000),	
    categoria varchar(20),
	tipo varchar(20),
    localidade varchar(50),
	ano int,
	id_user int,
	
	CONSTRAINT fk_produtouser FOREIGN KEY (id_user) REFERENCES usuario(id_user)
);

create table Arquitetura(
	id_arq int not null primary key auto_increment,
	curador varchar(50),
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
	biografia varchar(10000),
	id_prod int,
	
	CONSTRAINT fk_livroprod FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
);


create table img_path(
	id_img int not null primary key auto_increment,
	path_img varchar (1000),
	desc_img varchar(50),
	id_prod int,
	
	CONSTRAINT fk_imgproduto FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
	
);


insert into usuario(nome, login, senha, role)
values("admin", "admin", SHA2('admin',256), "admin");

insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Museu do Amanhã", "Santiago Calatrava", "O Museu do Amanhã é um museu construído no município do Rio de Janeiro, no Brasil.",
"arquitetura", "construção moderna", "Rio de Janeiro", 2020, 1);

insert into arquitetura(curador, area, id_prod)
values("curadorteste", 10000.50, 1);

insert into arte(tecnica, id_prod)
values("tecnica", 1);

insert into livro(editora, edicao, biografia, id_prod)
values("editorateste", 1, "biografiateste", 1);

insert into img_path(path_img, desc_img, id_prod)
values("imgs/imagem.jpg","descricao", 1);