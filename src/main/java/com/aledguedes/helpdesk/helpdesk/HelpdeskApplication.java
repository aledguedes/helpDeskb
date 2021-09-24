package com.aledguedes.helpdesk.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aledguedes.helpdesk.helpdesk.domain.Chamado;
import com.aledguedes.helpdesk.helpdesk.domain.Cliente;
import com.aledguedes.helpdesk.helpdesk.domain.Tecnico;
import com.aledguedes.helpdesk.helpdesk.enuns.Perfil;
import com.aledguedes.helpdesk.helpdesk.enuns.Prioridade;
import com.aledguedes.helpdesk.helpdesk.enuns.Status;
import com.aledguedes.helpdesk.helpdesk.repositories.ChamadoRepository;
import com.aledguedes.helpdesk.helpdesk.repositories.ClienteRepository;
import com.aledguedes.helpdesk.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner{
	
	@Autowired
	private TecnicoRepository tecRepository;
	@Autowired
	private ClienteRepository cliRepository;
	@Autowired
	private ChamadoRepository cmdRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico tec1 = new Tecnico(null, "Alexandre Guedes", "	80569379008", "alexandre@guedes.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Turvaldis", "15920688041", "linus@empresa.com", "123");
		
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado #01", "Primeiro chamado", cli1, tec1);
		
		tecRepository.saveAll(Arrays.asList(tec1));
		cliRepository.saveAll(Arrays.asList(cli1));
		cmdRepository.saveAll(Arrays.asList(ch1));
	}

}
