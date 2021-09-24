package com.aledguedes.helpdesk.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aledguedes.helpdesk.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
