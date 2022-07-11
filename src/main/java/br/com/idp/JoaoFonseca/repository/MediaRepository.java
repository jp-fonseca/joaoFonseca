package br.com.idp.JoaoFonseca.repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idp.JoaoFonseca.model.Media;

public interface MediaRepository extends JpaRepository<Media,Long>{

	Media findByName(@NotNull @NotEmpty String mediaName);

}
