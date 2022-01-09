package com.gillianocampos.curso.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_category")
//sexto passo serializable
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	//passos para fazer tudo entre duas tabelas um para muitos, no caso category e product
	//primeiro fazer os atributos
//segundo associações depois que tiver as classes produtos
	// terceiro contrutores vazio e com argumentos
	//quarto get e set, se for coleção o atributo é somente o get
	//quinto hascode e equals
	//sexto serializable
	//setimo colocar as @ anotations
	//@Entity para ser uma entidade do meu sistemas gerenciada pelo JPA
	//oitavo implementar um categoryRepository no pacote repositories copiar e colar o userrepository e mudar o nome user para category
	//nono ir na classe testconfig e fazer uma injeção de dependencia do categoryRepository e depois adicionar uns objetos category exemplos
	//decimo fazer a classe  CategoryService copiar e colar userService e colar com nome CategoryService e trocar palavras User por Service dar Ctrl + F onde tiver User colocar Category marcar tbm casesensitive
	//decimo primeiro fazer a classe CategoryResource copiar e colar do userResource colocar nome CategoryReosurce e trocar palavras tbm e trocar na classe  esse caminho do recursos por category@RequestMapping(value = "/categories")
	//por fim ir no postman e colocar o caminho que mudamos localhost:8080/categories e ver os dados se foram salvos
	//fazer o commit
	//agora fazer a classe prooduct
	//depois fazer a instancia na classe Product do relacionamento
	//decimo segundo depois fazer aqui a instancia deste lado do relacionamento categoria , produto
	//decimo terceiro criar somente o get da instancia criada pq é coleção usa so o get nao faz sentido usar set
	//decimo quarto voltar a classe product e fazer construtores da classe product
	//15 - criar get e set mas coleçoes somente ger na classe Product
	//16 hascode e equals da classe Product
	//17 serializable da classe product
	//18 fazer o mapeamento do jpa com suas anotations @entity,@Table(name = "tb_product") em cima da classe product
	//19 em cima do id de product fazer @Id e @GeneratedValue(strategy = GenerationType.IDENTITY) para identificar como chave primaria autoincrementada
	//20 fazer o ProductRepository copiando categoryRepository e colando  e trocar palavras category por product
	//21 na classe TestConfig fazer a injeção do ProductRepository para inserir no banco 	
	// @Autowired
	// private ProductRepository productRepository;
	//22 inserir os dados e salvar no banco na classe TestConfig //roda aplicação e ver se vai inserir no banco
	//23 fazer o service
	//24 fazer o resource
	//25 colocar @Transient em cima da associção set de category e product 
	//@Transient impede que o jpa tente interpretar esse set
	//26 ir no banco e ver se inseriu produtos http://localhost:8080/h2-console/
	//27 fazer o ProductService mudando os nomes
	//28 fazer o ProductResource mudando os nomes e caminhos
	//29 verificar no postman se inseriu os produtos no caminho localhost:8080/products
	//commit
	//ate que se faz relacionamento total de 2 tabelas 1 para muitos no caso category e product
	
 	//fala pro jpa qual é a chave primaria colocar em cima do atributo
		//como a chave é numerica para colocar autoincrementar usar o @ abaixo
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		// primeiro atributos
		private Integer id;
		private String name;
	
		//decimo segundo
		//instanciação do relacionamento category produto
		//set<product> pq dentro da categoria eu tenho conjunto de produto e la na classe Product dentro do produto eu tenho conjunto de categoria
		//nome products é o que esta representado no diagrama
		//clica no erro que dara no nome products e crie o metodo get e set deste atributo que criei agora, mas se for um tipo de coleção eu so uso o get pq não faz sentido em alterar uma coleção de produtos
		//eu nunca troco coleção eu posso adicionar e remover elementos da coleção, mas trocar não faz sentido
		@Transient
		private Set<Product> products = new HashSet<>();
	//terceiro construtor vazio
	public Category() {
		
	}

//terceiro construtor com argumentos
	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	//quarto get e set
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	//decimo terceiro somente o get da coleçao da associação com produtos
	public Set<Product> getProducts() {
		return products;
	}

//quinto passo: hascode e equals
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}




	
	
}
