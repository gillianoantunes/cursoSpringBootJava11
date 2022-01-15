package com.gillianocampos.curso.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "tb_payment")
public class Payment implements Serializable{
	//serializable
	private static final long serialVersionUID = 1L;
	
	//atributos e mapaeamento
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Instant moment;
	
	//mapeamento 1 para 1 ir na classe order e colocar tbm conforme os passos na
	@OneToOne
	@MapsId
	//atributo associação com classe order
	private Order order;

	//construtor vazio
	public Payment(){
	
	}

	//construtor com argumentos
	public Payment(Long id, Instant moment, Order order) {
		super();
		this.id = id;
		this.moment = moment;
		this.order = order;
	}

	
	//geters e seters
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
	//equals e hashcode do id
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}