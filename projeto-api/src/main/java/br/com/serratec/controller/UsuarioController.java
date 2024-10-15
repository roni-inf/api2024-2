package br.com.serratec.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.entity.Usuario;
import br.com.serratec.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody Usuario usuario){
			UsuarioRequestDTO dto = service.inserir(usuario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
	}
	
}
