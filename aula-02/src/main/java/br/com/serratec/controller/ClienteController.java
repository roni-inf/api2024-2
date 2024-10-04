package br.com.serratec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import br.com.serratec.entity.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private static List<Cliente> clientes = new ArrayList<>();

	static {
		clientes.add(new Cliente(1, "Maria", "m@gmail.com", "Petrópolis"));
		clientes.add(new Cliente(2, "Igor", "i@gmail.com", "Petrópolis"));
		clientes.add(new Cliente(3, "Ana", "a@gmail.com", "Juiz de Fora"));
	}

	@GetMapping
	public List<Cliente> listar() {
		return clientes;
	}

	@GetMapping("{id}")
	public Cliente buscar(@PathVariable Integer id) {
		/*
		 * for (int i = 0; i < clientes.size(); i++) { if
		 * (clientes.get(i).getId().equals(id)) { return clientes.get(i); } } return
		 * null;
		 */
		return clientes.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente inserir(@RequestBody Cliente cliente) {
		clientes.add(cliente);
		return cliente;
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getId().equals(id)) {
				clientes.remove(i);
			}
		}
	}

	@PutMapping("{id}")
	public Cliente alterar(@PathVariable Integer id, @RequestBody Cliente c) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getId().equals(id)) {
				clientes.set(i, c);
				return c;
			}
		}
		return null;
	}

}
