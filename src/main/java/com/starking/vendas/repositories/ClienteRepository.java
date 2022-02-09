package com.starking.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.vendas.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
