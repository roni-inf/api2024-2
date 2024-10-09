package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
