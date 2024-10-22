package br.com.serratec.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.entity.Foto;
import br.com.serratec.entity.Usuario;
import br.com.serratec.service.FotoService;
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
	
	@Autowired
	private FotoService fotoService;

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
	public ResponseEntity<Object> inserir(@RequestPart UsuarioRequestDTO dto, @RequestPart MultipartFile file)throws IOException {
		UsuarioResponseDTO dtoResponse = service.inserir(dto, file);
		return ResponseEntity.created(null).body(dtoResponse);
	}
	
	@GetMapping("{id}")
	public  ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable Long id){
		return ResponseEntity.ok(service.buscar(id));
	}


	@GetMapping("{idUsuario}/fotos/{idFoto}")
	public ResponseEntity<byte[]> buscarFuncionarioFotos(@PathVariable Long idUsuario, @PathVariable Long idFoto) {
		Foto foto = fotoService.buscarFotoUsuario(idUsuario, idFoto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);

	}

}
