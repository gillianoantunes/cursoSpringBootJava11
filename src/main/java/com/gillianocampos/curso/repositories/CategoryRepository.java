package com.gillianocampos.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gillianocampos.curso.entities.Category;

//responsavel pelo repositoy da classe Category. Sao interfaces
//ela herança com jpaRepository do tipo Category e o tipo do Id de Category é long
//so assim ja tem uma implementação padrao e ja esta pronto

//como essa interface repository pode ou nao colocar o Anotation @Repository pois ela ja herda do jpaRepository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
