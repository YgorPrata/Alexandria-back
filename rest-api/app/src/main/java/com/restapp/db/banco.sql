drop database if exists alexandria;
create database alexandria;
use alexandria;

create table usuario(
	id_user int not null primary key auto_increment,
	nome varchar (100),
	login varchar(100) not null,
	senha varchar(1000) not null,
	role varchar(10) not null
);

create table produto(
	id_prod int not null primary key auto_increment,
	titulo varchar(100),
	autor varchar(100),
    descricao varchar(10000),	
    categoria varchar(20),
	tipo varchar(100),
    localidade varchar(100),
	ano int,
	id_user int,
	
	CONSTRAINT fk_produtouser FOREIGN KEY (id_user) REFERENCES usuario(id_user)
);

create table Arquitetura(
	id_arq int not null primary key auto_increment,
	curador varchar(100),
    area double,
	id_prod int,
	
	CONSTRAINT fk_arqprod FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
    
);
	
create table Arte(
	id_arte int not null primary key auto_increment,
	tecnica varchar(100),
	id_prod int,
	
	CONSTRAINT fk_arteprod FOREIGN KEY (id_prod) REFERENCES produto(id_prod)	
);

create table Livro(
	id_livro int not null primary key auto_increment,
	editora varchar(100),
	edicao int,
	biografia varchar(10000),
	id_prod int,
	
	CONSTRAINT fk_livroprod FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
);


create table img_path(
	id_img int not null primary key auto_increment,
	path_img varchar (1000),
	desc_img varchar(1000),
	id_prod int,
	
	CONSTRAINT fk_imgproduto FOREIGN KEY (id_prod) REFERENCES produto(id_prod)
	
);


insert into usuario(nome, login, senha, role)
values("Felipe Savacini", "felipeadmin", SHA2('admin',256), "admin");

insert into usuario(nome, login, senha, role)
values("Ygor Pratta", "ygoradmin", SHA2('admin',256), "admin");




