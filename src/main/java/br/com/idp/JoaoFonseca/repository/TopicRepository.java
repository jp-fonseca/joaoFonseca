package br.com.idp.JoaoFonseca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idp.JoaoFonseca.model.Topic;

public interface TopicRepository extends JpaRepository<Topic,Long>{

	List<Topic> findByMediaTitle(String title);

}
