package com.gillianocampos.curso.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gillianocampos.curso.entities.pk.OrderItemPk;

//passos
// 1 colocar o atributos identificador que é chave primaria que sera do tipo da classe auxiliar que criamos chamada OrderItemPK
//2 colocar os outros atributos quantidade e preço
//preco coloca aqui tambem pq se eu alterar o preço do produto este preco aqui ficara guardando como historico da venda na tabela OrderItem
//3 construtor vazio e com argumentos obs: não colocar o id no construtor de argumentos pois vou colocar pra frente é o pedido e o produto
//4 get e set sem o id
//5 hashcode e qual somente de id pois ele que é a chave primaria
//serializable
//6 no construtor com argumento ira receber um Order order e um Product product
//ficara assim public OrderItem(Order order, Product product, Integer quantity, Double price)
//7 e fazer a atribuição para o id ficara assim
//id.setOrder(order);
//id.setProduct(product);
//8 fazer get e set do order e product
//fazer os mapeamentos @Entity @Table para jpa criar a tabela
//9 para dizer ao jpa que o id é chave primaria composta colocar o @EmbeddedId
//rodar e ver no banco de dados
//commit parte 1 dessa aula
//10associar order com orderitem entao na classe order colocar atributo uma coeção de itens private Set<orderItem> items = new HashSet<>()
//11colocar a anotation @OnetoMany um para muitos igual fizemos na clase user que tem varios pedido mapeado pelo client
//12 seguido do mapeamento (mappedBy = "id.order")pq na classe ordemitem eu tenho o id e .order pq id é do tipo classe auxiliar ordemitempk que tem atributo order
//13 fazer o get para esse atributo e agora meu pedido conhece os items dele
//14 instanciar os itens do pedido em testConfig
//15 salvar no banco mas antes tem que criar repository do OrderItemcopiar e colar userrepository e trocar por OrderItem a palavra User
//16/ fazer a injeção do repository na classe TestConfig @AutoWired
//17 rodar o banco e ver se inseriu e depois rodar no postman  localhost:8080/orders/1 vai dar loop eterno pedido chama itme  e itens chama pedido
//18 usar o @JsonIgnore no metodo getOrder na clase orderItem para cortar pq o getorder fica chamando o pedido associado ao item de pedido ai o pedido chama o item de pedido qe da o loop
//chama postman de novo localhost:8080/orders/1 e ele puxa todos pedidos e items certinhos
//19 commit
//associação do produto com ordemitem
//para pesquisar o produto vai ter que associar tbm com ordemitem passando por order
//da mesma forma que na classe ordem tem uma coleção de item que fiz farei tbm na classe product para percorrer
//20na classe product colocar colecão private Set<OrderItem> items = new HashSet<>(); uso Set e nao list para informar ao jpa que nao vou admitir repetiçoes do mesmo item.
//usei new instanciando para não ficar null
//21 fazer o mapeamento anotation no banco de dados igual fiz na classe order fica assim
//@OneToMany(mappedBy = id.product")  é id.product pq productitem tem atributo id e esse atributo id do tipoorderitempk tem product
//22 fazer o get para percorrer uma lista de order
//entao classe product fazer o get deste atributo items antes de hascode e equals
//23 na classe orderitem eu vou por um @JsonIgnore no meu getProductspara não acontecer aquele problema do loop eterno
//24 ir no postman localhost:8080/products/3 e verificar que pegou todos orders associados ao produto 3
//so que eu nao quero ao buscar produto apareça os pedidos e sim quando buscar pedidos apareça os itens do pedido e para cada item um produtos
//para inverter  retire o @jsonIgnote do getproduct na classe OrdemItem e na classe product coloque o @Json Ignore em getOrders
//25 no postman localhost:8080/products/3 agora so aparece os dados do produto
//26 no postman localhost:8080/orders/1 ai sim aparece o pedido o cliente, os itens de pedido e para cada item um produto associado a ele
//27 commit
//28 entidade pagamento associação onetoone associado ao pedido order
//um pagamento tem um pedido e 1 pedido tem zero ou 1 pagamento
//29 fazer classe payment com os atributos id tipo long e moment tipo instant
//30 fazer associação na classe payment private Order order; um pagemnto tem 1 pedido
//30 na classe order colocar tbm 1 pedido tem um pagamento private Payment payment e fazer gets e sets
//31 fazer os contrutores vazio e com argumentos agora na classe payment
//32 fazer gets e sets
//33  equals e hascode do id
//34 serializable
//35 fazer os mapeamoentos do jpa @Entity e @Table(name = "tb_payment") na classe payment
//36 mapeamento na chave primaria @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY) em cima do id
//37 associação quem é a classe dependente é o pagemento pois o pedido pode ter zero pagamento
// o pedido pode entrar no banco de dados sem ter pagamento pra ele independente
// na classe dependente no caso payment ir no atributo de associção no caso private Order order e colocar o mapemaneot assim:
//@OnetoOne e tbm o @MapsId na classe dependente
//38 na classe independente do outro lado no caso order colocar no atributo de asssocição no caso priavte Payment payment
// o nome entre aspas é o nome atributo do outro lado da associação que é a classe Payment no caso é order OnetoOne(mappedBy = "order", cascade = CascadeType.All
//cascade tipo all significa que no caso 1 para 1 as duas entidades tera mesmo id..ex: se o pedido for codigo 5 o pagamento sera codigo 5 tbm. mapenando para ter mesmo id é obrigato usar cascade
//39 ir na classe Testconfig e inserir um pagamento 2 horas depois do pedido o1 que foi 19:53
//no pedido que ja foi pago vou acrescentar Payment pi1 = new Payment(null,Instant.parse("2019-06-20T21:53:07z"),o1);
// //para voce salvar um objeto dependente no caso pagamento é dependente de order numa relação 1 para 1 voce nao vai chamar o repository do proprio objeto payment nao
//vou chamar meu pedido o1.setPayment(pay1) associando assim meu pedido o1 com pagamento pay1 
//o1.setPayment(pay1);
//agora eu salvo o pedido com pagamento associado e o jpa salva esse pagamento associado
//orderRepository.save(o1);
//commit

