package com.gillianocampos.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gillianocampos.curso.entities.Order;

//responsavel pelo repositoy da classe Order. Sao interfaces
//ela herança com jpaRepository do tipo Order e o tipo do Id de Order é long
//so assim ja tem uma implementação padrao e ja esta pronto

//como essa interface repository pode ou nao colocar o Anotation @Repository pois ela ja herda do jpaRepository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
