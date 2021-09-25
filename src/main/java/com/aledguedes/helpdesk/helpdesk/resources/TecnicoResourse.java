package com.aledguedes.helpdesk.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aledguedes.helpdesk.helpdesk.domain.Tecnico;
import com.aledguedes.helpdesk.helpdesk.dto.TecnicoDTO;
import com.aledguedes.helpdesk.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResourse {

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> listById(@PathVariable Integer id) {
		Tecnico obj = service.listarPorId(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> listarTodos(){
		List<Tecnico> list = service.listarTodos();
		List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

}
