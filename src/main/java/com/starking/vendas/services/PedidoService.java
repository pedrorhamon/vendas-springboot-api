package com.starking.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.starking.vendas.entities.Pedido;
import com.starking.vendas.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscarTodos(Long id) {
		return this.pedidoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado"));
	}

	public List<Pedido> findAll(){
		return this.pedidoRepository.findAll();
	}
		
	@Transactional
	public  ResponseEntity<Pedido> salvar(Pedido pedido) {
		Pedido pedidoNew = this.pedidoRepository.save(pedido);
		if(pedido != null) {
			return ResponseEntity.ok(pedidoNew);
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional(readOnly = true)
	public void deletar(Long id) {
		this.pedidoRepository.deleteById(id);
	}


}
