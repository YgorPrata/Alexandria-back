package com.restapp.model.entities;

public class Arquitetura {
	
	
	private Integer id_arq;
	private String titulo;
	private String autor;
	private String descricao;
	private String categoria;
	private String tipo;
	private String localidade;
	private Integer ano;

		
	public Arquitetura() {
		
	}

	public Arquitetura(String titulo, String autor, String descricao, String categoria, String tipo, String localidade, Integer ano) {		
		this.titulo = titulo;
		this.autor = autor;
		this.descricao = descricao;
		this.categoria = categoria;
		this.tipo = tipo;
		this.localidade = localidade;
		this.ano = ano;
	}
	

	public Integer getId_arq() {
		return id_arq;
	}

	public void setId_arq(Integer id_arq) {
		this.id_arq = id_arq;
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

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_arq == null) ? 0 : id_arq.hashCode());
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
		Arquitetura other = (Arquitetura) obj;
		if (id_arq == null) {
			if (other.id_arq != null)
				return false;
		} else if (!id_arq.equals(other.id_arq))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arquitetura [id_arq=" + id_arq + ", titulo=" + titulo + ", autor=" + autor + ", descricao=" + descricao
				+ ", categoria=" + categoria + ", tipo=" + tipo + ", localidade=" + localidade + ", ano=" + ano + "]";
	}

	

	
	

	

	
	
	
}
