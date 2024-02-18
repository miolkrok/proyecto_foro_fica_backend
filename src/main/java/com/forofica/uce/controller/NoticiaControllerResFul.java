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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forofica.uce.repository.modelo.Noticia;
import com.forofica.uce.service.INoticiaService;
import com.forofica.uce.service.to.NoticiaTO;

@RestController
@RequestMapping("/noticias")
@CrossOrigin
public class NoticiaControllerResFul {

	@Autowired
	private INoticiaService noticiaService;

	// GET
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NoticiaTO>> consultaTodas() {
		List<NoticiaTO> lista = this.noticiaService.buscarTodas();
		for (NoticiaTO n : lista) {
			Link myLink = linkTo(methodOn(NoticiaControllerResFul.class).consultarPorId(n.getId())).withSelfRel();
			n.add(myLink);
		}
		return new ResponseEntity<>(lista, null, 200);
	}

	// GET
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoticiaTO> consultarPorId(@PathVariable Integer id) {
		NoticiaTO noticiaTO = this.noticiaService.buscarPorId(id);
		return new ResponseEntity<>(noticiaTO, null, 200);
	}

	// POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void guardar(@RequestBody Noticia noticia) {
		this.noticiaService.guardarNoticia(noticia);
	}

	// PUT
	@PutMapping(path = "/{identificador}")
	public void actualizar(@RequestBody Noticia noticia, @PathVariable Integer identificador) {
		noticia.setId(identificador);
		this.noticiaService.actualizar(noticia);
	}

	// DELETE
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.noticiaService.eliminar(id);
	}
	
	@GetMapping(path = "titulos/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NoticiaTO>> consultarPorNombre(@PathVariable String titulo) {
	    List<NoticiaTO> noticiaTO = this.noticiaService.buscarNoticiaNombre(titulo);
	    return ResponseEntity.ok(noticiaTO);
	}


}
