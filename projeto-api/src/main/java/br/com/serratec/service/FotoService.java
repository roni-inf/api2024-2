package br.com.serratec.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.entity.Foto;
import br.com.serratec.entity.Usuario;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.FotoRepository;

@Service
public class FotoService {
	@Autowired
	private FotoRepository repository;

	public Foto inserir(Usuario usuario, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setDados(file.getBytes());
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setUsuario(usuario);
		return repository.save(foto);
	}

	public Foto buscarPorIdUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		Optional<Foto> foto = repository.findByUsuario(usuario);
		if (foto.isPresent()) {
			return foto.get();
		}
		throw new ResourceNotFoundException("Usuário não encontrado");
	}

	public Foto buscarFotoUsuario(Long idUsuario, Long idFoto) {
		return repository.buscarFotoUsuario(idUsuario, idFoto);
	}

}
