package br.com.leite.contas.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.leite.contas.model.ContaEspecial;

public interface EspecialDAO extends CrudRepository<ContaEspecial, Integer>{

}
