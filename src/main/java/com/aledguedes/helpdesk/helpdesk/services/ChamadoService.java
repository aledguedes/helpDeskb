package com.aledguedes.helpdesk.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aledguedes.helpdesk.helpdesk.domain.Chamado;
import com.aledguedes.helpdesk.helpdesk.repositories.ChamadoRepository;
import com.aledguedes.helpdesk.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;

	public Chamado listarPorId(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}
	
	public List<Chamado> listarTodos() {
		return repository.findAll();
	}
	
}
