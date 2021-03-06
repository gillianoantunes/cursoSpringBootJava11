package com.gillianocampos.curso.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//mapeamento1
@Entity
@Table(name = "tb_product")
public class Product implements Serializable{

	//serializable
	private static final long serialVersionUID = 1L;
	
	//atributos
	
	@Id //para identificar chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //para ser auto incrementada
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	//associação 1 produto tem varias categorias nao vou usar lista e sim set e instanciar
	//e tbm 1 categoria tem varios produtos. muitos pra muitos fazer o jointable
	//instanciar para garantir que a coleção nãpo comece valendo nula ela tem que comecar vazia porem instanciada
	
	//usei hashset pq o set não pode ser instanciar entao tenho qu usar uma classe correspondente a esta interface
	//da mesma forma que usamos o list e instancia arrayList usamos o set e instanciando hashset
	//depois de fazer esta associação fazer a instaciaação do outro lado na classe category
	//set<category> pq dentro da produto eu tenho conjunto de categoria e la na classe categoria dentro do categoria eu tenho conjunto de produto
	//nome categories é o que esta representando no diagrama
	
	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	
	//associação product com coleçao de itens de pedido
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>(); 
	
	//construtor vazio
	public Product() {
		
	}

	//construtor com argumento sem as coleções ou seja sem categories
	//eu tiro as coleçoes pq ja estou instanciando ela la em cima private Set<Category> categories = new HashSet<>();
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	
	//metodo get e set
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}

    
	//metodo get para pegar o produto e ver os pedidos associados a ele
	//dei o nome de getOrders pq orders é o nome que esta no diagrama uml
	@JsonIgnore //coloquei para quando eu pesquisar produtos não aparecer os pedidos e items, aparecer somente o produto
	public Set<Order> getOrders(){
		//declarar uma coleção chamada set para guardar
		Set<Order> set = new HashSet<>();
		//percorrer cada objetos do tipo ordemitem e para cada orderitem pegar order associado a ele
		//para cada objeto OrdemItem vou chamar de x contido na minha lista ou coleçao de items, percorrendo todos ordemitems com apelido x
		for(OrderItem x : items) {
			//vou adicionar cada objeto encontrado no set  
			set.add(x.getOrder());
		}
		//retorna toda minha coleção de resultado chamado set
		return set;
	}
	
	
	
	
	
	//apaguei o setCategories pq é coleção
	
	
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

	
	
}
