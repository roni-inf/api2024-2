package br.com.serratec.entity;

import java.util.UUID;

public class Cliente {
	private Integer id;
	private String nome;
	private String email;
	private String cidade;

	public Cliente(Integer id, String nome, String email, String cidade) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cidade = cidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
