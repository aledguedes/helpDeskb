package com.aledguedes.helpdesk.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aledguedes.helpdesk.helpdesk.domain.Chamado;
import com.aledguedes.helpdesk.helpdesk.domain.Cliente;
import com.aledguedes.helpdesk.helpdesk.domain.Tecnico;
import com.aledguedes.helpdesk.helpdesk.dto.ChamadoDTO;
import com.aledguedes.helpdesk.helpdesk.enuns.Prioridade;
import com.aledguedes.helpdesk.helpdesk.enuns.Status;
import com.aledguedes.helpdesk.helpdesk.repositories.ChamadoRepository;
import com.aledguedes.helpdesk.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	@Autowired
	private TecnicoService tecService;
	
	@Autowired
	private ClienteService cliService;

	public Chamado listarPorId(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}
	
	public List<Chamado> listarTodos() {
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		return repository.save(newChamado(objDTO));
	}
	

	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = listarPorId(id);
		oldObj = newChamado(objDTO);
		return repository.save(oldObj);
	}
	
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecService.listarPorId(obj.getTecnico());
		Cliente cliente = cliService.listarPorId(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		
		return chamado;
	}

	
}
