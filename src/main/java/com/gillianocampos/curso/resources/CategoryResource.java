package com.gillianocampos.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.curso.entities.Category;
import com.gillianocampos.curso.services.CategoryService;
//essa classe disponibiliza um recusro web referente a entidade Category apenas
//colocar esses controladores passando o caminho localhost:8080/users
//users é o nome do caminho roda a aplicaçao depois vai no navegador
//para falar que essa classe é um recurso web que é implementado pelo controlador rest
//para dar um nome pro recurso rest que sera o caminho no http
@RestController
@RequestMapping(value = "/categories") 
public class CategoryResource {
	
	//dependencia para o service
	//importar java.util.list e entities.Category
	@Autowired
	private CategoryService service;
	
	//metodo para encontrar todos registros de categorias
	//getmapping para o metodo responde o rest do tipo get do http
	//o metodo retorna uma lista de Category <List<Category>>
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		//fazer uma declaraçao+ de uma lista de categorias recebendo e chamando o service.findalll e esse servico vai chamar o repository , o banco
		List<Category> lista = service.findAll();
		//ResponseEntity.ok retorna a resposta do http e no corpo da resposta eu retorno a minha lista
		return ResponseEntity.ok().body(lista);
	}
	
	//implementar tbm um endpoint ou metodo para buscar a categoria por id
	//getmapping pq tbm vai ser uma requição do tipo get passando o id 
	@GetMapping(value = "/{id}")
	//ResponseEntity<Category> o metodo nao retorna lista agora é so um category por id
	//Long id vai receber o parametro que chegara da url para isso usar o anotattion @PathVariable
	public ResponseEntity<Category> findById(@PathVariable Long id){
		//chama o service passando o id que chegou como parametro na requisição
		Category obj = service.findById(id);
		
		//ResponseEntity.ok() para indicar que teve sucesso
		//body(obj) passando no corpo o meu objeto objCategoryService.java
		return ResponseEntity.ok().body(obj);
	}
		
}
