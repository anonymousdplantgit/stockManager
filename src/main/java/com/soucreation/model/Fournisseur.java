package com.soucreation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fournisseur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fournisseurId;
	private String nom;
	private String prenom;
	private String phone;
	private String adresse;
	public Long getFournisseurId() {
		return fournisseurId;
	}
	public void setFournisseurId(Long fournisseurId) {
		this.fournisseurId = fournisseurId;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
}
