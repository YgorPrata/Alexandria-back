package com.restapp.model.entities;

public class Livro {
	
	private Integer id_livro;
	private Integer edicao;
	private Integer ano;
	private String tipo;
	private String editora;
	private String biografia;
	private String descricao;
	private String autor;
	private String titulo;
	private String categoria;
	private Integer id_arq;
	
	public Livro(){
		
	}
	
	

	public Livro(Integer id_livro, Integer edicao, Integer ano, String tipo, String editora, String biografia,
			String descricao, String autor, String titulo, String categoria) {
		this.id_livro = id_livro;
		this.edicao = edicao;
		this.ano = ano;
		this.tipo = tipo;
		this.editora = editora;
		this.biografia = biografia;
		this.descricao = descricao;
		this.autor = autor;
		this.titulo = titulo;
		this.categoria = categoria;
	}



	public Integer getId_livro() {
		return id_livro;
	}

	public void setId_livro(Integer id_livro) {
		this.id_livro = id_livro;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

	public Integer getId_arq() {
		return id_arq;
	}



	public void setId_arq(Integer id_arq) {
		this.id_arq = id_arq;
	}



	@Override
	public String toString() {
		return "Livro [id_livro=" + id_livro + ", edicao=" + edicao + ", ano=" + ano + ", tipo=" + tipo + ", editora=" + editora
				+ ", biografia=" + biografia + ", descricao=" + descricao + ", autor=" + autor + ", titulo=" + titulo + ", categoria=" + categoria + "]";
	}
		
	
	
	
	
	
}
