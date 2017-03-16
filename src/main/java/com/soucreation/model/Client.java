package com.soucreation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long clientId;
	private String nom;
	private String prenom;
	private String phone;
	private String adresse;
	private String email;
	
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="client")
	private List<Commande> commandes = new ArrayList<>();
	
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Client(String nom, String prenom, String phone, String adresse,String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.phone = phone;
		this.adresse = adresse;
		this.email = email;
	}
	public Client() {
		super();
	}
	
}
