drop database if exists alexandria;
create database alexandria;
use alexandria;

create table Artigos(
	codigo int auto_increment primary key,
	titulo varchar(20),
	autor varchar(20),
	assunto varchar(500)
);

INSERT INTO Artigos(titulo, autor, assunto)
VALUES
	("tituloteste", "autorteste","assuntoteste")
	



