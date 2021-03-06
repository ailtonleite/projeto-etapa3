package br.com.leite.contas.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero")
	private int numero;
	
	@Column(name = "titular", length = 200, nullable = false)
	private String titular;
	
	@Column(name = "saldo")
	private double saldo;
	
	
	public Conta() {
		super();
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getTitular() {
		return titular;
	}

	public double getSaldo() {
		return saldo;
	}
	
	public void sacar(double valor) {
		if(valor >= 0) {
			saldo -= valor;
		}
	}
	
	public void depositar(double valor) {
		if(valor >= 0) {
			saldo += valor;
		}
	}
}
