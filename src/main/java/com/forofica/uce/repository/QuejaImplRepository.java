package com.forofica.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.forofica.uce.repository.modelo.Queja;

@Repository
@Transactional
@CrossOrigin
public class QuejaImplRepository implements IQuejaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertaQueja(Queja queja) {
		this.entityManager.persist(queja);

	}

	@Override
	public Queja buscaQueja(Integer id) {
		return this.entityManager.find(Queja.class, id);
	}

	@Override
	public List<Queja> buscaTodasQuejas() {
		TypedQuery<Queja> myQuery = this.entityManager
				.createQuery("SELECT q FROM Queja q JOIN FETCH q.estudiante ORDER BY q.fechaPublicacion DESC", Queja.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizar(Queja queja) {
		this.entityManager.merge(queja);
	}

	@Override
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscaQueja(id));
	}

}
