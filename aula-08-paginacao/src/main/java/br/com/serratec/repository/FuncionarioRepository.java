package br.com.serratec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.serratec.entity.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	Page<Funcionario> findByNome(String nome, Pageable pageable);
	//select * from funcionario where funcionario.nome = nome
	
	//@Query(value = "SELECT * FROM FUNCIONARIO WHERE SALARIO BETWEEN :valorMinimo AND :valorMaximo",
	//		nativeQuery = true)
	//Page<Funcionario> buscarSalario(Double valorMinimo, Double valorMaximo, Pageable pageable);
	
	Page<Funcionario>findBySalarioBetween(Double valorMinimo, Double valorMaximo, Pageable pageable);
	
	//@Query(value = "SELECT * FROM FUNCIONARIO WHERE UPPER(NOME) LIKE UPPER(CONCAT('%',:nome,'%'))",
		//	nativeQuery = true)
	
	//Page<Funcionario> buscarNome(String nome, Pageable pageable);
	
	Page<Funcionario> findByNomeContaining(String nome, Pageable pageable);

}
