package com.soucreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Long> {

}
