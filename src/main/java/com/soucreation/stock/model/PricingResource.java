package com.soucreation.stock.model;

import java.math.BigDecimal;

public class PricingResource {
	
	BigDecimal prixTotal;
	BigDecimal gainTotal;
	
	public BigDecimal getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(BigDecimal prixTotal) {
		this.prixTotal = prixTotal;
	}
	public BigDecimal getGainTotal() {
		return gainTotal;
	}
	public void setGainTotal(BigDecimal gainTotal) {
		this.gainTotal = gainTotal;
	}
	public PricingResource(BigDecimal prixTotal, BigDecimal gainTotal) {
		super();
		this.prixTotal = prixTotal;
		this.gainTotal = gainTotal;
	}
	
	

}
