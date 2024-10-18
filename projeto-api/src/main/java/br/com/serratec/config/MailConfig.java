package br.com.serratec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String para, String assunto, String texto) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("roni.schanuel@docente.firjan.senai.br");
		mail.setTo(para);
		mail.setSubject(assunto);
		mail.setText("Dados de cadastro do usuário:\n" + texto 
				+ "\n\n\nTurma 20 - Serratec-2024");
		javaMailSender.send(mail);
	}
}
