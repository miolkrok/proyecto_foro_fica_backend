package com.forofica.uce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forofica.uce.repository.IForoRepository;
import com.forofica.uce.repository.modelo.Comentario;
import com.forofica.uce.repository.modelo.Foro;
import com.forofica.uce.service.to.ComentarioTO;
import com.forofica.uce.service.to.EstudianteTO;
import com.forofica.uce.service.to.ForoTO;

@Service
public class ForoServiceImpl implements IForoService {

	@Autowired
	private IForoRepository foroRepository;

	@Autowired
	private IEstudianteService estudianteService;

	@Override
	public void guardar(ForoTO foro) {
		Foro f = new Foro();
		f.setTitulo(foro.getTitulo());
		f.setTexto(foro.getTexto());
		f.setEstudiante(this.estudianteService.buscarPorId(foro.getEstudiante().getId()));
		f.setFecha(foro.getFecha());
		this.foroRepository.insertar(f);
	}

	@Override
	public ForoTO buscarPorId(Integer id) {
		return this.convertirATO(this.foroRepository.buscarPorId(id));
	}

	@Override
	public Foro buscarPorIdNormal(Integer id) {

		return this.foroRepository.buscarPorId(id);
	}

	@Override
	public List<ForoTO> buscarTodos() {
		List<Foro> lista = this.foroRepository.buscarTodos();
		List<ForoTO> listaTO = lista.stream().map(x -> this.convertirATO(x)).collect(Collectors.toList());
		return listaTO;
	}

	@Override
	public void actualizar(Foro foro) {
		this.foroRepository.actualizar(foro);
	}

	@Override
	public void eliminar(Integer id) {
		this.foroRepository.eliminar(id);
	}

	private ForoTO convertirATO(Foro foro) {
		EstudianteTO estudiante = this.estudianteService.convertir(foro.getEstudiante());

		List<Comentario> listaComentarios = foro.getComentarios();
		List<ComentarioTO> listaComentariosTO = listaComentarios.stream().map(c -> {
			ComentarioTO com = new ComentarioTO();
			com.setId(c.getId());
			com.setTitulo(c.getTitulo());
			com.setContenido(c.getContenido());
			com.setFecha(c.getFechaPublicacion());
			EstudianteTO estudianteComentario = this.estudianteService.convertir(c.getEstudiante());
			com.setEstudianteTO(estudianteComentario);
			return com;
		}).collect(Collectors.toList());

		ForoTO f = new ForoTO();
		f.setId(foro.getId());
		f.setTitulo(foro.getTitulo());
		f.setTexto(foro.getTexto());
		f.setFecha(foro.getFecha());
		f.setEstudiante(estudiante);
		f.setComentarios(listaComentariosTO);
		return f;
	}

	@Override
	public List<ForoTO> buscarPorCedulaEstudiante(String cedula) {
		List<Foro> lista = this.foroRepository.buscarPorCedulaEstudiante(cedula);
		List<ForoTO> listaTO = lista.stream().map(x -> this.convertirATO(x)).collect(Collectors.toList());
		return listaTO;
	}

}