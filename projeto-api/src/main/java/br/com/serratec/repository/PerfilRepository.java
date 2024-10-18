package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
}
