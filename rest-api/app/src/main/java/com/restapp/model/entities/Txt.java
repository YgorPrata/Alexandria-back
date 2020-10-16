package com.restapp.model.entities;

public class Txt {
	
	private Integer id_txt;
	private String path_txt;	
	private Integer id_arq;
	private Integer id_arte;
	private Integer id_livro;
	
	
	
	public Txt() {

	}

	public Txt(String path_txt) {
		this.path_txt = path_txt;		
	}

	public Integer getId_txt() {
		return id_txt;
	}

	public void setId_txt(Integer id_txt) {
		this.id_txt = id_txt;
	}

	public String getPath_txt() {
		return path_txt;
	}

	public void setPath_txt(String path_txt) {
		this.path_txt = path_txt;
	}

	public Integer getId_arq() {
		return id_arq;
	}

	public void setId_arq(Integer id_arq) {
		this.id_arq = id_arq;
	}

	public Integer getId_arte() {
		return id_arte;
	}

	public void setId_arte(Integer id_arte) {
		this.id_arte = id_arte;
	}

	public Integer getId_livro() {
		return id_livro;
	}

	public void setId_livro(Integer id_livro) {
		this.id_livro = id_livro;
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
		Txt other = (Txt) obj;
		if (id_arq == null) {
			if (other.id_arq != null)
				return false;
		} else if (!id_arq.equals(other.id_arq))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Txt [id_txt=" + id_txt + ", path_txt=" + path_txt + ", id_arq=" + id_arq + ", id_arte=" + id_arte
				+ ", id_livro=" + id_livro + "]";
	}

	
	
	
	
	
}
