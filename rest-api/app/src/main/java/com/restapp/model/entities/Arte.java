package com.restapp.model.entities;

import java.util.Date;

public class Arte {
	
	private Integer id;
	private String categoria;
	private String titulo;
	private String tipo;
	private String autor;
	private String material;
	private String tecnica;
	private Date ano;
	private String descricao;
	
	public Arte(){
		
	}
	
	public Arte(Integer id, String categoria, String titulo, String tipo, String autor, String material,
			String tecnica, Date ano, String descricao) {
		this.id = id;
		this.categoria = categoria;
		this.titulo = titulo;
		this.tipo = tipo;
		this.autor = autor;
		this.material = material;
		this.tecnica = tecnica;
		this.ano = ano;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Arte [id=" + id + ", categoria=" + categoria + ", titulo=" + titulo + ", tipo=" + tipo + ", autor="
				+ autor + ", material=" + material + ", tecnica=" + tecnica + ", ano=" + ano
				+ ", descricao=" + descricao + "]";
	}
		
}
