package com.restapp.model.entities;

import java.util.Date;

public class Arquitetura {
	
	private Integer id_arq;
	private String categoria;
	private String nome;
	private String tipo;
	private String autor;
	private String material;
	private Date data;
	private String descricao;
	
	public Arquitetura() {
		
	}

	public Arquitetura(Integer id_arq, String categoria, String nome, String tipo, String autor,
			String material, Date data, String descricao) {
		this.id_arq = id_arq;
		this.categoria = categoria;
		this.nome = nome;
		this.tipo = tipo;
		this.autor = autor;
		this.material = material;
		this.data = data;
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


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
				+ ", autor=" + autor + ", material=" + material + ", data=" + data + ", descricao="
				+ descricao + "]";
	}
	
	
}
