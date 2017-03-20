package com.soucreation.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.stock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {

}
