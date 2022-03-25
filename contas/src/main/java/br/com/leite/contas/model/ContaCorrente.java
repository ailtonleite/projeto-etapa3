package br.com.leite.contas.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbcontacorrente")
public class ContaCorrente extends Conta{
	
	public ContaCorrente() {
		super();
	}
	
	public ContaCorrente(int numero) {
		super();
	}
	
	@Override
	public void sacar(double valor) {
		if(valor <= getSaldo()) {
			super.sacar(valor);
		}
	}
	
	@Override
	public void depositar(double valor) {
		super.depositar(valor);
	}
}
