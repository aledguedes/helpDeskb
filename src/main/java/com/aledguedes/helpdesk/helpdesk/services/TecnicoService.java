package com.aledguedes.helpdesk.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aledguedes.helpdesk.helpdesk.domain.Pessoa;
import com.aledguedes.helpdesk.helpdesk.domain.Tecnico;
import com.aledguedes.helpdesk.helpdesk.dto.TecnicoDTO;
import com.aledguedes.helpdesk.helpdesk.repositories.PessoaRepository;
import com.aledguedes.helpdesk.helpdesk.repositories.TecnicoRepository;
import com.aledguedes.helpdesk.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.aledguedes.helpdesk.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico listarPorId(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}
	
	public List<Tecnico> listarTodos() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validCpfAndEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}

	private void validCpfAndEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
		
	}

	
}
