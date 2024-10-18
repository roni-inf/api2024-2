package br.com.serratec.entity;

import java.time.LocalDate;

import br.com.serratec.entity.pk.UsuarioPerfilPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UsuarioPerfil {

	@EmbeddedId
	private UsuarioPerfilPK id = new UsuarioPerfilPK();
	
	private LocalDate dataCriacao;
	
	public UsuarioPerfil() {
	}

	public UsuarioPerfil(Usuario usuario,Perfil perfil, LocalDate dataCriacao) {
		this.id.setUsuario(usuario);
		this.id.setPerfil(perfil);
		this.dataCriacao = dataCriacao;
	}

	public void setUsuario(Usuario usuario) {
		id.setUsuario(usuario);
	}
	
	public Usuario getUsuario() {
		return id.getUsuario();
	}
	
	public void setPerfil(Perfil perfil) {
		id.setPerfil(perfil);
	}
	
	public Perfil getPerfil() {
		return id.getPerfil();
	}
	
	
	public UsuarioPerfilPK getId() {
		return id;
	}

	public void setId(UsuarioPerfilPK id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	

	
	
	
	
}
