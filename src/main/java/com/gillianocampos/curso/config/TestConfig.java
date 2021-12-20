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
	// no spring a declaração de dependecia é automatico implicito
	// para spring associar uma instancia do userRepositoy em cima do testeconfig
	// colocar o @Autowired
	@Autowired
	private UserRepository userRepository;

	// executado quando a aplicação for inciada
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		
		//para salvar no banco estes objetos chama o userepository.saveAll(lista de objetos)
		userRepository.saveAll(Arrays.asList(u1,u2));
	}

}
