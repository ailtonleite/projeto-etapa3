package br.com.leite.contas.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.leite.contas.dao.CorrenteDAO;
import br.com.leite.contas.model.ContaCorrente;
import br.com.leite.contas.util.ContaOperacao;

@RestController
public class ContaController {
	
	@Autowired
	private CorrenteDAO dao;
	
	@GetMapping("/contascorrente")
	public ResponseEntity<ArrayList<ContaCorrente>> obterContasCorrentes(){
		ArrayList<ContaCorrente> lista = (ArrayList<ContaCorrente>) dao.findAll();
		
		if(lista != null) {
			return ResponseEntity.ok(lista);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/contascorrente/{numero}")
	public ResponseEntity<ContaCorrente> obterContaCorrentePorNumero(@PathVariable int numero){
		ContaCorrente conta = dao.findById(numero).orElse(null);
		if(conta != null) {
			return ResponseEntity.ok(conta);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/contacorrente")
	public ResponseEntity<ContaCorrente> novaContaCorrente(@RequestBody ContaCorrente conta){
		try {
			ContaCorrente novaConta = dao.save(conta);
			return ResponseEntity.ok(novaConta);
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}
	
	@DeleteMapping("/deletecorrente/{numero}")
	public ResponseEntity<ContaCorrente> deletarConta(@PathVariable int numero){
		try{
			dao.deleteById(numero);
			return ResponseEntity.status(200).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@PutMapping("/sacar")
	public ResponseEntity<ContaCorrente> sacarValor(@RequestBody ContaOperacao conta) {
		ContaCorrente contaEncontrada = dao.findById(conta.getNumero()).orElse(null);
		if(contaEncontrada != null) {
			contaEncontrada.sacar(conta.getValor());
			dao.save(contaEncontrada);
			return ResponseEntity.status(200).build();
		}
		return ResponseEntity.status(400).build();
		
	}
	
	@PutMapping("/depositar")
	public ResponseEntity<ContaCorrente> depositarValor(@RequestBody ContaOperacao conta) {
		ContaCorrente contaEncontrada = dao.findById(conta.getNumero()).orElse(null);
		if(contaEncontrada != null) {
			contaEncontrada.depositar(conta.getValor());
			dao.save(contaEncontrada);
			return ResponseEntity.status(200).build();
		}
		return ResponseEntity.status(400).build();
	}
}
