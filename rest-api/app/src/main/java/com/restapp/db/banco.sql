drop database if exists alexandria;
create database alexandria;
use alexandria;

create table Arquitetura(
	id_arq int not null primary key auto_increment,
	nome varchar(20),
	categoria varchar(20),
	tipo varchar(20),
	autor varchar(20),
	material varchar(20),
	ano int,
	descricao varchar(200)
);
	
create table Arte(
	id_arte int not null primary key auto_increment,
	categoria varchar(20),
	titulo varchar(20),
	autor varchar(20),
	tipo varchar(20),
	material varchar(20),
	tecnica varchar(20),
	ano int,
	descricao varchar(200),
	id_arq int,
 
	
	CONSTRAINT FK_arquitetura FOREIGN KEY (id_arq)
	REFERENCES Arquitetura(id_arq)
	
);

create table Livro(
	id_livro int not null primary key auto_increment,
	categoria varchar(20),
	tipo varchar(20),
	autor varchar(20),
	editora varchar(20),
	edicao int,
	biografia varchar(200),
	descricao varchar(200),
	titulo varchar(50),
	ano int,
	id_arq int,
	
	CONSTRAINT FK_arquitetura2 FOREIGN KEY (id_arq)
	REFERENCES Arquitetura(id_arq)
	
);

create table img_path(
	id_img int not null primary key auto_increment,
	path_img varchar (1000),
	desc_img varchar(50),
	id_arq int,
	id_livro int,
	id_arte int,
	
	CONSTRAINT fk_arquiteturaximg FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_livroximg FOREIGN KEY (id_livro) REFERENCES Livro(id_livro),
	CONSTRAINT fk_arteximg FOREIGN KEY (id_arte) REFERENCES Arte(id_arte)	
);

create table txt_path(
	id_txt int not null primary key auto_increment,
	path_txt varchar(1000),
	id_arq int,
	id_livro int,
	id_arte int,
	
	CONSTRAINT fk_arquiteturaxtxt FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_livroxtxt FOREIGN KEY (id_livro) REFERENCES Livro(id_livro),
	CONSTRAINT fk_artextxt FOREIGN KEY (id_arte) REFERENCES Arte(id_arte)
);


insert into arte(categoria, titulo, autor, tipo, material, tecnica, ano, descricao)
values("categoriateste", "tituloteste",
		"autorteste", "tipoteste", "materialteste",
		"tecnicateste", 2020,
		"descricaoteste"
);

insert into livro(categoria, tipo, autor, editora, edicao, biografia, descricao, titulo, ano)
values("categoriateste", "tipoteste",
		"autorteste", "editorateste",
		1, "biografiateste", "descricaoteste", "tituloteste", 2020
);

insert into arquitetura(nome, categoria, tipo, autor, material, ano, descricao)
values("nometeste","categoriateste", 
		"tipoteste","autorteste",
		"materialteste", 2020, "descricaoteste"
);

insert into img_path(path_img, desc_img)
values("c:/temp/imgs/imagem.jpg","descricao");

insert into txt_path(path_txt)
values("c:/temp/txt/texto.txt");