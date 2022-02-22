package com.starking.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.vendas.entities.Produto;
import com.starking.vendas.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void buscarTodos(Long id) {
		this.produtoRepository.findById(id);
	}

	public List<Produto> findAll(){
		return this.produtoRepository.findAll();
	}
		
	@Transactional
	public Produto salvar(Produto produto) {
		return this.produtoRepository.save(produto);
	}

	@Transactional(readOnly = true)
	public void deletar(Long id) {
		this.produtoRepository.deleteById(id);
	}
}
