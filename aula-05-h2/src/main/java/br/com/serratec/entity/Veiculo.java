package br.com.serratec.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Preencha a placa")
	@Size(max = 7, min = 7)
	@Column(nullable = false, length = 7)
	private String placa;
	
	@NotBlank(message = "Preencha a marca")
	@Size(max = 40 )
	@Column(nullable = false, length = 40)
	private String marca;

	@NotBlank(message = "Preencha a modelo")
	@Size(max = 50 )
	@Column(nullable = false, length = 50)
	private String modelo;
	
	@Embedded
	private Caracteristica caracteristica;

	@OneToOne
	@JoinColumn(name = "id_proprietario")
	private Proprietario proprietario;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "veiculo")
	private List<Manutencao> manutencoes = new ArrayList<>();
	
	
	public List<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
	


	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	
	
	
	
	
}
