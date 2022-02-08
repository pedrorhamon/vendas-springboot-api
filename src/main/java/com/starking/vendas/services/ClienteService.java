package com.starking.vendas.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Transactional
	public void salvar(@RequestBody Cliente cliente) {
		this.clienteRepository.save(cliente);
	}

}
