package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository repository;

	@GetMapping
	public List<Funcionario> listar() {
		return repository.findAll();
	}

	@GetMapping("/paginacao")
	public Page<Funcionario> listar(@PageableDefault(page = 0, size = 10,
	sort = "dataNascimento" , direction = Direction.ASC) Pageable pageable) {
		return repository.findAll(pageable);
		
	}
	
	@GetMapping("/salario")
	public Page<Funcionario> listarSalario(@RequestParam(defaultValue = "0") Double valorMinimo,
			@RequestParam(defaultValue = "0") Double valorMaximo,
			Pageable pageable) {
		//return repository.buscarSalario(valorMinimo, valorMaximo, pageable);
		return repository.findBySalarioBetween(valorMinimo, valorMaximo, pageable);
		
	}
	
	@GetMapping("/nome")
	public Page<Funcionario> listarNome(@RequestParam(defaultValue = "") String nome,
			Pageable pageable) {
		//return repository.buscarNome(nome, pageable);
		return repository.findByNomeContaining(nome, pageable);
		
	}
	
	
}
