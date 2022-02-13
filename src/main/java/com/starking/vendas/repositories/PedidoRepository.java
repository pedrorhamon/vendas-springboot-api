package com.starking.vendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByCliente(Cliente cliente);

}
