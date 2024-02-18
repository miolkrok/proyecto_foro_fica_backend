package com.forofica.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forofica.uce.repository.modelo.Estudiante;

@Repository
@Transactional

public class EstudianteImplRepository implements IEstudianteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Estudiante seleccionarPorCedula(String cedula) {
		TypedQuery<Estudiante> myQuery = this.entityManager
				.createQuery("SELECT e FROM Estudiante e WHERE e.cedula = :datoCedula", Estudiante.class);
		myQuery.setParameter("datoCedula", cedula);
		return myQuery.getSingleResult();
	}

	@Override
	public void insertar(Estudiante estudiante) {
		this.entityManager.persist(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		this.entityManager.merge(estudiante);

	}

	@Override
	public void actualizarParcial(Boolean suscripcion, String cedula) {
		Query myQuery = this.entityManager
				.createQuery("UPDATE Estudiante e SET e.suscripcion = :datoSuscripcion WHERE e.cedula =:datoCondicion");
		myQuery.setParameter("datoSuscripcion", suscripcion);
		myQuery.setParameter("datoCondicion", cedula);
		myQuery.executeUpdate();

	}

	@Override
	public void borrar(Integer id) {
		this.entityManager.remove(this.buscarPorId(id));
	}

	@Override
	public Estudiante buscarPorId(Integer id) {
		return this.entityManager.find(Estudiante.class, id);
	}

	@Override
	public List<Estudiante> buscarTodos() {
		TypedQuery<Estudiante> myQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e ",
				Estudiante.class);
		return myQuery.getResultList();
	}

}
