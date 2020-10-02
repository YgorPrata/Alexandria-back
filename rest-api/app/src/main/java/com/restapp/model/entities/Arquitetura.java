package com.restapp.model.entities;

import java.sql.Date;

import javax.json.bind.annotation.JsonbDateFormat;

public class Arquitetura {
	
	
	private Integer id_arq;
	private String categoria;
	private String nome;
	private String tipo;
	private String autor;
	private String material;
	private Integer ano;
	private String descricao;
	
	private String img_path;
	private String img_desc;
	private String txt_path;
	
	public Arquitetura() {
		
	}

	public Arquitetura(String nome, String categoria, String tipo, String autor,
			String material, Integer ano, String descricao) {
		this.nome = nome;		
		this.categoria = categoria;		
		this.tipo = tipo;
		this.autor = autor;
		this.material = material;
		this.ano = ano;
		this.descricao = descricao;

	}

	public Integer getId_arq() {
		return id_arq;
	}


	public void setId_arq(Integer id_arq) {
		this.id_arq = id_arq;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
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
	

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	

	public String getImg_desc() {
		return img_desc;
	}

	public void setImg_desc(String img_desc) {
		this.img_desc = img_desc;
	}

	public String getTxt_path() {
		return txt_path;
	}

	public void setTxt_path(String txt_path) {
		this.txt_path = txt_path;
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
		return "Arquitetura [id_arq=" + id_arq + ", categoria=" + categoria + ", nome=" + nome + ", tipo=" + tipo
				+ ", autor=" + autor + ", material=" + material + ", ano=" + ano + ", descricao="
				+ descricao + " img_path=" + img_path + " img_desc=" + img_desc +"]";
	}
	
	
}
