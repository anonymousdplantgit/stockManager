package com.soucreation.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.stock.model.Commande;

import java.lang.Boolean;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long>{
	
	List<Commande> findByValide(Boolean valide);
	List<Commande> findByDelivred(Boolean delivred);
	List<Commande> findByValideAndDelivred(Boolean valide,Boolean delivred);
}
