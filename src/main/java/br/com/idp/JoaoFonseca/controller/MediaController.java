package br.com.idp.JoaoFonseca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idp.JoaoFonseca.dto.MediaDto;
import br.com.idp.JoaoFonseca.service.MediaService;

/**
 * This is a class that represents a Controller(Media).
 */
@RestController
@RequestMapping("/medias")
public class MediaController {

	@Autowired
	public MediaService mediaService;
	
	/**
	 * This method list All Medias.
	 * @return ResponseEntity.ok(medias)
	 */
	@GetMapping
	public ResponseEntity<List<MediaDto>> listAll(){
		List<MediaDto> medias = mediaService.listAll();
		return ResponseEntity.ok(medias);
	}
	
}
