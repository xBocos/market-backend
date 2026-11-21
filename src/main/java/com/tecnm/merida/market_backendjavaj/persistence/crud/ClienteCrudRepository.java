package com.tecnm.merida.market_backendjavaj.persistence.crud;

import com.tecnm.merida.market_backendjavaj.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteCrudRepository extends CrudRepository<Cliente, String> {
}