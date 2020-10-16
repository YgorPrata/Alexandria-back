drop database if exists alexandria;
create database alexandria;
use alexandria;

create table Arquitetura(
	id_arq int not null primary key auto_increment,
	localidade varchar(20),
	ano int	
);
	
create table Arte(
	id_arte int not null primary key auto_increment,
	tecnica varchar(20),
	ano int,
	id_arq int
 
	
	/*CONSTRAINT FK_arquitetura FOREIGN KEY (id_arq)
	REFERENCES Arquitetura(id_arq)*/
	
);

create table Livro(
	id_livro int not null primary key auto_increment,
	editora varchar(20),
	edicao int,
	biografia varchar(200),
	ano int,
	id_arq int
	
	/*CONSTRAINT FK_arquitetura2 FOREIGN KEY (id_arq)
	REFERENCES Arquitetura(id_arq)*/
	
);

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
	id_arq int,
	id_arte int,
	id_livro int,
	id_user int,
	
	CONSTRAINT fk_produtoarq FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_produtolivro FOREIGN KEY (id_livro) REFERENCES Livro(id_livro),
	CONSTRAINT fk_produtoarte FOREIGN KEY (id_arte) REFERENCES Arte(id_arte),
	CONSTRAINT fk_produtouser FOREIGN KEY (id_user) REFERENCES Usuario(id_user)
	
	
);

create table img_path(
	id_img int not null primary key auto_increment,
	path_img varchar (1000),
	desc_img varchar(50),
	/*id_arq int,
	id_livro int,
	id_arte int,*/
	id_prod int,
	
	CONSTRAINT fk_imgproduto FOREIGN KEY (id_prod) REFERENCES Produto(id_prod)
	
	/*CONSTRAINT fk_arquiteturaximg FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_livroximg FOREIGN KEY (id_livro) REFERENCES Livro(id_livro),
	CONSTRAINT fk_arteximg FOREIGN KEY (id_arte) REFERENCES Arte(id_arte)*/	
);

create table txt_path(
	id_txt int not null primary key auto_increment,
	path_txt varchar(1000),
	/*id_arq int,
	id_livro int,
	id_arte int,*/
	id_prod int,
	
	CONSTRAINT fk_txtproduto FOREIGN KEY (id_prod) REFERENCES Produto(id_prod)
	
	/*CONSTRAINT fk_arquiteturaxtxt FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_livroxtxt FOREIGN KEY (id_livro) REFERENCES Livro(id_livro),
	CONSTRAINT fk_artextxt FOREIGN KEY (id_arte) REFERENCES Arte(id_arte)*/
	
);

create table contemlivarq(
	id_contemlivarq int not null primary key auto_increment,
	id_arq int,
	id_livro int,
	
	CONSTRAINT fk_arquiteturalivro FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_livroarquitetura FOREIGN key (id_livro) REFERENCES Livro(id_livro)
);

create table comtemartearq(
	id_contemartearq int not null primary key auto_increment,
	id_arq int,
	id_arte int,
	
	CONSTRAINT fk_arquiteturaarte FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_artearquitetura FOREIGN key (id_arte) REFERENCES Arte(id_arte)
);

insert into usuario(login, senha)
values("userteste", MD5("usersenha"));

insert into produto(titulo, autor, descricao, categoria, tipo, id_arq, id_arte, id_livro, id_user)
values("tituloteste", "autorteste", "descricaoteste", "Arquitetura", "tipoteste", null, null, null, null);

insert into produto(titulo, autor, descricao, categoria, tipo, id_arq, id_arte, id_livro, id_user)
values("tituloteste", "autorteste", "descricaoteste", "Livro", "tipoteste", null, null, null, null);

insert into produto(titulo, autor, descricao, categoria, tipo, id_arq, id_arte, id_livro, id_user)
values("tituloteste", "autorteste", "descricaoteste", "Livro", "tipoteste", null, null, null, null);

insert into arquitetura(localidade, ano)
values("localidadeteste", 2020);

insert into arte(tecnica, ano)
values("tecnica", 2020
);

insert into livro(editora, edicao, biografia, ano)
values("editorateste", 1, "biografiateste", 2020
);

insert into img_path(path_img, desc_img)
values("imgs/imagem.jpg","descricao");

insert into txt_path(path_txt)
values("txt/texto.txt");

/*drop database if exists alexandria;
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
	id_arq int
 
	
	/*CONSTRAINT FK_arquitetura FOREIGN KEY (id_arq)
	REFERENCES Arquitetura(id_arq)*/
	
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
	id_arq int
	
	/*CONSTRAINT FK_arquitetura2 FOREIGN KEY (id_arq)
	REFERENCES Arquitetura(id_arq)*/
	
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

create table contemlivarq(
	id_contemlivarq int not null primary key auto_increment,
	id_arq int,
	id_livro int,
	
	CONSTRAINT fk_arquiteturalivro FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_livroarquitetura FOREIGN key (id_livro) REFERENCES Livro(id_livro)
);

create table comtemartearq(
	id_contemartearq int not null primary key auto_increment,
	id_arq int,
	id_arte int,
	
	CONSTRAINT fk_arquiteturaarte FOREIGN KEY (id_arq) REFERENCES Arquitetura(id_arq),
	CONSTRAINT fk_artearquitetura FOREIGN key (id_arte) REFERENCES Arte(id_arte)
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
values("imgs/imagem.jpg","descricao");

insert into txt_path(path_txt)
values("txt/texto.txt");*/