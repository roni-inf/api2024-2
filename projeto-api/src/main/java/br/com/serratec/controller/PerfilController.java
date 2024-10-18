package br.com.serratec.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.entity.Perfil;
import br.com.serratec.service.PerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilService service;

	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody Perfil perfil) {
		perfil = service.inserir(perfil);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(perfil.getId())
				.toUri();
		return ResponseEntity.created(uri).body(perfil);
	}

}
