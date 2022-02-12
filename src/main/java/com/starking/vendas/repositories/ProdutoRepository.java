package com.starking.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.vendas.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
