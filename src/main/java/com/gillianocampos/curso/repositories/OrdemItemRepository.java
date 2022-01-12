package com.gillianocampos.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gillianocampos.curso.entities.OrderItem;

//responsavel pelo repositoy da classe OrderItem. Sao interfaces
//ela herança com jpaRepository do tipo OrdemItem e o tipo do Id de OrdemItem é long
//so assim ja tem uma implementação padrao e ja esta pronto

//como essa interface repository pode ou nao colocar o Anotation @Repository pois ela ja herda do jpaRepository
public interface OrdemItemRepository extends JpaRepository<OrderItem, Long> {

}
