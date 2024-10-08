package br.com.serratec.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pagamento{
	@Autowired
	private Consulta consulta;
	@Autowired
	private Exame exame;
	
	
//	public Pagamento(Consulta consulta, Exame exame) {
//		this.consulta = consulta;
//		this.exame = exame;
//	}


	public Double calcularProcedimento(Double valorExame, Double valorConsulta) {
		return exame.calcularExame(valorExame) + consulta.calcularConsulta(valorConsulta);
	}
	
}
