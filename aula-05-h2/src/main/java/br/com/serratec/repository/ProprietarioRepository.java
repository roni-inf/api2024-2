package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Proprietario;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

}
