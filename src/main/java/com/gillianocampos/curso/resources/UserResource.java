package com.gillianocampos.curso.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.curso.entities.User;
//essa classe disponibiliza um recusro web referente a entidade User apenas
//colocar esses controladores passando o caminho localhost:8080/users
//users é o nome do caminho roda a aplicaçao depois vai no navegador
@RestController //para falar que essa classe é um recurso web que é implementado pelo controlador rest
@RequestMapping(value = "/users") //para dar um nome pro recurso rest que sera o caminho no http
public class UserResource {
	
	//metodo para encontrar todos registros de usuarios
	//getmapping para o metodo responde o rest do tipo get do http
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Maria", "maria@gmail.com", "9999999", "12345");
		//metodo retorna a resposta com sucesso do usuario u que instanciei
		return ResponseEntity.ok().body(u);
	}
	
}
