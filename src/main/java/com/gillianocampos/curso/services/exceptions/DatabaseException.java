package com.gillianocampos.curso.services.exceptions;


//classe para lançar exceção quando dá erro de violação de integridade. ex: deletar um id1 que tem pedidos associados em outra tabela dara erro de violaçoa de integridade
//quando de esse erro lançaremos essa exção com a mesagem que queremos colocar aqui
//vai extender do RunTimeExceprion
public class DatabaseException extends RuntimeException {
	//serializable
	private static final long serialVersionUID = 1L;
	
	//construtor com string que vai receber uma mensagem e chamar o super da superclasse passando essa mensagem
	public DatabaseException(String msg) {
		super(msg);
	}

}
