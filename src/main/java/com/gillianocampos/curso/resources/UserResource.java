package com.gillianocampos.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.curso.entities.User;
import com.gillianocampos.curso.services.UserService;
//essa classe disponibiliza um recusro web referente a entidade User apenas
//colocar esses controladores passando o caminho localhost:8080/users
//users é o nome do caminho roda a aplicaçao depois vai no navegador
//para falar que essa classe é um recurso web que é implementado pelo controlador rest
//para dar um nome pro recurso rest que sera o caminho no http
@RestController
@RequestMapping(value = "/users") 
public class UserResource {
	
	//dependencia para o service
	//importar java.util.list e entities.User
	@Autowired
	private UserService service;
	
	//metodo para encontrar todos registros de usuarios
	//getmapping para o metodo responde o rest do tipo get do http
	//o metodo retorna uma lista de User <List<User>>
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		//fazer uma declaraçao+ de uma lista de usuarios recebendo e chamando o service.findalll e esse servico vai chamar o repository , o banco
		List<User> lista = service.findAll();
		//ResponseEntity.ok retorna a resposta do http e no corpo da resposta eu retorno a minha lista
		return ResponseEntity.ok().body(lista);
	}
	
	//implementar tbm um endpoint para buscar o usuario por id
	//getmapping pq tbm vai ser uma requição do tipo get passando o id 
	@GetMapping(value = "/{id}")
	//ResponseEntity<User> o metodo nao retorna lista agora é so um user por id
	//Long id vai receber o parametro que chegara da url para isso usar o anotattion @PathVariable
	public ResponseEntity<User> findById(@PathVariable Long id){
		//chama o service passando o id que chegou como parametro na requisição
		User obj = service.findById(id);
		
		//ResponseEntity.ok() para indicar que teve sucesso
		//body(obj) passando no corpo o meu objeto obj
		return ResponseEntity.ok().body(obj);
	}
		
}
