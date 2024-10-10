package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
