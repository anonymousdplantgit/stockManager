package com.soucreation.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.stock.model.CommandeProduit;

public interface CommandeProduitRepository  extends JpaRepository<CommandeProduit,Long>{

}
