package com.soucreation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Operation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long operationId;
	@NotNull
	private Long qte;
	private Date date;

	@ManyToOne()
	private Produit produit;

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public Long getQte() {
		return qte;
	}

	public void setQte(Long qte) {
		this.qte = qte;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return "Operation [operationId=" + operationId + ", qte=" + qte + ", date=" + date + ", produit=" + produit
				+ "]";
	}

	public Operation(Long qte, Date date, Produit produit) {
		super();
		this.qte = qte;
		this.date = date;
		this.produit = produit;
	}

	public Operation() {
		super();
	}

}
