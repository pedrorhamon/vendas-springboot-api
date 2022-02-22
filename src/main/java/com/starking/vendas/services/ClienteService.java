package com.starking.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void buscarTodos(Long id) {
		this.clienteRepository.findById(id);
	}

	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
		
	@Transactional
	public Cliente salvar(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	@Transactional(readOnly = true)
	public void deletar(Long id) {
		this.clienteRepository.deleteById(id);
	}
}
