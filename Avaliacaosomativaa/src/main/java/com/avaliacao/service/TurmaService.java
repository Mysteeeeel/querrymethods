package com.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.entities.Turma;
import com.avaliacao.repository.TurmaRepository;

@Service
public class TurmaService {

	private final TurmaRepository turmaRepository;

	@Autowired
	public TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}

	//Query Method
    public List<Turma> buscaTurmaPorNome(String nome){
    	return turmaRepository.findByNome(nome);
    }
    
    public List<Turma> buscaTurmaDescricao(String descricao){
    	return turmaRepository.findByDescricao(descricao);
    }
    
    public List<Turma> buscaTurmaPorNomeEDescricao(String nome ,String descricao ){
    	return turmaRepository.findByNomeAndDescricao(nome, descricao);
    }

	public  List<Turma> buscaTodosTurma(){
		return turmaRepository.findAll();
	}

	public Turma buscaTurmaId(Long id) {
		Optional <Turma> Turma = turmaRepository.findById(id);
		return Turma.orElse(null);
	}

	public Turma salvaTurma(Turma Turma){
		return turmaRepository.save(Turma);
	}

	public Turma alterarTurma(Long id, Turma alterarTurma) {
		Optional <Turma> existeTurma = turmaRepository.findById(id);
		if (existeTurma.isPresent()) {
			alterarTurma.setId(id);
			return turmaRepository.save(alterarTurma);
		}
		return null;
	}

	public boolean apagarTurma (Long id) {
		Optional <Turma> existeTurma = turmaRepository.findById(id);
		if (existeTurma.isPresent()) {
			turmaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
