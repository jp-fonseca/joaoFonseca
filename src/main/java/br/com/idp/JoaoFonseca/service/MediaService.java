package br.com.idp.JoaoFonseca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import br.com.idp.JoaoFonseca.model.Media;
import br.com.idp.JoaoFonseca.repository.MediaRepository;

@Service
public class MediaService {

	@Autowired
	private MediaRepository mediaRepository;
	
	
	public List<MediaDto> listAll(){
		List<Media> medias = mediaRepository.findAll();
		return MediaDto.convert(medias);
	}
	
}
