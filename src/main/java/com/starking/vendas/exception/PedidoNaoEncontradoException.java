package com.starking.vendas.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class PedidoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PedidoNaoEncontradoException() {
		super("Pedido Não encontrado");
	}

}
