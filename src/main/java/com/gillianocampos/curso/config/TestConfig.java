package com.gillianocampos.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gillianocampos.curso.entities.Order;
import com.gillianocampos.curso.entities.User;
import com.gillianocampos.curso.entities.enums.OrderStatus;
import com.gillianocampos.curso.repositories.OrderRepository;
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
	
	@Autowired
	private OrderRepository orderRepository;

	// run executado quando a aplicação for inciada
	//colocou null no id pq ele é incrementado
	@Override
	public void run(String... args) throws Exception {
		
		//instanciando alguns users
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123457");
		
		//instanciando alguns pedidos
		//estou passando o id que banco o banco vai gerar, o instante é so chamar Instant.parse passando o formato iso8601 e ..
		//u1 que  é o usuario user fazendo a associação
		//essa hora é 3 horas atrasada ao utc 
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WAITING_PAYMENT, u1); 
		
		//para salvar no banco estes objetos chama o userepository.saveAll(lista de objetos)
		//userepository que acessa os dados
		//para criar lista usa arrays.asList
		//depois de rodar conferir no navegador http://localhost:8080/h2-console/ que ´e o nome que esta no arquivo application-test.properties
		//quando rodar da pra ver os comandos sql no console create,insert etc
		userRepository.saveAll(Arrays.asList(u1,u2));
		
		//salvar o pedido passando as listas o1,o2,o3
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		//agora roda e vai no localhost:8080/h2-console e atualiza pra ver a tabela se inseriu
	}

}
