package com.forofica.uce.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forofica.uce.service.IComentarioService;
import com.forofica.uce.service.to.ComentarioLigero;
import com.forofica.uce.service.to.ComentarioTO;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin
public class ComentarioControllerRestFul {
	
	@Autowired
	private IComentarioService comentarioService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ComentarioTO>> consultaTodas(){
		List<ComentarioTO> lista = this.comentarioService.buscarTodos();
		for(ComentarioTO n : lista){
			Link myLink = linkTo(methodOn(ComentarioControllerRestFul.class)
					.consultarPorId(n.getId()))
					.withSelfRel();
			n.add(myLink);
		}
		
		return new ResponseEntity<>(lista, null, 200);
	}

	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ComentarioTO> consultarPorId(@PathVariable Integer id){
		ComentarioTO comentarioTO = this.comentarioService.buscarPorId(id);
		return new ResponseEntity<>(comentarioTO, null, 200);
	}

	
	@PostMapping(consumes  = MediaType.APPLICATION_JSON_VALUE)
	public void guardar(@RequestBody ComentarioLigero comentario) {
		this.comentarioService.guardar(comentario);
	}
	
	
	@DeleteMapping(path="/{id}")
	public void eliminar(@PathVariable Integer id) {
		this.comentarioService.eliminar(id);
	}
}
