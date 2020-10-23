package com.restapp.model.entities;

import java.util.List;

public abstract class Produto {
	
	private Integer id_prod;
	private String titulo;
	private String autor;
	private String descricao;
	private String categoria;
	private String tipo;
	private String localidade;
	private Integer ano;
	private List<Img> listimg;
	
	private Img img;

	
	public Produto() {
		
	}
	

	public Produto(String titulo, String autor, String descricao, String categoria, String tipo, String localidade, Integer ano, List<Img> listimg) {
		this.titulo = titulo;
		this.autor = autor;
		this.descricao = descricao;
		this.categoria = categoria;
		this.tipo = tipo;
		this.localidade = localidade;
		this.ano = ano;
		this.listimg = listimg;
	}

	
	public Integer getId_prod() {
		return id_prod;
	}


	public void setId_prod(Integer id_prod) {
		this.id_prod = id_prod;
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
	
	
	public List<Img> getListImg() {
		return listimg;
	}


	public void setListImg(List<Img> listimg) {
		this.listimg = listimg;
	}
	
	
	public Img getImg() {
		return img;
	}
	
	
	public void setImg(Img img) {
		this.img = img;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_prod == null) ? 0 : id_prod.hashCode());
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
		Produto other = (Produto) obj;
		if (id_prod == null) {
			if (other.id_prod != null)
				return false;
		} else if (!id_prod.equals(other.id_prod))
			return false;
		return true;
	}
	
	
	
}
