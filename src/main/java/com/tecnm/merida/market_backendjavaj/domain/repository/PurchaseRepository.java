package com.tecnm.merida.market_backendjavaj.domain.repository;

import com.tecnm.merida.market_backendjavaj.domain.Purchase;
import com.tecnm.merida.market_backendjavaj.persistence.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}
