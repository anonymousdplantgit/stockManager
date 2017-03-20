package com.soucreation.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.stock.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Long> {

}
