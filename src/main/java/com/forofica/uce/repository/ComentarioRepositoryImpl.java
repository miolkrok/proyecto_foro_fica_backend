package com.forofica.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.forofica.uce.repository.modelo.Comentario;

/**
 * @author Neomett
 *
 */
@Repository
@Transactional
public class ComentarioRepositoryImpl implements IComentarioRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Comentario comentario) {
		this.entityManager.persist(comentario);
	}

	@Override
	public Comentario buscarPorId(Integer id) {
		return this.entityManager.find(Comentario.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscarPorId(id));
	}

	/*
	@Override
	public List<Comentario> buscarTodos() {
		String sql = "SELECT c FROM Comentario c";
		TypedQuery<Comentario> myQuery = this.entityManager.createQuery(sql, Comentario.class);
		return myQuery.getResultList();
	}
	*/
	
	@Override
	public List<Comentario> buscaTodosComentarios() {
		TypedQuery<Comentario> myQuery = this.entityManager
				.createQuery("SELECT c FROM Comentario c JOIN FETCH c.estudiante ORDER BY c.fechaPublicacion DESC", Comentario.class);
		return myQuery.getResultList();
	}


	@Override
	public List<Comentario> buscarPorCedulaEstudiante(String cedula) {
		String sql = "SELECT c FROM Comentario c WHERE c.estudiante.cedula = :cedula";
		TypedQuery<Comentario> myQuery = this.entityManager.createQuery(sql, Comentario.class);
		myQuery.setParameter("cedula", cedula);
		return myQuery.getResultList();
	}

	@Override
	public List<Comentario> buscarPorIdForo(Integer idForo) {
		String sql = "SELECT c FROM Comentario c WHERE c.foro.id = :idForo";
		TypedQuery<Comentario> myQuery = this.entityManager.createQuery(sql, Comentario.class);
		myQuery.setParameter("id", idForo);
		return myQuery.getResultList();
	}
}
