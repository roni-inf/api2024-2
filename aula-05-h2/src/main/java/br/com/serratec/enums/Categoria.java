package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumException;

public enum Categoria {
	SUV, HATCH, SEDAN, CONVERSIVEL, PICAPE, ESPORTIVO, COUPE;

	@JsonCreator
	public static Categoria verificar(String valor) {
		for (Categoria c : Categoria.values()) {
			if (c.name().equals(valor)) {
				return c;
			}
		}
		throw new EnumException("Categoria inválida");
	}
}
