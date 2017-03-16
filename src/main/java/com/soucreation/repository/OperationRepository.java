package com.soucreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Operation;
import com.soucreation.model.Produit;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {
	List<Operation> findByProduit(Produit produit);

}
