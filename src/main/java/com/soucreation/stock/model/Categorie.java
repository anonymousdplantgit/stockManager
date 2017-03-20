package com.soucreation.stock.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categorieId;
	@Column(unique=true,nullable=false)
	@NotEmpty
	private String ref;
	private String label;
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="categorie")
	private List<Produit> produits = new ArrayList<>();
	
	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	public Long getCategorieId() {
		return categorieId;
	}
	public void setCategorieId(Long categorieId) {
		this.categorieId = categorieId;
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
	public Categorie( String ref, String label, String description) {
		super();
		this.ref = ref;
		this.label = label;
		this.description = description;
	}
	public Categorie() {
		super();
	}
	@Override
	public String toString() {
		return "Categorie [categorieId=" + categorieId + ", ref=" + ref + ", label=" + label + ", description="
				+ description + "]";
	}
	
	
	
}
