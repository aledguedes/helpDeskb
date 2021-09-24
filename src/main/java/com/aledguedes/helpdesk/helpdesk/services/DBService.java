package com.aledguedes.helpdesk.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aledguedes.helpdesk.helpdesk.domain.Chamado;
import com.aledguedes.helpdesk.helpdesk.domain.Cliente;
import com.aledguedes.helpdesk.helpdesk.domain.Tecnico;
import com.aledguedes.helpdesk.helpdesk.enuns.Perfil;
import com.aledguedes.helpdesk.helpdesk.enuns.Prioridade;
import com.aledguedes.helpdesk.helpdesk.enuns.Status;
import com.aledguedes.helpdesk.helpdesk.repositories.ChamadoRepository;
import com.aledguedes.helpdesk.helpdesk.repositories.ClienteRepository;
import com.aledguedes.helpdesk.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecRepository;
	@Autowired
	private ClienteRepository cliRepository;
	@Autowired
	private ChamadoRepository cmdRepository;

	public void InstanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Alexandre Guedes", "80569379008", "alexandre@guedes.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Turvaldis", "15920688041", "linus@empresa.com", "123");

		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado #01", "Primeiro chamado", cli1,
				tec1);

		tecRepository.saveAll(Arrays.asList(tec1));
		cliRepository.saveAll(Arrays.asList(cli1));
		cmdRepository.saveAll(Arrays.asList(ch1));

		Tecnico tec2 = new Tecnico(null, "Joao Prado", "60914291084", "joao@vprado.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli2 = new Cliente(null, "Cirilo Prado", "87427014065", "cirilo@eletricista.com", "123");

		Chamado ch2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado #02", "Primeiro chamado", cli2,
				tec2);

		tecRepository.saveAll(Arrays.asList(tec2));
		cliRepository.saveAll(Arrays.asList(cli2));
		cmdRepository.saveAll(Arrays.asList(ch2));
	}
}
