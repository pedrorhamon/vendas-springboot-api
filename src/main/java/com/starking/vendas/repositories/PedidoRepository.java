package com.starking.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.vendas.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
