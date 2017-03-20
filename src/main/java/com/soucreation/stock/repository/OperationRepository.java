package com.soucreation.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.stock.model.Operation;
import com.soucreation.stock.model.Produit;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {
	List<Operation> findByProduit(Produit produit);

}
