package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.serratec.entity.Foto;
import br.com.serratec.entity.Usuario;


public interface FotoRepository extends JpaRepository<Foto, Long>{
	public Optional<Foto> findByUsuario(Usuario usuario);
	
	@Query(nativeQuery = true, value = "select * from foto where foto.id_usuario=:idUsuario and foto.id=:idFoto")
	public Foto buscarFotoUsuario(Long idUsuario, Long idFoto);

}
