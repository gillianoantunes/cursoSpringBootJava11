package com.gillianocampos.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gillianocampos.curso.entities.Order;
import com.gillianocampos.curso.repositories.OrderRepository;

//vou implementar operação para buscar todos usuarios 
// tbm operação para buscar usuario por ID
//@component ele registra sua classe como componente do springboot e faz com que possa ser injetado automaticamente com o autowired no Orderresource
//tem que usar o @component para o autoWired funcionar
//tem tambem o @repository para registra um repository e o @service para registrar um service
//vou usar o @service e apagar o @component para ficar semanticamente expressivo pois essa classe é serviço
@Service
public class OrderService {
	
	//para fazer isso meu OrderService tem que ter dependencia do OrderRepository
	//autowired para que o springboot faça essa injeção de dependencia
	
	@Autowired
	private OrderRepository repository; //injeçao de dependencia para o repository
	
	//criar metodo para retornar todos usuarios do banco de dados
	//import java.util.list e o entity.user
	public List<Order> findAll(){
		//o repository tem varias operaçoes que desejar. vamos usar findAll abaixo
		//abaixo entao a camada de serviço repassa a chamada para o repository
		return repository.findAll();
	}

	//metodo para recuperar por id
	public Order findById(Long id) {
		//o findbyid retornara um objeto optional entao cria variavel obj do tipo optional de Order
		Optional<Order> obj = repository.findById(id);
		//retorna obj.get a operacao get do optional retorna um obj do tipo user que estiver dentro do meu optional
		return obj.get();
	}
	//la no OrderResources tem que atualizar a implementação do findAll
}
