package com.restapp.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Arte {
	
	private Integer id_arte;
	private String categoria;
	private String titulo;
	private String tipo;
	private String autor;
	private String material;
	private String tecnica;
	private Integer ano;
	private String descricao;
	
	private Arquitetura arq;
	
	public Arte(){
		
	}
	
	public Arte(String categoria, String titulo, String tipo, String autor, String material,
			String tecnica, Integer ano, String descricao) {

		this.categoria = categoria;
		this.titulo = titulo;
		this.tipo = tipo;
		this.autor = autor;
		this.material = material;
		this.tecnica = tecnica;
		this.ano = ano;
		this.descricao = descricao;
	}

	public Integer getId_arte() {
		return id_arte;
	}

	public void setId_arte(Integer id_arte) {
		this.id_arte = id_arte;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Arquitetura getArq() {
		return arq;
	}

	public void setArq(Arquitetura arq) {
		this.arq = arq;
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
		return "Arte [id_arte=" + id_arte + ", categoria=" + categoria + ", titulo=" + titulo + ", tipo=" + tipo + ", autor="
				+ autor + ", material=" + material + ", tecnica=" + tecnica + ", descricao=" + descricao + "]";
	}
		
}
