package com.soucreation.stock.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
//SeedStarter
@Entity
public class Commande {
	
	private Long commandeId;
	
	private Date dateCmd;
	@DateTimeFormat (pattern="MM/dd/yyyy")
	private Date dateLivraison;
	
	private Boolean valide;
	
	private Boolean delivred;
	
	private List<CommandeProduit> commandeProduits=new ArrayList<>();
	
	private Client client;
	

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public void addProduit(Produit produit,Long qte){
		CommandeProduit commandeProduit = new CommandeProduit();
		commandeProduit.setCommande(this);
		commandeProduit.setProduit(produit);
		commandeProduit.setQte(qte);
		this.commandeProduits.add(commandeProduit);
		produit.getCommandeProduits().add(commandeProduit);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getCommandeId() {
		return commandeId;
	}
	
	@OneToMany(mappedBy="commande",cascade={CascadeType.MERGE,CascadeType.REMOVE})
	public List<CommandeProduit> getCommandeProduits() {
		return commandeProduits;
	}
	
	@ManyToOne()
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	public void setCommandeId(Long commandeId) {
		this.commandeId = commandeId;
	}
	public void setCommandeProduits(List<CommandeProduit> commandeProduits) {
		this.commandeProduits = commandeProduits;
	}
	
	public Boolean getDelivred() {
		return delivred;
	}

	public void setDelivred(Boolean delivred) {
		this.delivred = delivred;
	}

	public Date getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}
	public Boolean getValide() {
		return valide;
	}
	public void setValide(Boolean valide) {
		this.valide = valide;
	}
	
	

}
