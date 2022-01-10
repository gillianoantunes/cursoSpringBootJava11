package com.gillianocampos.curso.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gillianocampos.curso.entities.Order;
import com.gillianocampos.curso.entities.Product;

//passos essa classe auxiliar sera uma chave primaria da classe OrderItem que criaremos depois
//1 fazer os atributos com referencia da classe order pedido e classe product
//essa classe auxiliar não tem construtores
//2 fazer getters e setters
//3 fazer hascode e equals com os 2 atributos pois os 2 que identificam o item
//4 serializable
//fazer os @Anotations do JPA
//5 colocar @Embeddable para indicar para o jpa que trata de classe auxiliar composta 
//6 @ManytoOne no atributo order e product pois sao muitos para um com pedido e produto
//7 @JoinCollum indicando qual nome da chave estrangeira no tabela do banco de dados relacional no caso id
//terminamos a classe auxiliar agora vou fazer a classe OrdemItem que aparece no diagrama
//acompanhar os passos la

@Embeddable
public class OrderItemPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//atributos
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	
	//get e set
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPk other = (OrderItemPk) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
	
}
