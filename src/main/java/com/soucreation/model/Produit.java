package com.soucreation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Produit {
	
	private Long produitId;
	@Column(unique=true,nullable=false)
	@NotEmpty
	private String ref;
	private String label;
	private String description;
	private BigDecimal prixAchat;
	private BigDecimal prixVente;
	private Categorie categorie;
	
	private List<Operation> operations = new ArrayList<>();
	
	private List<CommandeProduit> commandeProduits=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getProduitId() {
		return produitId;
	}
	
	@OneToMany(mappedBy="produit")
	public List<CommandeProduit> getCommandeProduits() {
		return commandeProduits;
	}

	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="produit")
	public List<Operation> getOperations() {
		return operations;
	}
	
	@ManyToOne()
	public Categorie getCategorie() {
		return categorie;
	}
	
	
	public BigDecimal getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(BigDecimal prixVente) {
		this.prixVente = prixVente;
	}

	public void setCommandeProduits(List<CommandeProduit> commandeProduits) {
		this.commandeProduits = commandeProduits;
	}


	public Long calculeSum(){
		Long sum=0L;
		for(Operation o : operations)
			sum=sum+o.getQte();
		return sum;
	}
	
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	public Produit(String ref, String label, String description, BigDecimal prixAchat, BigDecimal prixVente,Categorie categorie) {
		super();
		this.ref = ref;
		this.label = label;
		this.description = description;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.categorie = categorie;
	}
	
	public Produit() {
		super();
	}



	@Override
	public String toString() {
		return "Produit [produitId=" + produitId + ", ref=" + ref + ", label=" + label + ", description=" + description
				+ ", prixAchat=" + prixAchat + ", categorie=" + categorie + "]";
	}
	
	
	
	
}