/*ARQUITETURA 1*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Museu do Amanhã", "Santiago Calatrava", "O Museu do Amanhã é um museu construído no município do Rio de Janeiro, no Brasil.
 O prédio, projeto do arquiteto espanhol Santiago Calatrava, foi erguido ao lado da Praça Mauá,
 na zona portuária (mais precisamente no Píer Mauá).
 Sua construção teve o apoio da Fundação Roberto Marinho e teve o custo total de cerca de 230 milhões de reais.
 O edifício foi inaugurado em 17 de dezembro de 2015 com a presença da então presidente do Brasil Dilma Rousseff e recebeu cerca
 de 25 mil visitantes em seu primeiro final de semana de funcionamento",
"arquitetura", "Moderno", "Rio de Janeiro, RJ - Brasil", 2015, 2);

insert into arquitetura(curador, area, id_prod)
values("Prefeitura do Rio de Janeiro", 15000.0, 1);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/museudoamanha1.jpg","Museu do Amanhã - Inauguração", 1);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/museudoamanha2.jpg","Museu do Amanhã com a Ponte Rio Niterói ao fundo.", 1);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/museudoamanha3.jpg","Museu do Amanhã visto de cima", 1);


/*ARQUITETURA 2*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Igreja de Nossa Senhora da Candelária", "Antônio Martins da Palma e sua esposa, Leonor Gonçalves", "A Igreja de Nossa Senhora da Candelária surgiu em
 cumprimento de uma promessa feita por Antônio Martins da Palma e sua esposa, Leonor Gonçalves.
 O casal de espanhóis foi surpreendido por uma tempestade durante uma viagem marítima e invocou pela Santa de sua devoção,
 Nossa Senhora da Candelária, prometendo erguer uma capela no primeiro porto que chegasse a salvo. A promessa foi cumprida e,
 aportando no Rio de Janeiro, ergueram a capela. No dia 03 de junho de 1775, a Provedoria da Irmandade autorizou a construção de um grandioso templo em estilo barroco,
 a atual Igreja de Nossa Senhora da Candelária, no lugar da capela já deteriorada pela ação do tempo. Após 123 anos de construção,
 a Igreja foi inaugurada em 10 de julho de 1898. Ao longo de mais de dois séculos, a Igreja passou por reformas e revitalizações,
 sempre preservando os aspectos arquitetônicos originais e a tradição religiosa. Hoje no templo, além das missas e celebrações religiosas,
 são realizadas apresentações culturais do Projeto Candelária.",
"arquitetura", "Neoclassico", "Rio de Janeiro, RJ - Brasil", 1877, 1);

insert into arquitetura(curador, area, id_prod)
values("Não aplicável", 5000.0, 2);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/candelaria1.jpg","Igreja de Nossa Senhora da Candelária", 2);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/candelaria2.jpg","Detalhe em bronze dos portais de autoria de Teixeira Lopes", 2);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/candelaria3.jpg","Candelária em 2014", 2);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/candelaria4.jpg","Vista do altar-mor da igreja", 2);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/candelaria5.jpg","Vista do teto da cúpula da Candelária com pinturas de Zeferino da Costa e de seus ajudantes", 2);


/*ARQUITETURA 3*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Santa Sofia", "Isidoro de Mileto e Antêmio de Trales", "Construída em Constantinopla, entre 527 e 532, durante o reinado do Imperador Justiniano (527-565),
 a Basílica de Santa Sofia foi dedicada à Sabedoria de Deus e daí sua denominação latinizada de “Santa Sofia” e no grego “Hagia Sophia”. 
 A denominação de basílica relaciona-se ao fato do poder civil, representado pelo imperador (basileus em grego) ter ordenado a sua construção e tal gesto foi
 imortalizado no mosaico presente na basílica de San Vitale, em Ravena na Itália, onde o imperador Justiniano aparece acompanhado de seu séquito (militares e políticos)
 e entrega ao bispo Maximiano (bispo de Constantinopla) um recipiente que contém ouro, simbolizando o financiamento da igreja.",
"arquitetura", "Bizantino", "Istambul - Turquia", 2015, 2);

insert into arquitetura(curador, area, id_prod)
values("Não aplicável", 100000.0, 3);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/santasofia1.jpg","Santa Sofia, vista externa. Foto retirada em 2004.", 3);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/santasofia2.jpg","Interior de Santa Sofia", 3);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/santasofia3.jpg","Interior de Santa Sofia, com o mosaico da Virgem e o Menino ao fundo", 3);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/santasofia4.jpg","Interior de Santa Sofia, mostrando dois dos quatro medalhões instalados pelos Fossati no século XIX", 3);


/*ARQUITETURA 4*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Museu de Arte Moderna do Rio de Janeiro", "Affonso Eduardo Reidy", "O museu foi inaugurado em 1948,
 por iniciativa de um grupo de empresários presidido por Raymundo Ottoni de Castro Maia, como uma organização particular sem fins lucrativos,
 fruto do contexto cultural e econômico que o Brasil vivenciou no segundo pós-guerra, em que se observou a diversificação dos equipamentos culturais deste país,
 a aquisição de um valioso patrimônio artístico e a assimilação das correntes artísticas modernas. 
 Palco de diversos acontecimentos de grande relevância na vanguarda artística brasileira,
 o museu amealhou ao longo de sua história uma grande coleção de arte moderna e contemporânea altamente representativa.
 Parte dessa coleção foi perdida no trágico incêndio de 1978. Conserva hoje aproximadamente 15 mil obras de arte, sendo 6.600 da coleção própria e as demais,
 em regime de comodato, advindas da Coleção Gilberto Chateaubriand, desde 1993, e da coleção de fotografias de Joaquim Paiva.",
"arquitetura", "Moderno", "Rio de Janeiro, RJ - Brasil", 1948, 1);

insert into arquitetura(curador, area, id_prod)
values("Fernando Cocchiarale", 40000.0, 4);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/museuartemoderna1.jpg","Museu de Arte Moderna", 4);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/museuartemoderna2.jpg","Museu de Arte Moderna visto a noite", 4);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/museuartemoderna3.jpg","Vista do térreo com escada no início da sua construção", 4);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/museuartemoderna4.jpg","Jardim frontal do museu no início da sua construção", 4);





/*LIVRO 1*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Livro de catecismo", "Sophronius of Vratsa", "O Livro do Domingo é o primeiro livro publicado em búlgaro moderno.
 Ele foi escrito pelo bispo Sofronii, um membro da Paisii Hilandarski, fundadora do movimento renascentista búlgaro.
 É constituído de 96 sermões e destinava-se a servir como guia religioso,
 numa época em que a Bíblia ainda não havia sido traduzida do búlgaro antigo.",
"livro", "Religião", "Biblioteca do Congresso, Washington - EUA", 1806, 2);

insert into livro(editora, edicao, biografia, id_prod)
values("Não aplicável", 1, "Santo Sophronius de Vratsa, nascido Stoyko Vladislavov, era um clérigo búlgaro e uma
 das principais figuras do início do renascimento nacional búlgaro. Vladislavov nasceu na cidade de Kotel,
 no leste da Bulgária, em 1739, da família de um comerciante de gado. Seu pai, Vladislav, morreu em 1750, em Istambul.", 5);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/livrocatecismo1.jpg","Representação de várias figuras de acordo com suas crenças.", 5);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/livrocatecismo2.jpg","Selo representado pela Biblioteca do Congresso, identificando que é uma obra rara", 5);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/livrocatecismo3.jpg","Representação de uma figura divina em uma das artes presentes no livro", 5);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/livrocatecismo4.jpg","Representação escrita do idioma bulgaro em uma das páginas introdutórias do livro.", 5);


/*LIVRO 2*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Uma Carta de Cristóvão Colombo", "Cristóvão Colombo", "Após sua primeira viagem através do Atlântico,
 Colombo escreveu um breve relato sobre as ''ilhas da Índia além do Ganges''.
 Sua intenção era a de anunciar suas recentes descobertas e garantir apoio financeiro e político para uma outra viagem.
 A primeira edição da carta foi impressa em espanhol, em Barcelona, em abril de 1493. Um mês depois, em Roma,
 Stephan Plannck publicou uma tradução em latim. O preâmbulo de Plannck dava crédito a Fernando de Aragão por apoiar a expedição,
 porém omitia qualquer referência à Rainha Isabel. Plannck logo publicou uma edição corrigida que mencionava o papel de Isabel.
 Foi esta edição em latim que circulou amplamente e espalhou, por toda a Europa, a notícia das descobertas de Colombo.",
"livro", "Epístola - Geografia - História", "Biblioteca do Congresso, Washington - EUA", 1493, 2);

insert into livro(editora, edicao, biografia, id_prod)
values("Não aplicável", 1, "Cristóvão de Colombo foi um navegador e explorador italiano,
 responsável por liderar a frota que alcançou o continente americano em 12 de Outubro de 1492,
 sob as ordens dos Reis Católicos de Espanha, no chamado descobrimento da América", 6);
 
 insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/cristovaocolombo1.jpg","Página introdutória da epístole escrita por Cristóvão Colombo.", 6);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/cristovaocolombo2.jpg","Estrutura da Capa da Epístole", 6);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/cristovaocolombo3.jpg","Título dado a Epístole de Cristóvão Colombo", 6);


/*LIVRO 3*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("A Divina Comédia", "Dante Alighieri", "A divina comédia é um dos maiores clássicos da literatura universal.
 Escrito no século XIV, o poema épico de Dante Alighieri é considerado também um dos textos fundadores da língua italiana.
 Nele, o escritor apresenta uma jornada inesquecível pelo tormento infinito do Inferno e a árdua subida da montanha do
 Purgatório até o glorioso reino do Paraíso. Dante conseguiu fundir sátira,
 inteligência e paixão em uma alegoria cristã imortal sobre a busca da humanidade pelo autoconhecimento e pela transformação espiritual.",
"livro", "Poesia épica", "Bibliotecas Virtuais", 2017, 1);

insert into livro(editora, edicao, biografia, id_prod)
values("Nova Fronteira", 12, "Dante Alighieri foi um escritor, poeta e político florentino, nascido na atual Itália.
 É considerado o primeiro e maior poeta da língua italiana, definido como il sommo poeta.", 7);
 
 insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/adivinacomedia1.jpg","Box - A Divina Comédia.", 7);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/adivinacomedia2.jpg","A Divina Comédia - Inferno", 7);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/adivinacomedia3.jpg","A Divina Comédia - Purgatório", 7);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/adivinacomedia4.jpg","A Divina Comédia - Paraíso", 2);


/*LIVRO 4*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Guia Ilustrado de Cultivo e Tecelagem: Vida Rural na China", "Jiāo Bǐngzhēn", "Este livro é composto de 23 ilustrações
 de cultivo e 23 ilustrações de tecelagem, cada uma acompanhada por um poema.
 Segundo o ''Prefácio às ilustrações imperialmente comissionadas de cultivo e tecelagem''
 e o memorando formal de apresentação por Yan Yudun, os poemas foram inscritos pelo Imperador Kangxi. O pintor, Jiao Bingzhen, baseia-se,
 em grande parte, nas ''Ilustrações de Cultivo e Tecelagem'' feitas pelo pintor Lou Shou no início da dinastia Song do Sul,
 fazendo alterações para produzir estas imagens, que são obras-primas de arte, notáveis por sua vivacidade e habilidosa execução.",
"livro", "Agricultura - Arte - Desenho", "Biblioteca do Congresso, Washington - EUA", 1696, 1);

insert into livro(editora, edicao, biografia, id_prod)
values("Não Aplicável", 1, "Jiāo Bǐngzhēn, 1689-1726 era natural de Jining, Shandong, que se tornou um notável pintor e astrônomo.
 Na pintura, ele é notável como um dos primeiros pintores da dinastia Qing a ser influenciado pelo Ocidente.
 Ele também está entre os pintores de retratos e miniaturas mais importantes no início da Dinastia Qing.", 8);
 
insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/agriculturachinesa1.jpg","Representação de um grupo de pessoas reunidos interagindo.", 8);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/agriculturachinesa2.jpg","A parte escrita do livro. Por mais que sua maior composição seja de pinturas,
 também possuem partes escritas. ", 8);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/agriculturachinesa3.jpg","O trabalho manual no campo realizado por homens", 8);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/agriculturachinesa4.jpg","Demonstração da parte interna das moradias dos locais rurais", 8);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/agriculturachinesa5.jpg","Representação das mulheres efetuando tarefas distintas das impostas aos homens", 8);



/*ARTE 1*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("A Boba", "Anita Malfatti", "A composição A Boba é um dos quadros mais conhecidos e notáveis da pintora Anita Malfatti que,
 não se atrelando aos rigores da época, ousou tanto na temática quanto no tratamento pictórico e no uso das cores",
"arte", "Retrato", "Museu de Arte Contemporânea da Universidade de São Paulo", 1916, 2);

insert into arte(tecnica, id_prod)
values("Cubismo", 9);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/aboba1.jpg","A boba - Anita Malfatti", 9);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/aboba2.jpg","1915 - Desenho a carvão feito por Anita. Um suposto estudo para representar sua obra A Boba", 9);


/*ARTE 2*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Estátua de Dom Pedro I", "Louis Rochet", "O monumento foi erguido por iniciativa de Pedro II do Brasil,
 em homenagem à proclamação da Independência do país, com projeto do artista brasileiro João Maximiano Mafra.
 As esculturas em bronze foram executadas e fundidas em Paris por Louis Rochet. 
 No conjunto encontram-se representadas as províncias brasileiras à época,
 e os quatro grandes rios nacionais — Amazonas, Madeira, São Francisco e Paraná.
 Nas alegorias estão figurados indígenas e diversas espécies de animais — antas, tatus,
 tamanduás — assim como gárgulas douradas e diversos outros motivos decorativos.",
"arte", "Estátua", "Praça Tiradentes, Rio de Janeiro RJ - Brasil", 1862, 1);

insert into arte(tecnica, id_prod)
values("Cubismo", 10);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/dompedro1.jpg","Estátua equestre de D. Pedro I, Rio de Janeiro", 10);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/dompedro2.jpg","Estátua equestre de D. Pedro I: alegoria ao rio São Francisco", 10);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/dompedro3.jpg","Estátua equestre de D. Pedro I: alegorias aos rios Madeira e Amazonas", 10);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/dompedro4.jpg","Registro feito em 1862 por Manoel Banchieri", 10);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/dompedro5.jpg","Diário do Rio de Janeiro, 23 de outubro de 1854.", 3);


/*ARTE 3*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Pietà", "Michelangelo Buonarroti", "“Pietà” foi elaborada em mármore e é considerada a primeira grande escultura do renomado artista.
 Apresentando Jesus Cristo morto no colo da Virgem Maria, essa peça encanta pela beleza e perfeição.
 Trazendo a figura de Jesus em proporção menor que a representada por Maria, o artista quis expressar que o filho não esmagaria a sua mãe.
 Já Maria conta com uma expressão de sofrimento idealizada, além de apresentar uma pureza inigualável.
 Logo, essa é uma das esculturas mais famosas do Renascentismo.",
"arte", "Escultura", "Basilica de São Pedro - Vaticano.", 1499, 1);

insert into arte(tecnica, id_prod)
values("Mármore", 11);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/pieta1.jpg","Escultura fotografada na Basílica de São Pedro", 11);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/pieta2.jpg","Detalhes próximo ao rosto da escultura", 11);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/pieta3.jpg","Basilica de São Pedro passando por um processo de higienização, por causa da Pandemia de Covid-19. Escultura Pieta ao fundo", 11);


/*ARTE 4*/
insert into produto(titulo, autor, descricao, categoria, tipo, localidade, ano, id_user)
values("Cerâmica indígena de Medellín", "Henry Price", "Em suas aquarelas e desenhos,
 Henry Price (1819 a 1863) sempre retratou artefatos de uso cotidiano usados pelos povos indígenas da Colômbia.
 Aqui mostra-se uma cerâmica indígena da província de Medellín. Price era um pintor e músico britânico,
 tendo sido um dos relatores da Comisión Corográfica (Comissão Corográfica), um órgão incumbido de estudar a Geografia,
 os recursos naturais, a História natural, a cultura regional e a agricultura da República de Nova Granada (atuais Colômbia e Panamá).
 Ele nasceu em Londres, mas se mudou para Nova York com sua família quando ainda era um adolescente. Em 1843,
 casou-se com Eliza Castello Brandon, filha de David Castello Montefiori (1790 a 1882).
 Price e Castello provavelmente se conheceram em Nova York, para onde Castello, que também era originalmente de Londres,
 havia transferido suas atividades comerciais antes sediadas em Kingston, Jamaica. Henry e Eliza Price se estabeleceram em Bogotá,
 onde ele inicialmente trabalhou para Castello como contador. Com a fundação da Sociedad Filarmónica,
 uma instituição influente na sociedade bogotana,
 ele pôde estabelecer importantes contatos sociais que o levaram a um cargo de docência no Colegio del Espíritu Santo.
 Foi lá que ele conheceu alguns dos futuros membros da Comisión Corográfica, incluindo o pintor venezuelano Carmelo Fernández (1809 a 1887),
 o botanista José Jerónimo Triana (1828 a 1890) e o advogado e estadista Santiago Pérez Manosalbas (1830 a 1900).
 Price foi contratado para a terceira expedição, em 1852, rumo às províncias de Mariquita, Medellín, Córdoba,
 Antioquia e Cauca, sob a direção de Agustín Codazzi (1793 a 1859).
 Muitas das aquarelas e desenhos de Price de 1852 a 1855 retratam as paisagens, o povo, seus costumes e os artefatos colombianos.
 Essas obras são mantidas hoje na Biblioteca Nacional da Colômbia (Colección Comisión Corográfica).",
"arte", "Escultura", "Biblioteca Nacional da Colombia", 1852, 2);

insert into arte(tecnica, id_prod)
values("Cerâmica", 12);

insert into img_path(path_img, desc_img, id_prod)
values("/assets/images/files/ceramicaindigena1.jpg","Representação no Papel da Escultura.", 12);
