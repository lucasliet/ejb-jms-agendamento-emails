package br.com.alura.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@Stateless
public class AgendamentoEmailDAO {
	
	private static final Logger LOGGER =
			Logger.getLogger(AgendamentoEmailServico.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<AgendamentoEmail> listar(){
		return entityManager.createQuery(
					"SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class
				).getResultList();
	}
	
	public List<AgendamentoEmail> listarNaoAgendados(){
		return entityManager.createQuery(
					"SELECT ae FROM AgendamentoEmail ae WHERE ae.agendado = false",
					AgendamentoEmail.class
				).getResultList();
	}
	
	public void inserir(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
	
	public void alterar(AgendamentoEmail agendamentoEmail) {
		entityManager.merge(agendamentoEmail);
	}
	
	public void enviar(AgendamentoEmail agendamentoEmail) {
		try {
			Thread.sleep(5000);
			LOGGER.info("O e-mail do(a) usuário(a) " +
					agendamentoEmail.getEmail() +
					" foi enviado!");
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}
}