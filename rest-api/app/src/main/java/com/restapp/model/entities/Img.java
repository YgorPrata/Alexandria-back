package com.restapp.model.entities;

public class Img {
	
	private Integer id_img;	
	private String path_img;
	private String desc_img;
	
	public static String abspathimg = "C:/Users/felip/OneDrive/Disciplinas - Cursos/Faculdade/2020.2/Projeto Final 2/PROJETOS/Upload/WebContent/imgs/";
	public static String pathimg = "imgs/";
	
	public Img() {

	}	

	public Img(Integer id_img, String path_img, String desc_img) {
		this.id_img = id_img;
		this.path_img = path_img;
		this.desc_img = desc_img;
	}

	
	public Integer getId_img() {
		return id_img;
	}



	public void setId_img(Integer id_img) {
		this.id_img = id_img;
	}
	
	
	public String getPath_img() {
		return path_img;
	}



	public void setPath_img(String path_img) {
		this.path_img = path_img;
	}



	public String getDesc_img() {
		return desc_img;
	}



	public void setDesc_img(String desc_img) {
		this.desc_img = desc_img;
	}
	
		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_img == null) ? 0 : id_img.hashCode());
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
		Img other = (Img) obj;
		if (id_img == null) {
			if (other.id_img != null)
				return false;
		} else if (!id_img.equals(other.id_img))
			return false;
		return true;

	}

	@Override
	public String toString() {
		return "Img [id_img=" + id_img + ", path_img=" + path_img + ", desc_img=" + desc_img + "]";
		}


	}

