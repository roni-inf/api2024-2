package br.com.serratec.dto;

import br.com.serratec.entity.Usuario;

public class UsuarioResponseDTO {
	private String nome;
	private String email;
//	private String url;

	
	public UsuarioResponseDTO() {
	}
	

	public UsuarioResponseDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
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

//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}

}
