package com.starking.vendas.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public List<Cliente> buscarTodos(){
		return this.clienteRepository.findAll();
	}
	
	@Transactional
	public void salvar(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}
	
	public void deletar(Long id) {
		this.clienteRepository.deleteById(id);
	}
}
