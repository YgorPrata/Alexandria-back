package com.restapp.model.entities;

public class User {
	
	private Integer id_user;
	private String user;
	private String password;
	private String role;
	private String user_name;
	
	public User() {
		
	}
	
	/*construtor para cumprir com os cadastros dos produtos para relacionar o 
	  chave primaria id do usuário com a chave estrangeira definida*/
	public User(Integer id_user) {
		this.id_user = id_user;
	}
	
	/*construtor definido para representar o nome do usuário em suas publicacoes
	  criado para não passar valores nulos nas instanciações*/	 
	public User(String userName) {
		this.user_name = userName;
	}

	public User(Integer id_user, String user, String password, String role, String user_name) {
		this.id_user = id_user;
		this.user = user;
		this.password = password;
		this.role = role;
		this.user_name = user_name;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		User other = (User) obj;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", user=" + user + ", password=" + password + ", role=" + role
				+ ", user_name=" + user_name + "]";
	}

	
	
	
	
}