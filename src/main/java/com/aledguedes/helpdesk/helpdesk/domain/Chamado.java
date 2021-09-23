package com.aledguedes.helpdesk.helpdesk.domain;

import java.time.LocalDate;

import com.aledguedes.helpdesk.helpdesk.enuns.Prioridade;
import com.aledguedes.helpdesk.helpdesk.enuns.Status;

public class Chamado {

	private Integer id;
	
	private LocalDate dataAbertura = LocalDate.now();
	
	private LocalDate dataFechamento;
	
	private Prioridade prioridade;
	
	private Status status;
	
	private String titulo;
	
	private String observacoes;
}
