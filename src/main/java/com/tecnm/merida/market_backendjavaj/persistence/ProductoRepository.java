package com.tecnm.merida.market_backendjavaj.persistence;

import com.tecnm.merida.market_backendjavaj.persistence.crud.ProductoCrudRepository;
import com.tecnm.merida.market_backendjavaj.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getall() {
        //Vamos a "castear"
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
