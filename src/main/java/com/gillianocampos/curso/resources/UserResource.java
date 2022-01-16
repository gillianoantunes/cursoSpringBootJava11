package com.gillianocampos.curso.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	// dependencia para o service
	// importar java.util.list e entities.User
	@Autowired
	private UserService service;

	// metodo para encontrar todos registros de usuarios
	// getmapping para o metodo responde o rest do tipo get do http
	// o metodo retorna uma lista de User <List<User>>
	// esse @GetMapping faz a gente chamar no postman o caminho /users ele retorna
	// todos usarios conforme este metodo
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		// fazer uma declaraçao+ de uma lista de usuarios recebendo e chamando o
		// service.findalll e esse servico vai chamar o repository , o banco
		List<User> lista = service.findAll();
		// ResponseEntity.ok retorna a resposta do http e no corpo da resposta eu
		// retorno a minha lista
		return ResponseEntity.ok().body(lista);
	}

	// implementar tbm um endpoint ou metodo para buscar o usuario por id
	// getmapping pq tbm vai ser uma requição do tipo get passando o id
	// @GetMapping(value = "/{id}") seu eu chamar no postman o caminho /users e
	// incluir um barra/ com numero de um id de usuario ele retorna aquele usuario
	// especifico do id
	// esses endpoint serve para recuperar dados do banco de dados para inserir
	// usamos o @PostMapping
	@GetMapping(value = "/{id}")
	// ResponseEntity<User> o metodo nao retorna lista agora é so um user por id
	// Long id vai receber o parametro que chegara da url para isso usar o
	// anotattion @PathVariable
	public ResponseEntity<User> findById(@PathVariable Long id) {
		// chama o service passando o id que chegou como parametro na requisição
		User obj = service.findById(id);

		// ResponseEntity.ok() para indicar que teve sucesso
		// body(obj) passando no corpo o meu objeto objUserService.java
		return ResponseEntity.ok().body(obj);
	}

	// metodo para inserir usuario
	// endpoin para inserir no banco @PostMapping o endpoint recebe um objeto User
	// obj
	// @RequestBody para Json decerializado para objeto User do java..tem que por o
	// @RequestBody
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		// chamar o service que ja esta injetado e jogar pra dentro de obj
		obj = service.insert(obj);
		// esse uri é para o caminho que eu chamar /id que eu inserir
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// inserir o obj que acabei de inserir no banco de dados
		return ResponseEntity.created(uri).body(obj);
	}

	// assinar o metodo e a resposta é void pois não vai retornar nada
	// metodo chamara delete e chamar um Long oid para deletar
	// @PathVariable para o id ser reconhecido como uma varuavel da minha url
	// endpoint para deletar usamos @DeleteMapping(value = "/{id}") passando o id para pegar o id da url igual o get
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// chamar o service
		service.delete(id);
		// retornar a resposta noContent pq é uma resposta vazia e o codigo http de uma
		// resposta vazia é o 204 e no final chamar o .build
		return ResponseEntity.noContent().build();
	}

}
