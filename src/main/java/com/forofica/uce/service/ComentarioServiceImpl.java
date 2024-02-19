package com.forofica.uce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forofica.uce.repository.IComentarioRepository;
import com.forofica.uce.repository.modelo.Comentario;
import com.forofica.uce.repository.modelo.Estudiante;
import com.forofica.uce.repository.modelo.Foro;
import com.forofica.uce.service.to.ComentarioLigero;
import com.forofica.uce.service.to.ComentarioTO;
import com.forofica.uce.service.to.EstudianteTO;
import com.forofica.uce.service.to.ForoTO;

@Service
public class ComentarioServiceImpl implements IComentarioService {

	@Autowired
	private IComentarioRepository comentarioRepository;
	@Autowired
	private IEstudianteService estudianteService;
	@Autowired
	private IForoService foroService;

	@Override
	public void guardar(ComentarioLigero comentario) {
		Foro f = this.foroService.buscarPorIdNormal(comentario.getForoId());
		Estudiante e = this.estudianteService.buscarPorId(comentario.getEstudianteId());
		Comentario c = new Comentario();
		c.setContenido(comentario.getContenido());
		c.setFechaPublicacion(comentario.getFecha());
		c.setEstudiante(e);
		c.setForo(f);
		this.comentarioRepository.insertar(c);
	}

	@Override
	public ComentarioTO buscarPorId(Integer id) {
		return this.convertirATO(this.comentarioRepository.buscarPorId(id));
	}

	@Override
	public void eliminar(Integer id) {
		this.comentarioRepository.eliminar(id);
	}

	@Override
	public List<ComentarioTO> buscarTodos() {
		// List<Comentario> lista = this.comentarioRepository.buscarTodos();
		List<Comentario> lista = this.comentarioRepository.buscaTodosComentarios();
		List<ComentarioTO> listaTO = lista.stream().map(x -> this.convertirATO(x)).collect(Collectors.toList());
		return listaTO;
	}

	private ComentarioTO convertirATO(Comentario comentario) {
		EstudianteTO estudiante = new EstudianteTO();
		estudiante.setId(comentario.getEstudiante().getId());
		estudiante.setCedula(comentario.getEstudiante().getCedula());
		estudiante.setNombre(comentario.getEstudiante().getNombre());
		estudiante.setApellido(comentario.getEstudiante().getApellido());
		estudiante.setSemestre(comentario.getEstudiante().getSemestre());

		ForoTO f = new ForoTO();
		f.setId(comentario.getForo().getId());
		f.setTitulo(comentario.getForo().getTitulo());

		ComentarioTO c = new ComentarioTO();
		c.setId(comentario.getId());
		c.setTitulo(comentario.getTitulo());
		c.setContenido(comentario.getContenido());
		c.setFecha(comentario.getFechaPublicacion());
		c.setEstudianteTO(estudiante);
		c.setForo(f);
		return c;
	}

	@Override
	public List<ComentarioTO> buscarPorCedulaEstudiante(String cedula) {
		List<Comentario> lista = this.comentarioRepository.buscarPorCedulaEstudiante(cedula);
		return lista.stream().map(x -> this.convertirATO(x)).collect(Collectors.toList());

	}

	@Override
	public List<ComentarioTO> buscarPorIdForo(Integer idForo) {
		List<Comentario> lista = this.comentarioRepository.buscarPorIdForo(idForo);
		return lista.stream().map(x -> this.convertirATO(x)).collect(Collectors.toList());
	}
}
