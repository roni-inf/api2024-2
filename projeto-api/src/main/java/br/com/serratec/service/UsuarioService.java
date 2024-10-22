package br.com.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.config.MailConfig;
import br.com.serratec.dto.EnderecoResponseDTO;
import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.entity.Endereco;
import br.com.serratec.entity.Usuario;
import br.com.serratec.entity.UsuarioPerfil;
import br.com.serratec.exception.EmailException;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.EnderecoRepository;
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

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private FotoService fotoService;

	public List<UsuarioResponseDTO> listar() {
		List<Usuario> usuarios = repository.findAll();
		List<UsuarioResponseDTO> dtos = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			dtos.add(new UsuarioResponseDTO(usuario));
		}
		return dtos;
	}

	@Transactional
	public UsuarioResponseDTO inserir(UsuarioRequestDTO dto, MultipartFile file) throws IOException {
		Optional<Usuario> u = repository.findByEmail(dto.getEmail());
		if (u.isPresent()) {
			throw new EmailException("Email existente!");
		}
		dto.setSenha(encoder.encode(dto.getSenha()));

		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());

		Endereco endereco = enderecoRepository.findByCep(dto.getCep());
		if (endereco != null) {
			usuario.setEndereco(endereco);
		} else {
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				endereco = new Endereco();
				endereco.setCep(enderecoViaCep.get().getCep());
				endereco.setBairro(enderecoViaCep.get().getBairro());
				endereco.setLocalidade(enderecoViaCep.get().getLocalidade());
				endereco.setLogradouro(enderecoViaCep.get().getUf());
				enderecoRepository.save(endereco);
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}

		}

		usuario = repository.save(usuario);

		for (UsuarioPerfil up : dto.getUsuarioPerfis()) {
			up.setUsuario(usuario);
			System.out.println(perfilService.buscar(up.getPerfil().getId()));
			up.setPerfil(perfilService.buscar(up.getPerfil().getId()));
			up.setDataCriacao(LocalDate.now());
		}

		usuarioPerfilRepository.saveAll(dto.getUsuarioPerfis());

		fotoService.inserir(usuario, file);

		// mailConfig.sendEmail(usuario.getEmail(), "Confirmação de cadastro",
		// usuario.toString());
		return adicionarUrlFoto(usuario);
	}

	private UsuarioResponseDTO adicionarUrlFoto(Usuario usuario) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuarios/{id}/foto")
				.buildAndExpand(usuario.getId()).toUri();

		UsuarioResponseDTO dto = new UsuarioResponseDTO();
		dto.setNome(usuario.getNome());
		dto.setEmail(usuario.getEmail());
	//	dto.setUrl(uri.toString());

		return dto;
	}

	public UsuarioResponseDTO buscar(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			return new UsuarioResponseDTO(usuario.get());
		}
		throw new ResourceNotFoundException("Usuário não encontrado");
	}
}
