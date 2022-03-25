package br.com.leite.contas.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.leite.contas.model.ContaCorrente;

public interface CorrenteDAO extends CrudRepository<ContaCorrente, Integer>{

}
