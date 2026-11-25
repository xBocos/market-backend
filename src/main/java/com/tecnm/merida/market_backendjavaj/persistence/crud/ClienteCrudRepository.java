package com.tecnm.merida.market_backendjavaj.persistence.crud;

import com.tecnm.merida.market_backendjavaj.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, String> {
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);
}