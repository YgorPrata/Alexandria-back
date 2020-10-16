package com.restapp.model.entities;

public class Img {
	
	private Integer id_img;	
	private String path_img;
	private String desc_img;
	
	
	private Arquitetura arq;
	private Arte arte;
	private Livro livro;
	
	public Img() {

	}

	

	public Img(String path_img, String desc_img, Arquitetura arq, Arte arte, Livro livro) {
		this.path_img = path_img;
		this.desc_img = desc_img;
		this.arq = arq;
		this.arte = arte;
		this.livro = livro;
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

	public Arquitetura getArq() {
		return arq;
	}



	public void setArq(Arquitetura arq) {
		this.arq = arq;
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
	public String toString() {
		return "Img [id_img=" + id_img + ", path_img=" + path_img + ", desc_img=" + desc_img + ", arq=" + arq
				+ ", arte=" + arte + ", livro=" + livro + "]";
	}


	



	
	
	
	
}
