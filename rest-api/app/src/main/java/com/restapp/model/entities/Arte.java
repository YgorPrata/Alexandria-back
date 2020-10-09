package com.restapp.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Arte {
	
	private Integer id_arte;
	private String titulo;
	private String autor;
	private String descricao;
	private String categoria;
	private String tipo;
	private String tecnica;
	private Integer ano;
	
	private Integer id_arq;
	
	public Arte(){
		
	}

	public Arte(String titulo, String autor, String descricao, String categoria, String tipo, String tecnica,
			Integer ano) {
		this.titulo = titulo;
		this.autor = autor;
		this.descricao = descricao;
		this.categoria = categoria;
		this.tipo = tipo;
		this.tecnica = tecnica;
		this.ano = ano;
	}

	public Integer getId_arte() {
		return id_arte;
	}

	public void setId_arte(Integer id_arte) {
		this.id_arte = id_arte;
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

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
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
		result = prime * result + ((id_arte == null) ? 0 : id_arte.hashCode());
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
		Arte other = (Arte) obj;
		if (id_arte == null) {
			if (other.id_arte != null)
				return false;
		} else if (!id_arte.equals(other.id_arte))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arte [id_arte=" + id_arte + ", titulo=" + titulo + ", autor=" + autor + ", descricao=" + descricao
				+ ", categoria=" + categoria + ", tipo=" + tipo + ", tecnica=" + tecnica + ", ano=" + ano + ", id_arq="
				+ id_arq + "]";
	}
	
		
}
