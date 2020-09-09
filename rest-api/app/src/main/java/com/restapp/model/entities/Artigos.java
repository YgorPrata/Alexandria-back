package com.restapp.model.entities;

public class Artigos {
	
	private Integer id;
	private String autor;
	private String titulo;
	private String assunto;
	
	public Artigos(){
		
	}
		
	public Artigos(Integer id, String titulo, String autor, String assunto) {
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.assunto = assunto;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	@Override
	public String toString() {
		return "Artigos [id=" + id + ", autor=" + autor + ", titulo=" + titulo + ", assunto=" + assunto + "]";
	}
	
	
	
	
}
