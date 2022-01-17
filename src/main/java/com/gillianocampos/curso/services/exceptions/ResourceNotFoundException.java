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
//65 exceções para deletar exemplo deletar um objeto que não existe retorna 500 o ideal é lançar a exceção da aula passada 404 não encontrado
// se no postman vc mudar delete localhost:8080/users/7 dara codigo 500 pois usuario de codigo 7 não existe
//na classe UserSrvice no método delete colocar um try{ repos....} catch (RunTimeException e){ e.printStackTrace para ver o tipo da exceção que foi lançada par gente tratar
//executa limpa o console botao direito e clear vai no postamn delete localhost:8080/users/7
//verifica no console a exceção que deu EmptyResultDataAccessException e não deletou esse nome de exceçao vou trocar a execção do catch na classe UserService no metodo delete .. trocar runtimeexception por EmptyResultDataAccessException 
//salvar e rodar de novo limpar o console e no postman deletar o ususario 7 que nao existe e agora sim da o err 404 nao encontrado igual fizemos na aula anterior
//no console a gente ve que foi executado uma consulta e como nao tinha o codigo ele gerou a exceção emptyResult eu capturei e lancei minha exceçao
//66 agora fazer a exceção para quando tentar deletar o usuario 1 que tem pedidos associados a ele não deixar apagar violação de integridade
//se vc for no postman tentar apagar usuario 1 vai dar erro 500 
//entao na classe UserService no metodo delete depois de capturar EmptyResultDataAccessException eu vou mandar caputar qualquer outra RunTimeException que ocorrer
//colocar debaixo do throw new ResourceNotFoundException colocar catch(RunTimeException e){ e.printStackTrace(); para imprimir no console essa exceção e rodar e tentar deletar usuario 1 que tem pedidos
//limpar o console e deletar no postman o usuario 1 no postman da codigo 204 nocontent como se estivesse apagado mas nao apagou pq a gente capturou a execção e nao fez nada
//ir no console no spring e ver o nome da exceção que é DataIntegrityViolationException violação de integridade
//66 agora trocar o rumtimeexception que é generico demais na classe UserService no metodo delete e colocar DataIntegrityViolationException
//67agora vamos lanar outra execção personalizada de violação de integridade que vamos criar no pacote services.exceptions chamada DatabaseException e lançar ela ao inves de lançar a exceççao que fizemos antes que é a ResourceNotFoundException que é para id nao encontrado
//68 extender a classe runtimeexception, colocar serializable, construtor com string passando uma mesnagem para a superclasse
//69 agora na classe UserService no metodo delete no catch DataIntegrityViolationException lançar essa exceção que fiz chamada DatabaseException lançando assim uma execção da minha camada de serviço
//70 no pacote reources.exceptions na classe ResourceExceptionHandler eu trato manualmente essa execção que crie DatabaseException
// entao em ResourceExceptionHandler criar um metodo para a databaseException. la ja tem um metodo para resourceNotFoundException copiar esse metodo e colar e alterar os nomes e a mensagem 
//71 salvar e rodar  e ver se deu uma exceçao personalizada ir no post e tentar apagar o usuario 1 que tem pedidos associados no postman delete localhost:8080/users/1
//agora sim da erro 400  BadRequest
//commit




//classe para lançar exceção de erro 404 que é quando id não é encontrado
public class ResourceNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	//recebe um object id
	public ResourceNotFoundException(Object id) { 
		//lançar a exceção chamando o construtor da super classe colocando uma mensagem concatenando com id que não encontrei
		super("Resource não econtrado. Id " + id);
	}
}
