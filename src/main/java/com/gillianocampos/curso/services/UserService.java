package com.gillianocampos.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gillianocampos.curso.entities.User;
import com.gillianocampos.curso.repositories.UserRepository;

//vou implementar operação para buscar todos usuarios 
// tbm operação para buscar usuario por ID
//@component ele registra sua classe como componente do springboot e faz com que possa ser injetado automaticamente com o autowired no Userresource
//tem que usar o @component para o autoWired funcionar
//tem tambem o @repository para registra um repository e o @service para registrar um service
//vou usar o @service e apagar o @component para ficar semanticamente expressivo pois essa classe é serviço
@Service
public class UserService {
	
	//para fazer isso meu UserService tem que ter dependencia do UserRepository
	//autowired para que o springboot faça essa injeção de dependencia
	
	@Autowired
	private UserRepository repository; //injeçao de dependencia para o repository
	
	//criar metodo para retornar todos usuarios do banco de dados
	//import java.util.list e o entity.user
	public List<User> findAll(){
		//o repository tem varias operaçoes que desejar. vamos usar findAll abaixo
		//abaixo entao a camada de serviço repassa a chamada para o repository
		return repository.findAll();
	}

	//metodo para recuperar por id
	public User findById(Long id) {
		//o findbyid retornara um objeto optional entao cria variavel obj do tipo optional de User
		Optional<User> obj = repository.findById(id);
		//retorna obj.get a operacao get do optional retorna um obj do tipo user que estiver dentro do meu optional
		return obj.get();
	}

	//metodo para inserir um usuario
	public User insert(User obj) {
		//o save ja retorna o objeto salvo
		return repository.save(obj);
	}
	
	//metodo para deletar usuario passando id como paramentro
	public void delete(Long id) {
		//deleta no banco
		repository.deleteById(id);
	}
	
	//função para atualizar usuario cahamada updade que recebe id para indicar qual usuario irei atualizar e obj user contendo os dados para ser atualizado
	public User update(Long id, User obj) {
		//getOne ele instancia o usuario e não vai no banco aoenas deixr o objeto monitorado para trabalhar com ele
		//é melhor que findById que vai no banco
		User entity = repository.getOne(id);
		//para atualizar os dados do objeti entity e atualizar com os dados que veio do obj no parametro criei um metodo updateData
		updateData(entity,obj);
		//salvar no banco o entity
		return  repository.save(entity);
	}

	//metodo criado que atualiza os dados do entity com base no que chegou no parametro obj
	private void updateData(User entity, User obj) {
		// atualizar os dados do entity nao atualizo id nem senha
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
	
	//la no UserResources tem que atualizar a implementação do findAll
}
