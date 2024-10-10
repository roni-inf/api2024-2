package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

}
