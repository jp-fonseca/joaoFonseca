//package br.com.idp.JoaoFonseca.controller;
//
//import java.net.URI;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import br.com.idp.JoaoFonseca.form.ReplyForm;
//import br.com.idp.JoaoFonseca.model.Reply;
//import br.com.idp.JoaoFonseca.service.ReplyService;
//
//@RestController
//@RequestMapping("/topics")
//public class ReplyController {
//
//	@Autowired
//	private ReplyService replyService;
//	
//	@PostMapping("/{id}")
//	public ResponseEntity<ReplyDto> registerReply(@PathVariable Long id, @RequestBody @Valid ReplyForm form, UriComponentsBuilder uriBuilder){
//		Reply reply = replyService.register(id, form);
//		
//		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(reply.getId()).toUri();
//		return ResponseEntity.created(uri).body(new ReplyDto(reply));
//	}
//	
//}