package com.aledguedes.helpdesk.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aledguedes.helpdesk.helpdesk.domain.Chamado;
import com.aledguedes.helpdesk.helpdesk.domain.Cliente;
import com.aledguedes.helpdesk.helpdesk.domain.Tecnico;
import com.aledguedes.helpdesk.helpdesk.enuns.Perfil;
import com.aledguedes.helpdesk.helpdesk.enuns.Prioridade;
import com.aledguedes.helpdesk.helpdesk.enuns.Status;
import com.aledguedes.helpdesk.helpdesk.repositories.ChamadoRepository;
import com.aledguedes.helpdesk.helpdesk.repositories.PessoaRepository;

@Service
public class DBService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void InstanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Alexandre Guedes", "805.693.790-08", "alexandre@guedes.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
		Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
		Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));

		Cliente cli1 = new Cliente(null, "Linus Turvaldis", "159.206.880-41", "linus@empresa.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));

		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado #01", "Primeiro chamado", cli1, tec1);
		Chamado ch2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado #02", "Segundo chamado", cli2, tec2);
		Chamado ch3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado #03", "Terceiro chamado", cli3, tec3);
		Chamado ch4 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado #04", "Quarto chamado", cli4, tec4);
		Chamado ch5 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado #05", "Quinto chamado", cli5, tec5);
		Chamado ch6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado #06", "Sexto chamado", cli5, tec5);
		
		pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
		chamadoRepository.saveAll(Arrays.asList(ch1, ch2, ch3, ch4, ch5, ch6));		
	}
}
