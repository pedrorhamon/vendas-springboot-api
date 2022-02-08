package com.starking.vendas.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Transactional
	public void salvar(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}
	
	public void deletar(Long clienteId) {
		this.clienteRepository.deleteById(clienteId);
	}

}
