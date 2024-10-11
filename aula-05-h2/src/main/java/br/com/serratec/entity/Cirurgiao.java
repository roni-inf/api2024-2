package br.com.serratec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Cirurgiao extends Medico{
	@Column
	private String especialidadeCirgurgica;

	public String getEspecialidadeCirgurgica() {
		return especialidadeCirgurgica;
	}

	public void setEspecialidadeCirgurgica(String especialidadeCirgurgica) {
		this.especialidadeCirgurgica = especialidadeCirgurgica;
	}
	
	
}
