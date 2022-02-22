package com.starking.vendas.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.starking.vendas.entities.Produto;
import com.starking.vendas.repositories.ProdutoRepository;
import com.starking.vendas.services.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/{id}")
	public Produto buscarPorId(@PathVariable Long id){
		return this.produtoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado"));
		
	}
	
	@GetMapping
	public List<Produto>  findAll(){
		 return this.produtoRepository.findAll();
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		Produto produtoNew = this.produtoService.salvar(produto);
		if(produtoNew != null) {
			return ResponseEntity.ok(produtoNew);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.produtoService.deletar(id);
	}
	

	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @RequestBody Produto produto) {
		BeanUtils.copyProperties(produto, id, "id");
		this.produtoService.salvar(produto);
	}
}
