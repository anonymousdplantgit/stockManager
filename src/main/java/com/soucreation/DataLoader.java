package com.soucreation;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.soucreation.stock.model.Categorie;
import com.soucreation.stock.model.Client;
import com.soucreation.stock.model.Commande;
import com.soucreation.stock.model.CommandeProduit;
import com.soucreation.stock.model.Operation;
import com.soucreation.stock.model.Produit;
import com.soucreation.stock.repository.CategorieRepository;
import com.soucreation.stock.repository.ClientRepository;
import com.soucreation.stock.repository.CommandeProduitRepository;
import com.soucreation.stock.repository.CommandeRepository;
import com.soucreation.stock.repository.OperationRepository;
import com.soucreation.stock.repository.ProduitRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	CategorieRepository catRepository;
	@Autowired
	ProduitRepository proRepository;
	@Autowired
	OperationRepository opeRepository;
	@Autowired
	CommandeRepository cmdRepository;
	@Autowired
	CommandeProduitRepository cmdPrdRepository;
	@Autowired
	ClientRepository cltRepository;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		cltRepository.save(new Client("Abdelmajid", "Boudouh", "0606546562", "Adresse 2 rue de l'adresse","Abdelmajid@Boudouh.com"));
		cltRepository.save(new Client("Youssef", "Ezzahri", "0606546562", "Adresse 2 rue de l'adresse","Youssef@Ezzahri.com"));
		Client client =cltRepository.save(new Client("Souhail", "AIT SAID",  "0606546562","Adresse 2 rue de l'adresse","souhail@aitsaid.com"));
		Categorie autre = catRepository.save(new Categorie("AUTRE", "Autre categorie", "Categorie non referancé"));
		Produit produit1 = proRepository
				.save(new Produit("PRD1", "Produit 1", "Produit 1 de test", BigDecimal.valueOf(RandomUtils.nextDouble(Double.valueOf(15.05),Double.valueOf(20.05))),BigDecimal.valueOf(RandomUtils.nextDouble(Double.valueOf(20.05),Double.valueOf(25.05))), autre));
		Produit produit2 = proRepository
				.save(new Produit("PRD2", "Produit 2", "Produit 2 de test", BigDecimal.valueOf(RandomUtils.nextDouble(Double.valueOf(15.05),Double.valueOf(20.05))),BigDecimal.valueOf(RandomUtils.nextDouble(Double.valueOf(20.05),Double.valueOf(25.05))), autre));
		opeRepository.save(new Operation(100L, new Date(), produit1));
		opeRepository.save(new Operation(-10L, new Date(), produit1));
		opeRepository.save(new Operation(-5L, new Date(), produit1));
		Commande cmd = new Commande();
		cmd.setClient(client);
		cmd.setValide(false);
		cmd.setDateCmd(new Date());
		cmdRepository.save(cmd);
		CommandeProduit cmdPdt= new CommandeProduit();
		cmdPdt.setQte(44L);
		cmdPdt.setCommande(cmd);
		cmdPdt.setProduit(produit1);
		cmd.getCommandeProduits().add(cmdPdt);
		cmdPdt= new CommandeProduit();
		cmdPdt.setQte(55L);
		cmdPdt.setCommande(cmd);
		cmdPdt.setProduit(produit2);
		cmd.getCommandeProduits().add(cmdPdt);
		cmdRepository.save(cmd);
		
		
		

		for (int i = 0; i < 3; i++) {
			Categorie pot = catRepository.save(new Categorie(RandomStringUtils.randomAlphanumeric(10).toUpperCase(),
					RandomStringUtils.randomAlphanumeric(15), RandomStringUtils.randomAlphanumeric(20)));
			for (int j = 0; j < 3; j++) {
				Produit black = proRepository.save(new Produit(RandomStringUtils.randomAlphanumeric(10).toUpperCase(),
						RandomStringUtils.randomAlphanumeric(15), RandomStringUtils.randomAlphanumeric(20),
						BigDecimal.valueOf(RandomUtils.nextDouble(Double.valueOf(15.05),Double.valueOf(20.05))),BigDecimal.valueOf(RandomUtils.nextDouble(Double.valueOf(20.09),Double.valueOf(25.05))), pot));
				Produit whit = proRepository.save(new Produit(RandomStringUtils.randomAlphanumeric(10).toUpperCase(),
						RandomStringUtils.randomAlphanumeric(15), RandomStringUtils.randomAlphanumeric(20),
						BigDecimal.valueOf(RandomUtils.nextDouble(Double.valueOf(15.05),Double.valueOf(20.05))),BigDecimal.valueOf(RandomUtils.nextDouble(Double.valueOf(20.09),Double.valueOf(25.05))), pot));
				opeRepository.save(new Operation(RandomUtils.nextLong(1L,100L)-10, new Date(), black));
				opeRepository.save(new Operation(RandomUtils.nextLong(1L,100L)-10, new Date(), black));
				opeRepository.save(new Operation(RandomUtils.nextLong(1L,100L)-10, new Date(), black));
				opeRepository.save(new Operation(RandomUtils.nextLong(1L,100L)-10, new Date(), whit));
				opeRepository.save(new Operation(RandomUtils.nextLong(1L,100L)-10, new Date(), whit));
				opeRepository.save(new Operation(RandomUtils.nextLong(1L,100L)-10, new Date(), whit));
				Commande c = new Commande();
				c.setClient(client);
				c.setValide(false);
				c.setDelivred(false);
				c.setDateCmd(new Date());
				cmdRepository.save(c);
				CommandeProduit cp= new CommandeProduit();
				cp.setQte(RandomUtils.nextLong(1L, 100L));
				cp.setCommande(c);
				cp.setProduit(black);
				c.getCommandeProduits().add(cp);
				cmdRepository.save(c);
				CommandeProduit cp2= new CommandeProduit();
				cp2.setQte(RandomUtils.nextLong(1L, 100L));
				cp2.setCommande(c);
				cp.setProduit(whit);
				c.getCommandeProduits().add(cp);
				cmdRepository.save(c);
				for (int k = 0; k < 12; k++) {
					opeRepository.save(new Operation(RandomUtils.nextLong(1L, 100L)-20L, new Date(), black));
				}
			}
		}

	}

}
