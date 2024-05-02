package com.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaliacao.entities.Turma;
import com.avaliacao.service.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/turma")
public class TurmaController {

	private final TurmaService turmaService;

	@Autowired
	public TurmaController(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Turma>> buscarTurmaPorNome(@PathVariable String nome){
		List<Turma> turma = turmaService.buscaTurmaPorNome(nome);
		return ResponseEntity.ok(turma);
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Turma>> buscarTurmaPorDescricao(@PathVariable String descricao){
		List<Turma> turma = turmaService.buscaTurmaDescricao(descricao);
		return ResponseEntity.ok(turma);
	}

	@GetMapping("/nome/{nome}/descricao/{descricao}")
	public ResponseEntity<List<Turma>> buscarTurmaPorNomeEDescricao(@PathVariable String nome,@PathVariable  String descricao){
		List<Turma> turma = turmaService.buscaTurmaPorNomeEDescricao(nome, descricao);
		return ResponseEntity.ok(turma);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> buscaTurmaControlId(@PathVariable Long id) {
		Turma turma = turmaService.buscaTurmaId(id);
		if (turma != null) {
			return ResponseEntity.ok(turma);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Turma>> buscaTodosTurmaControl() {
		List<Turma> Turma = turmaService.buscaTodosTurma();
		return ResponseEntity.ok(Turma);
	}

	@PostMapping
	public ResponseEntity<Turma> salvaTurmaControl(@RequestBody @Valid Turma turma) {
		Turma salvaTurma = turmaService.salvaTurma(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaTurma);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Turma> alterarTurmaControl(@PathVariable Long id, @RequestBody @Valid Turma turma) {
		Turma alterarTurma = turmaService.alterarTurma(id, turma);
		if (alterarTurma != null) {
			return ResponseEntity.ok(turma);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Turma> apagaTurmaControl(@PathVariable Long id) {
		boolean apagar = turmaService.apagarTurma(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
