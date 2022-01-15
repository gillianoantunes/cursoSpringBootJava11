package com.gillianocampos.curso.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gillianocampos.curso.entities.enums.OrderStatus;

//colocar em cima do id os anotations @ do jpa para dizer que essa classe vai ser uma tabela do banco de dados
//para a o nome da classe Order não der conflito com a palavra order do sql usar o anotation @Table e dar um nome no caso tb_order
@Entity
@Table(name = "tb_order")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// classe Instant é melhor que o Date para registrar momentos de datas
	//para garantir que meu instant seja mostrado la no meu json no formato de string do iso 8601 acrescentar a anotation @JsonFormat para fomatar
	//pattern é o formato e o timezone é padrao utc que é horario universal
	//ir no postman e verificar em localhost:8080/orders/1 se a momento da data foi formatado
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	//atributo para usar enumerado do Enum OrderStatus e colocar no construtor
	//coloquei como Integer e nao OrderStatus para eu dizer explitamente que eu estou gravando no banco de dados um número inteiro
	// e na hora de mostrar eu mostro o OrderStatus
	private Integer orderStatus;
	
	//associações com user 1 pedido de um cliente e 1 user tem varios pedidos
	//client é o nome que esta no diagrama de entidades do tipo user
	//implementar na classe user a associação 1 user tem varios pedidos e por metodo get para essa lista
	//para instruir o jpa que o client é uma chave estrangeira muitosparaum usar o @ManyToOne
	// e o @JoinColumn e entre parenteses o nome da chave estrangeira no banco de dados
	//fazer tbm na classe User o lado de 1 para muitos
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	
	//atributo coleção de itens mapeado com id que é chave primaria e .order da classe auxiliar ordemitempk
	@OneToMany(mappedBy = "id.order")
	 private Set<OrderItem> items = new HashSet<>();
	 
	//mapeamento e dentro de mappedBy colocar o nome do atributo que esta do outro lado na outra classe que é na classe Payment o atributo order
	//cascade mapeia para a classe order ter mesmo id para o pagamento tbm
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	//associação com classe payment 1 pedido tem 1 pagamento
	 private Payment payment;
	 
	public Order() {
		
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		//chama a operação setOrderStatus para atribuir um orderStatus para meu objeto Order
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	// meu atributo OrderStatus é integer tenho que converter integer para OrderStatus
	//tenho um metodo que criei no Enum OrderStatus que faz essa conversão entao vou chamar ele chamado converteParaOrderStatus
	public OrderStatus getOrderStatus() {
		return OrderStatus.converteParaOrderStatus(orderStatus);
	}

	//agora no set é o inverso..vou receber um OrderStatus e guardar um inteiro bata eu chamar a operação getCodigo la no enum OrderStatus
	public void setOrderStatus(OrderStatus orderStatus) {
		//se caso o programador passar um valor nulo para contruir o objeto
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCodigo();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	//get da colecçao do atribto que associa ordem com orderitem
	public Set<OrderItem> getItems(){
		return items;
	}
	
	//get e set no atributo payment associação com classe payment pagamento
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	//hashcode e equals
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
