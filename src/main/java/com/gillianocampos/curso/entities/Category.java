package com.gillianocampos.curso.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_category")
//sexto passo serializable
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

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
	
	
	
	//fala pro jpa qual é a chave primaria colocar em cima do atributo
		//como a chave é numerica para colocar autoincrementar usar o @ abaixo
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		// primeiro atributos
		private Integer id;
		private String name;
	

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
