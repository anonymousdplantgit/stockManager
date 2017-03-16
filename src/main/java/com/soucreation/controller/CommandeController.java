package com.soucreation.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.Commande;
import com.soucreation.model.CommandeProduit;
import com.soucreation.model.PricingResource;
import com.soucreation.model.Produit;
import com.soucreation.repository.ClientRepository;
import com.soucreation.repository.CommandeRepository;
import com.soucreation.repository.ProduitRepository;

@Controller
@RequestMapping(value = "/commandes")
public class CommandeController {
	
	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Commande> commandeList = null;
		try {
			commandeList = commandeRepository.findAll();
			
			buildMap(commandeList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("commandeList", buildMap(commandeList));
		return "commandes";
	}
	
	@RequestMapping(value = "/managCmd", method = RequestMethod.GET)
	public String managCmd(Model model) {
		List<Produit> produitList = null;
		try {
			produitList = produitRepository.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("produitList", produitList);
		model.addAttribute("clientList", clientRepository.findAll());
		model.addAttribute("commande", new Commande());
		return "commandeView";
	}
	
	@RequestMapping(value = "/managCmd", method = RequestMethod.POST)
	public String save(Model model,Commande commande) {
		try {
			List<CommandeProduit> cmdPrdList=commande.getCommandeProduits();
			commande.setCommandeProduits(new ArrayList<CommandeProduit>());
			commande.setDateCmd(new Date());
			commande.setValide(false);
			commande = commandeRepository.save(commande);
			for(CommandeProduit cp : cmdPrdList){
				cp.setCommande(commande);
				commande.getCommandeProduits().add(cp);
			}
			commandeRepository.save(commande);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("commandeList", buildMap(commandeRepository.findAll()));
		return "commandes";
	}
	
	@RequestMapping(value = "/managCmd", params={"addRow"})
	public String addRow(Model model,Commande commande) {
		model.addAttribute("produitList",  produitRepository.findAll());
		model.addAttribute("clientList", clientRepository.findAll());
		commande.getCommandeProduits().add(new CommandeProduit());
		return "commandeView";
	}
	
	@RequestMapping(value = "/managCmd", params={"removeRow"})
	public String removeRow(Model model,Commande commande,final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		model.addAttribute("produitList",  produitRepository.findAll());
		model.addAttribute("clientList", clientRepository.findAll());
		commande.getCommandeProduits().remove(rowId.intValue());
		return "commandeView";
	}
	
	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id, Model model) {
		try {
			commandeRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("commandeList", buildMap(commandeRepository.findAll()));
		return "commandes";
	}
	
	
	@RequestMapping(value = "/invoice{id}", method = RequestMethod.GET)
	public String invoice(Long id, Model model) {
		model.addAttribute("commande", commandeRepository.findOne(id));
		return "invoiceView";
	}
	
	@RequestMapping(value = "/print{id}", method = RequestMethod.GET)
	public String print(Long id, Model model) {
		return "invoicePrint";
	}

	private Map<Commande,PricingResource> buildMap(List<Commande> commandeList) {
		Map<Commande,PricingResource> commandMap=new HashMap<>();
		for(Commande c : commandeList){
			commandMap.put(c, calculeSommeCommande(c));
		}
		return commandMap;
	}
	
	
	
	private PricingResource calculeSommeCommande(Commande commande) {
		PricingResource pricingResource=new PricingResource( BigDecimal.ZERO,  BigDecimal.ZERO);
			for(CommandeProduit cp : commande.getCommandeProduits()){
				pricingResource.setPrixTotal( pricingResource.getPrixTotal().add(
						cp.getProduit().getPrixVente().multiply(
								BigDecimal.valueOf(cp.getQte()))));
				pricingResource.setGainTotal(pricingResource.getGainTotal().add(
						(cp.getProduit().getPrixVente().subtract(cp.getProduit().getPrixAchat())).multiply(
								BigDecimal.valueOf(cp.getQte()))));
			}
		return pricingResource;
	}
}
