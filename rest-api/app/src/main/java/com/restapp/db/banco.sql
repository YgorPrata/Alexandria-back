drop database if exists alexandria;
create database alexandria;
use alexandria;

create table Artigos(
	id_artigos int primary key auto_increment,
	titulo varchar(20),
	autor varchar(20),
	assunto varchar(200)
);

create table Arquitetura(
	id_arq int primary key auto_increment,
	categoria varchar(20),
	nome varchar(20),
	tipo varchar(20),
	autor varchar(20),
	material varchar(20),
	data date,
	descricao varchar(200)
);
	
create table Arte(
	id_arte int primary key auto_increment,
	categoria varchar(20),
	titulo varchar(20),
	autor varchar(20),
	material varchar(20),
	tecnica varchar(20),
	data date,
	descricao varchar(200),
	id_arq int,
	
	CONSTRAINT FK_EntidadesxArquitetura FOREIGN KEY (id_arq)
	REFERENCES Arte(id_arte)
);

create table Livro(
	id_livro int primary key auto_increment,
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
	
	CONSTRAINT FK_EntidadesxArquitetura2 FOREIGN KEY (id_arq) 
	REFERENCES Livro(id_livro)
);


INSERT INTO Artigos(titulo, autor, assunto)
VALUES
	("tituloteste", "autorteste",
	"assuntoteste");

insert into arte(categoria, titulo, autor, material, tecnica, data, descricao, id_arq)
values("categoriateste", "tituloteste",
		"autorteste", "materialteste",
		"tecnicateste","2020/01/01",
		"descricaoteste", 1
);

insert into livro(categoria, tipo, autor, editora, edicao, biografia, descricao, titulo, ano, id_arq)
values("categoriateste", "tipoteste",
		"autorteste", "editorateste",
		1, "biografiateste", "descricaoteste", "tituloteste", 2020, 1
);

insert into arquitetura(categoria, nome, tipo, autor, material, data, descricao)
values("categoriateste", "nometeste",
		"tipoteste","autorteste",
		"materialteste", "2020/01/01", "descricaoteste"
);


	













