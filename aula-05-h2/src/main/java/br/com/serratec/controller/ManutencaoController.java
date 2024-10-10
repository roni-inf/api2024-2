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

import br.com.serratec.entity.Manutencao;
import br.com.serratec.repository.ManutencaoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

	@Autowired
	private ManutencaoRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Manutencao inserir(@Valid @RequestBody Manutencao m) {
		return repository.save(m);
	}

	@PutMapping("{id}")
	public ResponseEntity<Manutencao> alterarManutencao(@PathVariable Long id, @Valid @RequestBody Manutencao m) {
		if (repository.existsById(id)) {
			m.setId(id); // aqui ele vai fazer o put se não ele iria criar outro novo
			return ResponseEntity.ok(repository.save(m));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Manutencao> inserirVarios(@RequestBody List<Manutencao> manutencoes) {
		return repository.saveAll(manutencoes);
	}

	@GetMapping
	public List<Manutencao> listar() {
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
