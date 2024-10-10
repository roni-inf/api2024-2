package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Servico;
import br.com.serratec.repository.ServicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

	@Autowired
	private ServicoRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico inserir(@Valid @RequestBody Servico s) {
		return repository.save(s);
	}

	@PutMapping("{id}")
	public ResponseEntity<Servico> alterarServico(@PathVariable Long id, @Valid @RequestBody Servico s) {
		if (repository.existsById(id)) {
			s.setId(id); // aqui ele vai fazer o put se não ele iria criar outro novo
			return ResponseEntity.ok(repository.save(s));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Servico> inserirVarios(@RequestBody List<Servico> servicos) {
		return repository.saveAll(servicos);
	}

	@GetMapping
	public List<Servico> listar() {
		return repository.findAll();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
