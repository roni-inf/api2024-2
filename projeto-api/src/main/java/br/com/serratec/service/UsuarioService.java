package br.com.serratec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.config.MailConfig;
import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.entity.Usuario;
import br.com.serratec.entity.UsuarioPerfil;
import br.com.serratec.exception.EmailException;
import br.com.serratec.repository.UsuarioPerfilRepository;
import br.com.serratec.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private MailConfig mailConfig;
	
	@Autowired
	private PerfilService perfilService;

	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository;
	
	public List<UsuarioResponseDTO> listar() {
		List<Usuario> usuarios = repository.findAll();
		List<UsuarioResponseDTO> dtos = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			dtos.add(new UsuarioResponseDTO(usuario));
		}
		return dtos;
	}

	@Transactional
	public UsuarioResponseDTO inserir(UsuarioRequestDTO dto) {
		Optional<Usuario> u = repository.findByEmail(dto.getEmail());
		if (u.isPresent()) {
			throw new EmailException("Email existente!");
		}
		dto.setSenha(encoder.encode(dto.getSenha()));
		
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());

		usuario = repository.save(usuario);
		
		for (UsuarioPerfil up: dto.getUsuarioPerfis()) {
			up.setUsuario(usuario);
			System.out.println(perfilService.buscar(up.getPerfil().getId()));
			up.setPerfil(perfilService.buscar(up.getPerfil().getId()));
			up.setDataCriacao(LocalDate.now());
		}

		usuarioPerfilRepository.saveAll(dto.getUsuarioPerfis());
		
		// mailConfig.sendEmail(usuario.getEmail(), "Confirmação de cadastro",
		// usuario.toString());
		return new UsuarioResponseDTO(usuario);
	}

}
