package com.aledguedes.helpdesk.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aledguedes.helpdesk.helpdesk.domain.Pessoa;
import com.aledguedes.helpdesk.helpdesk.domain.Cliente;
import com.aledguedes.helpdesk.helpdesk.dto.ClienteDTO;
import com.aledguedes.helpdesk.helpdesk.repositories.PessoaRepository;
import com.aledguedes.helpdesk.helpdesk.repositories.ClienteRepository;
import com.aledguedes.helpdesk.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.aledguedes.helpdesk.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente listarPorId(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}

	public List<Cliente> listarTodos() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validCpfAndEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id); // setar o ID que veio do front
		Cliente oldObj = listarPorId(id); // chamo a função listar por ID
		validCpfAndEmail(objDTO);
		oldObj = new Cliente(objDTO); // se chegar aqui é pq as excessões dos outros métodps estão ok

		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = listarPorId(id);
		
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui Ordens e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private void validCpfAndEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}

	}
}
