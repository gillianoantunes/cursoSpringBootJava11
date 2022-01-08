package com.gillianocampos.curso.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Instant moment;
	
	//associações com user 1 pedido de um cliente e 1 user tem varios pedidos
	//client é o nome que esta no diagrama de entidades do tipo user
	//implementar na classe user a associação 1 user tem varios pedidos e por metodo get para essa lista
	//para instruir o jpa que o client é uma chave estrangeira muitosparaum usar o @ManyToOne
	// e o @JoinColumn e entre parenteses o nome da chave estrangeira no banco de dados
	//fazer tbm na classe User o lado de 1 para muitos
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	public Order() {
		
	}

	public Order(Long id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
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

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

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
