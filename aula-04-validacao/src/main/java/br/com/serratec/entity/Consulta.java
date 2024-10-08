package br.com.serratec.entity;

import org.springframework.stereotype.Component;

@Component
public class Consulta {

	public Double calcularConsulta(Double valor) {
		return valor = valor * 1.10;
	}
}
