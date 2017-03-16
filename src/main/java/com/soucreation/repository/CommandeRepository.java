package com.soucreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande,Long>{

}