//40 fazer o metodo subtotal que esta no diagrama UML da classe OrdemItem e depois o metodo total que esta na classe Order
//na classe OrdemItem antes de equals e hashcode colocar o metodo getSubTotal tem que por palavra get para aparecer no resultado json
//na classe Order fazer o método getTotal( tem que usar palavra get) valor total é a soma dos subtotal dos item de pedido
//salvar e testar no postman localhost:8080/orders/1 vai dar um erro de loop eterno
//41 o pedido tem pagamento e o pagamento tem pedido colocar @JsonIgnore na classe Payment no atributo Order para cortar loop eterno
//salvar e rodar testar no postman localhost:8080/orders/1  e conferir os subtotais e total
//commit

//42 fazer inserção de usuarios , trabalhar no UserService e UserResource, entao no UserService
//ja temos la findById e findAll agora vamos criar a operação insert para salvar no banco de dados um usuario
// 43 no UserResource fazer o endpoint para inserir o resource é um controlador rest que tem caminho /"users
//usamos la a naotation @GetMapping para recuperar dados do banco de dados. para inserir usamos o @PostMapping que é um metodo do http
//fazendo o metodo para chamar o insert no UserService
//44 agora vamos enviar um objeto json representando os campos do usuario nome,email,phone e password
//no postman chamar o metodo post agora e nao get trocar e localhost:8080/orders/ e copia o objeto completo do material inserindo la no postman assim:
//inserir no post esse objeto json abaixo na aba Body sub aba raw e colocar tbm na aba final trocar text para json para comunicar ao backend que estou enviando os dados neste formato no postman
//na aba Headers na linha key digitar ContentType e value application/json e verificar se inseriu 
//{ 
//"name": "Bob Brown", 
//"email": "bob@gmail.com", 
//"phone": "977557755", 
//"password": "123456" 
//} 
// no post abaixo  retorna o codigo resposta 200 mas o melhor é voltar o 201 que é especifico do http que vc criou um novo recurso
//45 entao mudar no User|Resource ao inves de return ResponseEntity.ok mudar para ResponseEntity.created(aqui dentro espera um objeto URI criar antes



//continuando private OrdemItemRepository orderItemRepository
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
   
	//serializable
	private static final long serialVersionUID = 1L;

	//chave primaria id do tipo da classe auxiliar OrderItemPk e ela esta como null entao tem que instanciar com new  OrderItemPk();
	@EmbeddedId
	private OrderItemPk id = new OrderItemPk();
	
	private Integer quantity;
	private Double price;
	
	//construtor vazio
	public OrderItem() {
	
	}

	//construtor com argumentos sem o id , pois vou colocar pra frente é o pedido Order e o produto Product
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		//passa para o id o pedido
		id.setOrder(order);
		//passa para o id o produto
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	//get e set sem por o id
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	//get e set de order 
	// no get ele retorna o id.getOrder quem pesquisar
	@JsonIgnore //para cortar o loop eterno pedido chama item e item chama pedido
	public Order getOrder() {
		return id.getOrder();
	}
	
	//no set ele recebe um order no parametro e joga esse order dentro do id.setorder
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	//get e set de product
	//retirei o @JsonIgnore //para não dar loop eterno quando chamar getOrder na classe product
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	//método subtotal que calcula os preço dos itens com quantidade
	//tenho que por o nome get pq na plataforma java interprise o que vale é o get para aparecer no meu resultado Json
	public Double getSubTotatl() {
		return price * quantity;
	}
	
	//hashcode e equals de id pois é chave primaria
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
	

	
	
}
