package com.soucreation.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.stock.model.Client;

public interface ClientRepository  extends JpaRepository<Client,Long>{

}
