package br.com.idp.JoaoFonseca.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This is a class that represents an Author of the Topic/Reply.
 * 
 */

@Entity
public class Author {

	/**
	 * The id of the author.
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * The name of the author.
	 */
	private String name;
	
	/**
	 * The email of the author.
	 */
	private String email;
	
	/**
	 * The password of the author.
	 */
	private String password;
	
	
	public Author () {
	}
	
	/**
	 * This is constructor of author with the given Name, Email and Password.
	 */
	public Author(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
