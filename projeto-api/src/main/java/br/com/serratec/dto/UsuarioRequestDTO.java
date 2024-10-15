package br.com.serratec.dto;

import br.com.serratec.entity.Usuario;

public class UsuarioRequestDTO {
	private Long id;
	private String nome;
	private String email;

	public UsuarioRequestDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	
	
}
