package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByNome(String nome);

	Optional<Usuario> findByEmail(String email);

	
}
