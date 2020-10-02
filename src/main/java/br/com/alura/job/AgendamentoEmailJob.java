package br.com.alura.job;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@Stateless
public class AgendamentoEmailJob {
	
	private static AgendamentoEmailJob instance;
	
	private AgendamentoEmailJob() {}
	
	public synchronized static AgendamentoEmailJob getInstance() {
		if (instance == null)
			instance = new AgendamentoEmailJob();
		return instance;
	}
	
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void enviarEmail() {
		List<AgendamentoEmail> listaPorNaoAgendado =
				agendamentoEmailServico.listarNaoAgendados();
		listaPorNaoAgendado.forEach(emailNaoAgendado -> {
			agendamentoEmailServico.enviar(emailNaoAgendado);
			agendamentoEmailServico.alterar(emailNaoAgendado);
		});;
	}
}
