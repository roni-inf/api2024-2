package br.com.serratec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.entity.Perfil;
import br.com.serratec.repository.PerfilRepository;

@Service
public class PerfilService {
	@Autowired
	private PerfilRepository repository;

	public Perfil buscar(Long id) {
		Optional<Perfil> perfil = repository.findById(id);
		if (perfil.isPresent()) {
			return perfil.get();
		}
		return null;

	}

	public Perfil inserir(Perfil perfil) {
		return repository.save(perfil);
	}
}
