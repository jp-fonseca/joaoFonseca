package br.com.idp.JoaoFonseca.repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idp.JoaoFonseca.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Long>{

	Author findByName(String name);

	Author findByEmail(@NotNull @NotEmpty @Email String authorEmail);

}
