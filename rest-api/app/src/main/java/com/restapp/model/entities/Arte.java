package com.restapp.model.entities;

import java.util.List;

public class Arte extends Produto{
	
	private Integer id_arte;
	private String tecnica;
		
	public Arte(){
		
	}

	public Arte(String titulo, String autor, String descricao, String categoria, String tipo, String localidade,
			Integer ano, List<Img> img, User id_user, String tecnica) {
		super(titulo, autor, descricao, categoria, tipo, localidade, ano, img, id_user);
		this.tecnica = tecnica;
	}



	public Integer getId_arte() {
		return id_arte;
	}

	public void setId_arte(Integer id_arte) {
		this.id_arte = id_arte;
	}

	
	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
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
		return "Arte [id_arte=" + id_arte + ", tecnica=" + tecnica + ", toString()=" + super.toString() + "]";
	}

	
	
		
}
