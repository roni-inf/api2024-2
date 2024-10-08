package br.com.serratec.entity;

import org.springframework.stereotype.Component;

@Component
public class Exame {
	
	public Double calcularExame(Double valor) {
		return valor = valor * 1.05;
	}
	
}
