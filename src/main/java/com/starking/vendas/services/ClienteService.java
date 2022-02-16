package com.starking.vendas.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public void buscarTodos(Long id) {
		this.clienteRepository.findById(id);
	}
	
	@Transactional
	public void salvar(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}

	@Transactional(readOnly = true)
	public void deletar(Long id) {
		this.clienteRepository.deleteById(id);
	}
}
