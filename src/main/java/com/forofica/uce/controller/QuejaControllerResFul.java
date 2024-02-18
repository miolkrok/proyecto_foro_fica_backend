package com.forofica.uce.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forofica.uce.repository.modelo.Queja;
import com.forofica.uce.service.IQuejaService;
import com.forofica.uce.service.to.QuejaTO;

@RestController
@RequestMapping("/quejas")
@CrossOrigin
public class QuejaControllerResFul {

	@Autowired
	private IQuejaService quejaService;

	// GET
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QuejaTO>> consultaTodas() {
		List<QuejaTO> lista = this.quejaService.buscarTodas();
		for (QuejaTO n : lista) {
			Link myLink = linkTo(methodOn(QuejaControllerResFul.class).consultarPorId(n.getId())).withSelfRel();
			n.add(myLink);
		}
		return new ResponseEntity<>(lista, null, 200);
	}

	// GET
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuejaTO> consultarPorId(@PathVariable Integer id) {
		QuejaTO quejaTO = this.quejaService.buscarPorId(id);
		return new ResponseEntity<>(quejaTO, null, 200);
	}

	// POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Queja> guardar(@RequestBody Queja queja) {
		this.quejaService.guardarQueja(queja);
		return new ResponseEntity<>(queja, HttpStatus.CREATED);
		
	}

	// PUT
	@PutMapping(path = "/{identificador}")
	public void actualizar(@RequestBody Queja queja, @PathVariable Integer identificador) {
		queja.setId(identificador);
		this.quejaService.actualizar(queja);
	}

	// DELETE
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.quejaService.eliminar(id);
	}

}
