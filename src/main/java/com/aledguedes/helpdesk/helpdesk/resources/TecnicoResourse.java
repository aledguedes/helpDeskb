package com.aledguedes.helpdesk.helpdesk.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aledguedes.helpdesk.helpdesk.domain.Tecnico;
import com.aledguedes.helpdesk.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResourse {

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Tecnico> listById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.listarPorId(id));
	}

	@GetMapping
	public ResponseEntity<List<Tecnico>> listarTodos(){
		return ResponseEntity.ok().body(service.listarTodos());
	}

}
