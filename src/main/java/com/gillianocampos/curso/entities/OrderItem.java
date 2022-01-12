package com.gillianocampos.curso.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
//12 seguido do mapeamento (mappedBy = "id.order")pq na classe ordemitem eu tenho o id e o id que tem o pedido
//13 fazer o get para esse atributo e agora meu pedido conhece os items dele
//14 instanciar os itens do pedido em testConfig
//15 salvar no banco mas antes tem que criar repository do OrderItemcopiar e colar userrepository e trocar por OrderItem a palavra User
//16/ fazer a injeção do repository na classe TestConfig @AutoWired
//17 rodar o banco e ver se inseriu e depois rodar no postman  localhost:8080/orders/1 vai dar loop eterno pedido chama itme  e itens chama pedido
//18 usar o @JsonIgnore no metodo getOrder na clase orderItem para cortar pq o getorder fica chamando o pedido associado ao item de pedido ai o pedido chama o item de pedido qe da o loop


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
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
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
