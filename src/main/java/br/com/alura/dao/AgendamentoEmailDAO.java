package br.com.alura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.entidade.AgendamentoEmail;

public class AgendamentoEmailDAO {
	
	private EntityManager entityManager;

	public AgendamentoEmailDAO() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("AgendamentoEmailDS");
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	public List<AgendamentoEmail> listar(){
		entityManager.getTransaction().begin();
		List<AgendamentoEmail> resultado = entityManager.createQuery(
				"SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class
		).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return resultado;		
	}

}
