package com.gillianocampos.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gillianocampos.curso.entities.Category;
import com.gillianocampos.curso.repositories.CategoryRepository;

//vou implementar operação para buscar todas categorias
// tbm operação para buscar usuario por ID
//@component ele registra sua classe como componente do springboot e faz com que possa ser injetado automaticamente com o autowired no Categoryresource
//tem que usar o @component para o autoWired funcionar
//tem tambem o @repository para registra um repository e o @service para registrar um service
//vou usar o @service e apagar o @component para ficar semanticamente expressivo pois essa classe é serviço
@Service
public class CategoryService {
	
	//para fazer isso meu CategoryService tem que ter dependencia do CategoryRepository
	//autowired para que o springboot faça essa injeção de dependencia
	
	@Autowired
	private CategoryRepository repository; //injeçao de dependencia para o repository
	
	//criar metodo para retornar todos usuarios do banco de dados
	//import java.util.list e o entity.user
	public List<Category> findAll(){
		//o repository tem varias operaçoes que desejar. vamos usar findAll abaixo
		//abaixo entao a camada de serviço repassa a chamada para o repository
		return repository.findAll();
	}

	//metodo para recuperar por id
	public Category findById(Long id) {
		//o findbyid retornara um objeto optional entao cria variavel obj do tipo optional de Category
		Optional<Category> obj = repository.findById(id);
		//retorna obj.get a operacao get do optional retorna um obj do tipo user que estiver dentro do meu optional
		return obj.get();
	}
	//la no CategoryResources tem que atualizar a implementação do findAll
}
