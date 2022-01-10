package com.gillianocampos.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gillianocampos.curso.entities.Category;
import com.gillianocampos.curso.entities.Order;
import com.gillianocampos.curso.entities.Product;
import com.gillianocampos.curso.entities.User;
import com.gillianocampos.curso.entities.enums.OrderStatus;
import com.gillianocampos.curso.repositories.CategoryRepository;
import com.gillianocampos.curso.repositories.OrderRepository;
import com.gillianocampos.curso.repositories.ProductRepository;
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

	@Autowired
	// injeçao de dependencia do categoryRepository vc deve fazer para todos
	// repository
	private CategoryRepository categoryRepository;
	
	@Autowired
	// injeçao de dependencia do ProductRepository vc deve fazer para todos
	// repository
	private ProductRepository productRepository;

	
	
	
	
	// run executado quando a aplicação for inciada
	// colocou null no id pq ele é incrementado
	@Override
	public void run(String... args) throws Exception {

		// instanciando alguns users
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123457");

		// instanciando alguns pedidos
		// estou passando o id que banco o banco vai gerar, o instante é so chamar
		// Instant.parse passando o formato iso8601 e ..
		// u1 que é o usuario user fazendo a associação
		// essa hora é 3 horas atrasada ao utc
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		// instanciar Categorias
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		//instanciar Produtos
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		
		// para salvar no banco estes objetos chama o userepository.saveAll(lista de
		// objetos)
		// userepository que acessa os dados
		// para criar lista usa arrays.asList
		// depois de rodar conferir no navegador http://localhost:8080/h2-console/ que
		// ´e o nome que esta no arquivo application-test.properties
		// quando rodar da pra ver os comandos sql no console create,insert etc
		userRepository.saveAll(Arrays.asList(u1, u2));

		// salvar o pedido passando as listas o1,o2,o3
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
				
		// salvar a categoria passando as listas cat1,cat2,cat3
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		// salvar o produto passando as listas p1,p2,p3,p4,p5
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
	
				
		
		//para fazer associação entre as tabelas product e category exemplos
		//p1.getCategories().add(cat2) acessa a categoria de p1 e adiciona o elemento da categoria2 fazendo assim a associação entre product e category
		 p1.getCategories().add(cat2);
		 //outros exemplos
		 p2.getCategories().add(cat1);
		 p2.getCategories().add(cat3);
		 p3.getCategories().add(cat3);
		 p4.getCategories().add(cat3);
		 p5.getCategories().add(cat2);
		//na hora de salvar fica tudo certinho no modelo relacional
		
		 //salvar as associações que fiz acima basta chamar de novo o repository
		 productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		 
		 
		// agora roda e vai no localhost:8080/h2-console e atualiza pra ver a tabela se
		// inseriu
	}

}
