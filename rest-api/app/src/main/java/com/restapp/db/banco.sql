drop database if exists alexandria;
create database alexandria;
use alexandria;

create table Arquitetura(
	id_arq int not null primary key auto_increment,
	titulo varchar(20),
    autor varchar(20), 
    descricao varchar(1000),
    categoria varchar (20),
	tipo varchar(20),
	localidade varchar(20),
	ano int	
);
	
create table Arte(
	id_arte int not null primary key auto_increment,
	titulo varchar(20),
	autor varchar(20),
    descricao varchar(1000),
    categoria varchar(20),
	tipo varchar(20),
	tecnica varchar(20),
	ano int,
	id_arq int,
 
	
	CONSTRAINT FK_arquitetura FOREIGN KEY (id_arq)
	REFERENCES Arquitetura(id_arq)
	
);

create table Livro(
	id_livro int not null primary key auto_increment,
    titulo varchar(50),
	autor varchar(20),
    descricao varchar(1000),	
    categoria varchar(20),
	tipo varchar(20),
	editora varchar(20),
	edicao int,
	biografia varchar(200),
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

insert into arquitetura(titulo, autor, descricao, categoria, tipo, localidade, ano)
values("tituloteste", "autorteste", "descricaoteste", "categoriateste", "tipoteste", "localidadeteste", 2020);

insert into arte(titulo, autor, descricao, categoria, tipo, tecnica, ano)
values("tituloteste", "autorteste", "descricaoteste", "categoriateste", "tipoteste", "tecnica", 2020
);

insert into livro(titulo, autor, descricao, categoria, tipo, editora, edicao, biografia, ano)
values("tituloteste", "autorteste", "descricaoteste", "categoriateste", "tipoteste", "editorateste", 1, "biografiateste", 2020
);

insert into img_path(path_img, desc_img)
values("c:/temp/imgs/imagem.jpg","descricao");

insert into txt_path(path_txt)
values("c:/temp/txt/texto.txt");