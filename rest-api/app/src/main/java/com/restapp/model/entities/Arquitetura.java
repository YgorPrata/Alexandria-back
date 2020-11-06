package com.restapp.model.entities;

import java.util.List;

public class Arquitetura extends Produto{
	
	
	private Integer id_arq;
	private String curador;
	private Double area;
	private Arte arte;
	private Livro livro;
			
	public Arquitetura() {
		super();
	}

	public Arquitetura(String titulo, String autor, String descricao, String categoria, String tipo, String localidade,
			Integer ano, List<Img> img, String curador, Double area) {
		super(titulo, autor, descricao, categoria, tipo, localidade, ano, img);
		this.curador = curador;
		this.area = area;
		this.arte = arte;
		this.livro = livro;
	}


	public Integer getId_arq() {
		return id_arq;
	}

	public void setId_arq(Integer id_arq) {
		this.id_arq = id_arq;
	}


	public String getCurador() {
		return curador;
	}


	public void setCurador(String curador) {
		this.curador = curador;
	}


	public Double getArea() {
		return area;
	}


	public void setArea(Double area) {
		this.area = area;
	}


	public Arte getArte() {
		return arte;
	}


	public void setArte(Arte arte) {
		this.arte = arte;
	}


	public Livro getLivro() {
		return livro;
	}


	public void setLivro(Livro livro) {
		this.livro = livro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id_arq == null) ? 0 : id_arq.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
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
		return "Arquitetura [produto=" + super.toString() + ", id_arq=" + id_arq + ", curador=" + curador + ", area=" + area + ", arte=" + arte
				+ ", livro=" + livro + "]";
	}


	


	


	


	
	
	

	
	

	

	
	
	
}
