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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Operation(summary = "Lista todos os usuários", description = "A resposta lista os dados dos usuários id, nome, cpf e email.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
			content = {@Content(schema = @Schema(implementation = Usuario.class), mediaType = "application/json")},
			description = "Retorna todos os usuários"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	
	@Operation(summary = "Insere um novo usuário", description = "A resposta retorna o nome e email.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Usuario.class), mediaType = "application/json")},
			description = "Usuário cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody Usuario usuario) {
		UsuarioRequestDTO dto = service.inserir(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
