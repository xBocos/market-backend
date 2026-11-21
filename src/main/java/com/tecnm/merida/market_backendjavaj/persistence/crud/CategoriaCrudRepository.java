package com.tecnm.merida.market_backendjavaj.persistence.crud;

import com.tecnm.merida.market_backendjavaj.persistence.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {
}