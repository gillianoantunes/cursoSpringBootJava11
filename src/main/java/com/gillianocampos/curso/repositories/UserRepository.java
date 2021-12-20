package com.gillianocampos.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gillianocampos.curso.entities.User;

//responsavel pelo repositoy da classe User. Sao interfaces
//ela herança com jpaRepository do tipo User e o tipo do Id de User é long
//so assim ja tem uma implementação padrao e ja esta pronto
public interface UserRepository extends JpaRepository<User, Long> {

}
