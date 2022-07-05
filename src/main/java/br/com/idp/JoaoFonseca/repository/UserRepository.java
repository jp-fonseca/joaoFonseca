package br.com.idp.JoaoFonseca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idp.JoaoFonseca.model.Author;

public interface UserRepository extends JpaRepository<Author,Long>{

}
