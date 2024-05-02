package com.avaliacao.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.avaliacao.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	List<Aluno> findByRenda(Double renda);
	List<Aluno> findByCidade(String cidade);
	/*List<Aluno> findByNome(@Param("nome") String nome);*/
	List<Aluno> findByRa(String ra);
	List<Aluno> findByCidadeAndRenda (String cidade, Double renda);
	
	
	@Query("SELECT a FROM Aluno WHERE a.nome = :nome")
	List<Aluno> findByNome(@Param("nome") String nome);
	
	@Query("SELECT a FROM Aluno WHERE a.nomeCompleto LIKE :nomeCompleto")
	List<Aluno> findByNomeLike(@Param("nomeCompleto") String nomeCompleto);
	
	@Query("SELECT from Aluno a JOIN a.turma t WHERE t.id = :turmaId")
	List<Aluno> findByTurmaId(@Param("turmaId") Long turmaId);
	
}