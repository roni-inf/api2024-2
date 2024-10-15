package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.entity.Usuario;
import br.com.serratec.exception.EmailException;
import br.com.serratec.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<UsuarioResponseDTO> listar() {
		List<Usuario> usuarios = repository.findAll();
		List<UsuarioResponseDTO> dtos = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			dtos.add(new UsuarioResponseDTO(usuario));
		}
		return dtos;
	}

	public UsuarioRequestDTO inserir(Usuario usuario) {
		Optional<Usuario> u = repository.findByEmail(usuario.getEmail());
		if (u.isPresent()) {
			throw new EmailException("Email existente!");
		}
		usuario.setSenha(encoder.encode(usuario.getSenha()));

		return new UsuarioRequestDTO(repository.save(usuario));
	}

}
