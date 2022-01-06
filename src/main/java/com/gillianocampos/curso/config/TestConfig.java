package com.gillianocampos.curso.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gillianocampos.curso.entities.User;
import com.gillianocampos.curso.repositories.UserRepository;


//para falar pro spring que esta classe é especifica de configuração @Configuration

//para falar pro spring que esta clase é uma configuração especifica para o perfil de teste
//o nome test tem que ser igual esta em application.properties
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
//essa classe vai popular o banco como teste
	// no spring a declaração de dependencia é automatico implicito
	// para spring associar uma instancia do userRepositoy em cima do testeconfig
	// colocar o @Autowired
	@Autowired
	private UserRepository userRepository;

	// run executado quando a aplicação for inciada
	//colocou null no id pq ele é incrementado
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123457");

		
		//para salvar no banco estes objetos chama o userepository.saveAll(lista de objetos)
		//userepository que acessa os dados
		//para criar lista usa arrays.asList
		//depois de rodar conferir no navegador http://localhost:8080/h2-console/ que ´e o nome que esta no arquivo application-test.properties
		//quando rodar da pra ver os comandos sql no console create,insert etc
		userRepository.saveAll(Arrays.asList(u1,u2));
	}

}
