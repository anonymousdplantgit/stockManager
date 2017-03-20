package com.soucreation.stock.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//row
@Entity
public class CommandeProduit {
	
	private Long idCommandeProduit;
	
	private Commande commande;
	
	private Produit produit;
	
	private Long qte;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdCommandeProduit() {
		return idCommandeProduit;
	}

	public void setIdCommandeProduit(Long idCommandeProduit) {
		this.idCommandeProduit = idCommandeProduit;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="commandeId")
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="produitId")
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Long getQte() {
		return qte;
	}

	public void setQte(Long qte) {
		this.qte = qte;
	}

	
	

}
