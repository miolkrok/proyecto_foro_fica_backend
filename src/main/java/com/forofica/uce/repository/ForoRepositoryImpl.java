package com.forofica.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.forofica.uce.repository.modelo.Foro;

@Repository
@Transactional
public class ForoRepositoryImpl implements IForoRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Foro foro) {
		this.entityManager.persist(foro);
	}

	@Override
	public Foro buscarPorId(Integer id) {
		return this.entityManager.find(Foro.class, id);
	}

	@Override
	public List<Foro> buscarTodos() {
		String sql = "SELECT f FROM Foro f";
		TypedQuery<Foro> myQuery = this.entityManager.createQuery(sql, Foro.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizar(Foro foro) {
		this.entityManager.merge(foro);
	}

	@Override
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscarPorId(id));
	}

	@Override
	public List<Foro> buscarPorCedulaEstudiante(String cedula) {
		String sql = "SELECT f FROM Foro f WHERE f.estudiante.cedula = :cedula";
		TypedQuery<Foro> myQuery = this.entityManager.createQuery(sql, Foro.class);
		myQuery.setParameter("cedula", cedula);
		return myQuery.getResultList();
	}
}
