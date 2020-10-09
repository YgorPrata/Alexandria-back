package com.restapp.model.entities;

public class Livro {
	
	private Integer id_livro;
	private String titulo;
	private String autor;
	private String descricao;
	private String categoria;
	private String tipo;
	private String editora;
	private Integer edicao;
	private String biografia;
	private Integer ano;
		
	private Integer id_arq;
	
	public Livro(){
		
	}

	public Livro(String titulo, String autor, String descricao, String categoria, String tipo, String editora,
			Integer edicao, String biografia, Integer ano) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.descricao = descricao;
		this.categoria = categoria;
		this.tipo = tipo;
		this.editora = editora;
		this.edicao = edicao;
		this.biografia = biografia;
		this.ano = ano;
	}

	public Integer getId_livro() {
		return id_livro;
	}

	public void setId_livro(Integer id_livro) {
		this.id_livro = id_livro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getId_arq() {
		return id_arq;
	}

	public void setId_arq(Integer id_arq) {
		this.id_arq = id_arq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_livro == null) ? 0 : id_livro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id_livro == null) {
			if (other.id_livro != null)
				return false;
		} else if (!id_livro.equals(other.id_livro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Livro [id_livro=" + id_livro + ", titulo=" + titulo + ", autor=" + autor + ", descricao=" + descricao
				+ ", categoria=" + categoria + ", tipo=" + tipo + ", editora=" + editora + ", edicao=" + edicao
				+ ", biografia=" + biografia + ", ano=" + ano + ", id_arq=" + id_arq + "]";
	}

	
		
	
	
	
	
	
}
