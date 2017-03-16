package com.soucreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Client;

public interface ClientRepository  extends JpaRepository<Client,Long>{

}
