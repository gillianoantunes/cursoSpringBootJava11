package com.gillianocampos.curso.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gillianocampos.curso.services.exceptions.ResourceNotFoundException;

@ControllerAdvice // traz todas exceções para ser executado aqui nesta classe
public class ResourceExceptionHandler {

	//primeiro tratamento sera do ResourceNotFoundException que tratará buscas vazias por id
	//objeto de respota ReponseEntity com tipo StandardError que é a classe que criei com campos de erro
	//nome do metodo ResourceNotFound que recebe uma exceção do meu tipo personalizado que é minha classe ResourceNotFoundException e um objeto HttpServletRequest que chamarei de request
	// criar o metodo para tratar a exceção de id nao encontrado e usar a anotation @ExceptionHandler(ResourceNotFoundException.class) para que ele seja capaz de interceptar a requisição que deu exceção para cair ali para tratar e entre parentese o nome da exceção que estou interceptando
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,HttpServletRequest request) {
		// mensagem básica de erro para o usuário
		String error = "Resource não encontrado";
		// colocar o status de erro que é o 404 not found
		HttpStatus status = HttpStatus.NOT_FOUND;
		//instanciar um objeto do tipo StandardError que é classe que criamos para personalizar os erros do http
		//passando os parametros instante atual, status que fiz acima .value para passar pra inteiro, error é a msg que criei acima, a mensagem é a que veio no paramentro que recebi no caso e.getmessage() e o caminho é o do parametro request.getrequestURI
		StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());
		//retorna um responseEntity .status para retronar com codigo personalizado e o .body com corpo da resposta o err
		return ResponseEntity.status(status).body(err);
	}
}
