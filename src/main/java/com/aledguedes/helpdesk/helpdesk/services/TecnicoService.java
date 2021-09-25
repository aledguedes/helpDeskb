package com.aledguedes.helpdesk.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aledguedes.helpdesk.helpdesk.domain.Tecnico;
import com.aledguedes.helpdesk.helpdesk.dto.TecnicoDTO;
import com.aledguedes.helpdesk.helpdesk.repositories.TecnicoRepository;
import com.aledguedes.helpdesk.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico listarPorId(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}
	
	public List<Tecnico> listarTodos() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}

	
}
