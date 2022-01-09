package com.gillianocampos.curso.entities.enums;

public enum OrderStatus {

	//por padrao se não colocar valores o primeiro valor é zero
	//primeiro 0, segundo 1 e assim por diante, mas no caso abaixo colocamos pra começão com 1
	//quando poe valores o java exige que você faça um  campo codigo e um construtor para um tipo enumerado
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(4);
	
	//o codigo do tipo enumerado e abaixo o construtor para este tipo enumerado
	private int codigo;
	
	//esse construtor para este tipo enumerado é private porque não é chamado externamente
	private OrderStatus(int codigo) {
		this.codigo = codigo;
	}
	
	//para este codigo ficar acessivel ao mundo exterior eu crio um metodo publico para acessar este codigo
	public int getCodigo() {
		return codigo;
	}
	
	//criar um metodo estatico para converter um valor numero para um tipo enumerado
	// estatico pq o metodo vai funcionar sem instanciar que vai retornar um obejto do tipo OrderStatus chamado ConvertparaEnum
	// ele recebe um codigo e este obejto retorna o enum OrderStatus deste codigo
	//exemplo se vier o codigo 1 ele retorna WaitingPayment que é o codigo 1 no OrderStatus
	public static OrderStatus  converteParaOrderStatus(int codigo) {
		//para percorrer todos valores do Enum OrderSatatus
		for(OrderStatus value : OrderStatus.values()) {
			//para cada um deles testa se o codigo = ao codigo do parametro e retorna o valor
			if(value.getCodigo() == codigo) {
				return value;
			}
		}
		//se não encontrar nenhum valor do tipo enumerado eu lanço uma exceção
		throw new IllegalArgumentException("Código Inválido");	
	}
}
