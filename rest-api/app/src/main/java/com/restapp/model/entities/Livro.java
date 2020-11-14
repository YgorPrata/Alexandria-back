package com.restapp.model.entities;

import java.util.List;

public class Livro extends Produto {
	
	private Integer id_livro;
	private String editora;
	private Integer edicao;
	private String biografia;

	
	public Livro(){
		
	}

	public Livro(String titulo, String autor, String descricao, String categoria, String tipo, String localidade, Integer ano, List<Img> img, User id_user, String editora,
			Integer edicao, String biografia) {
		super(titulo, autor, descricao, categoria, tipo, localidade, ano, img, id_user);
		this.editora = editora;
		this.edicao = edicao;
		this.biografia = biografia;
	}

	public Integer getId_livro() {
		return id_livro;
	}

	public void setId_livro(Integer id_livro) {
		this.id_livro = id_livro;
	}


	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_livro == null) ? 0 : id_livro.hashCode());
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
		Livro other = (Livro) obj;
		if (id_livro == null) {
			if (other.id_livro != null)
				return false;
		} else if (!id_livro.equals(other.id_livro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Livro [id_livro=" + id_livro + ", editora=" + editora + ", edicao=" + edicao + ", biografia="
				+ biografia + ", toString()=" + super.toString() + "]";
	}

	

	
		
	
	
	
	
	
}
