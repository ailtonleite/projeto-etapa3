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

import br.com.leite.contas.dao.EspecialDAO;
import br.com.leite.contas.model.ContaEspecial;
import br.com.leite.contas.util.ContaOperacao;

@RestController
public class ContaEspecialController {
	@Autowired
	private EspecialDAO dao;
	
	@GetMapping("/contasespecial")
	public ResponseEntity<ArrayList<ContaEspecial>> obterContasEspeciais(){
		ArrayList<ContaEspecial> lista = (ArrayList<ContaEspecial>) dao.findAll();
		
		if(lista != null) {
			return ResponseEntity.ok(lista);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/contasespecial/{numero}")
	public ResponseEntity<ContaEspecial> obterContaEspecialPorNumero(@PathVariable int numero){
		ContaEspecial conta = dao.findById(numero).orElse(null);
		if(conta != null) {
			return ResponseEntity.ok(conta);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/contaespecial")
	public ResponseEntity<ContaEspecial> novaContaEspecial(@RequestBody ContaEspecial conta){
		try {
			ContaEspecial novaConta = dao.save(conta);
			return ResponseEntity.ok(novaConta);
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}
	
	
	@DeleteMapping("/deleteespecial/{numero}")
	public ResponseEntity<ContaEspecial> deletarContaEspecial(@PathVariable int numero){
		try{
			dao.deleteById(numero);
			return ResponseEntity.status(200).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/sacarespecial")
	public ResponseEntity<ContaEspecial> sacarValor(@RequestBody ContaOperacao conta) {
		ContaEspecial contaEncontrada = dao.findById(conta.getNumero()).orElse(null);
		if(contaEncontrada != null) {
			contaEncontrada.sacar(conta.getValor());
			dao.save(contaEncontrada);
			return ResponseEntity.status(200).build();
		}
		return ResponseEntity.status(400).build();
	}
	
	@PutMapping("/depositarespecial")
	public ResponseEntity<ContaEspecial> depositarValor(@RequestBody ContaOperacao conta) {
		ContaEspecial contaEncontrada = dao.findById(conta.getNumero()).orElse(null);
		if(contaEncontrada != null) {
			contaEncontrada.depositar(conta.getValor());
			dao.save(contaEncontrada);
			return ResponseEntity.status(200).build();
		}
		return ResponseEntity.status(400).build();
	}
}
