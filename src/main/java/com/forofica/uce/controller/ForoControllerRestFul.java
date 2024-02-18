package com.forofica.uce.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.forofica.uce.service.ComentarioServiceImpl;
import com.forofica.uce.service.IForoService;
import com.forofica.uce.service.to.ForoTO;

@RestController
@RequestMapping("/foros")
@CrossOrigin
public class ForoControllerRestFul {

	private static final Logger LOG = LoggerFactory.getLogger(ComentarioServiceImpl.class);

	@Autowired
	private IForoService foroService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ForoTO> guardar(@RequestBody ForoTO foro) {
		this.foroService.guardar(foro);
		return new ResponseEntity<>(foro, HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ForoTO> consultarPorId(@PathVariable Integer id) {
		ForoTO foroTO = this.foroService.buscarPorId(id);
		LOG.warn("buscando foro id:" + foroTO.getComentarios().size());
		return new ResponseEntity<>(foroTO, HttpStatus.ACCEPTED);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ForoTO>> consultarTodas() {
		List<ForoTO> lista = this.foroService.buscarTodos();
		for (ForoTO n : lista) {
			Link myLink = linkTo(methodOn(ForoControllerRestFul.class).consultarPorId(n.getId())).withSelfRel();
			n.add(myLink);
		}
		return new ResponseEntity<>(lista, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void eliminar(@PathVariable Integer id) {
		this.foroService.eliminar(id);
		
	}
}