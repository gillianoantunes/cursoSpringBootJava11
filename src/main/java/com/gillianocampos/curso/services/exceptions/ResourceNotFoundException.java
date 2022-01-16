package com.gillianocampos.curso.services.exceptions;

// 53 passos criei essa classe ResourceNotFoundException , quando a gente faz a busca no findByI e esse id não existe está dando um codigo 500 e o correto é dar codigo 404 de não encontrado
//criando uma exceceção personalizada da camada de serviço capaz de lançaer exceções dela.
//na classe UserService no metodo finbyId  quando não encontra o id no optional fica vazio e a função retorna o .get do obj que da um erro 500, vamos tratar este erro
//54 ela vai estender RunTimeException que é a exceççao que o compilador não obriga tratar
//55 adicionar numero de versao serializable
//56 criar um construtor recebendo o Object Id que eu tentei encontrar e não encontrei 
//57 criar a classe StandardError no pacote resources.exceptions pq quando da um erro o spring retorna um objeto com esses dados aqui:
//timestamp,status,error, message, path e vou criar para personalizar esses erros criando a classe com esses campos. quem trabalha com requisição é o resource
//criar os campos na classe, construtores,serializable,geters e seters nao fazer equals pq em momento algum vou comprar erro com outro
//58 criar a classe ResourceExceptionHandler onde darei nessa classe o tratamento manual para nossos erros
//59 colocar anotation na classe @ControllerAdvice que intercepta as exceções que acontecerem para que este objeto possa executar o tratamento
//60 criar o metodo para tratar a exceção de id nao encontrado e usar a anotation @ExceptionHandler(ResourceNotFoundException.class) para que ele seja capaz de interceptar a requisição que deu exceção para cair ali para tratar e entre parentese o nome da exceção que estou interceptando
//61 ea classe Userervice tempos que atualizar o metodo get do metodo findById
//trocar o return obj.get() que da a exceção 500 caso nao encontre id por return obj.orElseThrow(()-> new ResourceNotFoundException(id));
// esse obj.orElseThrow ele tenta dar o get se não tiver usuario ele lança a exeção a execeção entre parentese lambida e depois a nossa exceção personalizada ResourceNotFoundException passando id como paraemtro
//62 ir no postman chamar localhost:8080/users/7 que é um usuario que não existe e ver se a exceção agora deu 404 e personalizada
//63 neste erro o instante para formatar e usar até os segundos apenas sem milissegundos etc usar o formato que usamos na classe Order na declaraçao do atributo moment colocamos um formato acima. copiar e jogar na classe StarndarErro em cima do atributo timestamp
// agora a data no resultado do erro vem formatada no postman localhost:8080/users/7 
//64 commit


public class ResourceNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) { 
		//lançar a execeção chamando o construtor da super classe colocando uma mensando concatenando com id que não encontrei
		super("Resource não econtrado. Id " + id);
	}
}
