package com.starking.vendas.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.repositories.ClienteRepository;
import com.starking.vendas.services.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<Cliente> buscarTodos(@PathVariable Long id){
		 Optional<Cliente> cliente = this.clienteRepository.findById(id);
		 if(cliente.isPresent()) {			 
			 return ResponseEntity.ok(cliente.get());		 
		 }
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		Cliente clienteNew = this.clienteService.salvar(cliente);
		if(clienteNew != null) {
			return ResponseEntity.ok(clienteNew);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping
	public void deletar(@PathVariable Long id) {
		this.clienteService.deletar(id);
	}
	

	@PutMapping("/{id}")
	public void  atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		BeanUtils.copyProperties(cliente, id, "id");
		this.clienteService.salvar(cliente);
	}
}
