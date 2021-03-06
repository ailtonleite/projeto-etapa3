package br.com.leite.contas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbcontaespecial")
public class ContaEspecial extends Conta{
	
	@Column(name = "limite")
	private double limite;

	
	public ContaEspecial() {
		super();
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	@Override
	public void sacar(double valor) {
		if(getSaldo()+limite >= valor) {
			super.sacar(valor);
		}
	}
	
	@Override
	public void depositar(double valor) {
		super.depositar(valor);
	}
}
