package com.gillianocampos.curso.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


//colocar em cima do id os anotations @ do jpa para dizer que essa classe vai ser uma tabela do banco de dados
//para a o nome da classe User não der conflito com a palavra User do sql usar o anotation @Table e dar um nome no caso tb_user
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	//fala pro jpa qual é a chave primaria colocar em cima do atributo
	//como a chave é numerica para colocar autoincrementar usar o @ abaixo
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	//asssociaçaõ um user tem varios pedidos o nome orders esta no diagrama
	//usa lista para associação com vários e ja tem que instanciar as coleções new arraylist
	////instanciar para garantir que a coleção nãpo comece valendo nula ela tem que comecar vazia porem instanciada
	//acrescentar o metodo get para essa coleção o set não precisa pq não vamos alterar a lista hora nenhuma
	//clica em orders para criar
	//Anotation @OneToMany para indicar ao jpa que no banco de dados e entre paraentes o nome do atributo do outro lado da associação no caso client
	//OnetoMany está mapeado do outro lado por client
	@OneToMany(mappedBy =  "client")
	//para não dar loop eterno, pois user chama lista pedido,lista pedido chama user, usar a anotation abaixo @JsonIgnore abaixo da lista de pedido do cliente
	//colocar @JsonIgnore em pelo um lugar ou aqui ou la em pedido Order e resolve o problema no postman localhost:8080/users/1 que estava dando loop eterno
	//quando rodar no postman o pedido ex: localhost:8080/orders/1 o jpa carrega tbm os dados do user associado quando vc tem uma associação muitos para um, se vc carregar o objeto do lado do muitos no caso pedido o objeto do lado do 1 vem automaticamente
	//mas isso nao acontece se vc carregar um objeto do lado do 1 para muitos no caso User(lazy loading)
	//se eu colocar o anotation @jsonIgnore la em order.java em cima do private User client; no lado do pedido ai sim ele carrega e traz todos os pedidos do usuario 1 localhost:8080/users/1
	@JsonIgnore
	private List<Order>  orders = new ArrayList<>();
	
	public User() {
		
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//metodo get da lista orders da associação um user tem varios pedidos
	public List<Order> getOrders() {
		return orders;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
