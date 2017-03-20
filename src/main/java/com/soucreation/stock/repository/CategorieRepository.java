package com.soucreation.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.stock.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}
