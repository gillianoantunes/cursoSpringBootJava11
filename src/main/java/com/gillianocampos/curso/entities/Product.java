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
import javax.persistence.Table;

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
