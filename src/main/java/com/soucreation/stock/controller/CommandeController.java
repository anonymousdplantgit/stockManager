package com.soucreation.stock.controller;

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

import com.soucreation.stock.model.Commande;
import com.soucreation.stock.model.CommandeProduit;
import com.soucreation.stock.model.Operation;
import com.soucreation.stock.model.PricingResource;
import com.soucreation.stock.model.Produit;
import com.soucreation.stock.repository.ClientRepository;
import com.soucreation.stock.repository.CommandeRepository;
import com.soucreation.stock.repository.OperationRepository;
import com.soucreation.stock.repository.ProduitRepository;

@Controller
@RequestMapping(value = "/commandes")
public class CommandeController extends GlobalController{

	@Autowired
	CommandeRepository commandeRepository;

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OperationRepository operationRepository;

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
		model.addAttribute("commandeListToValidate", buildMap(commandeRepository.findByValide(false)));
		model.addAttribute("commandeListToDeliver", buildMap(commandeRepository.findByValideAndDelivred(true, false)));
		model.addAttribute("commandeList", buildMap(commandeRepository.findByValideAndDelivred(true, true)));
		return "stock/commandes";
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
		return "stock/commandeView";
	}

	@RequestMapping(value = "/managCmd", method = RequestMethod.POST)
	public String save(Model model, Commande commande) {
		try {
			List<CommandeProduit> cmdPrdList = commande.getCommandeProduits();
			commande.setCommandeProduits(new ArrayList<CommandeProduit>());
			commande.setDateCmd(new Date());
			commande.setValide(false);
			commande = commandeRepository.save(commande);
			for (CommandeProduit cp : cmdPrdList) {
				cp.setCommande(commande);
				commande.getCommandeProduits().add(cp);
			}
			commandeRepository.save(commande);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("commandeList", buildMap(commandeRepository.findAll()));
		return "stock/commandes";
	}

	@RequestMapping(value = "/managCmd", params = { "addRow" })
	public String addRow(Model model, Commande commande) {
		model.addAttribute("produitList", produitRepository.findAll());
		model.addAttribute("clientList", clientRepository.findAll());
		commande.getCommandeProduits().add(new CommandeProduit());
		return "stock/commandeView";
	}

	@RequestMapping(value = "/managCmd", params = { "removeRow" })
	public String removeRow(Model model, Commande commande, final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		model.addAttribute("produitList", produitRepository.findAll());
		model.addAttribute("clientList", clientRepository.findAll());
		commande.getCommandeProduits().remove(rowId.intValue());
		return "stock/commandeView";
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id, Model model) {
		try {
			commandeRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("commandeListToValidate", buildMap(commandeRepository.findByValide(false)));
		model.addAttribute("commandeListToDeliver", buildMap(commandeRepository.findByValideAndDelivred(true, false)));
		model.addAttribute("commandeList", buildMap(commandeRepository.findByValideAndDelivred(true, true)));
		return "stock/commandes";
	}

	@RequestMapping(value = "/invoice{id}", method = RequestMethod.GET)
	public String invoice(Long id, Model model) {
		model.addAttribute("commande", commandeRepository.findOne(id));
		return "stock/invoiceView";
	}
	
	@RequestMapping(value = "/deliver{id}", method = RequestMethod.GET)
	public String deliver(Long id, Model model) {
		Commande commande =  commandeRepository.findOne(id);
		commande.setDelivred(true);
		commandeRepository.save(commande);
		model.addAttribute("commandeListToValidate", buildMap(commandeRepository.findByValide(false)));
		model.addAttribute("commandeListToDeliver", buildMap(commandeRepository.findByValideAndDelivred(true, false)));
		model.addAttribute("commandeList", buildMap(commandeRepository.findByValideAndDelivred(true, true)));
		return "stock/commandes";
	}

	@RequestMapping(value = "/validate{id}", method = RequestMethod.GET)
	public String validate(Long id, Model model) {
		Commande commande = commandeRepository.findOne(id);

		try {
			if (!commande.getValide()) {
				for (CommandeProduit cp : commande.getCommandeProduits()) {
					if (cp.getQte() > getQteStockProduit(cp.getProduit().getProduitId())) {
						throw new Exception("Insuffisant stock pour le Produit" + cp.getProduit().getRef());
					}

				}
				for (CommandeProduit cp : commande.getCommandeProduits()) {
					Operation o = new Operation(cp.getQte(), new Date(), cp.getProduit());
					operationRepository.save(o);
				}
				commande.setValide(true);
				commandeRepository.save(commande);
				model.addAttribute("messageOK", messages.get(VALIDATE));
			}
		} catch (Exception e) {
			model.addAttribute("messageKO", messages.get(NOT_VALIDATE));
		}
		model.addAttribute("commande", commande);
		return "stock/invoiceView";
	}

	private Long getQteStockProduit(Long produitId) {
		Produit p = produitRepository.getOne(produitId);
		return p.calculateQteStock();
	}

	@RequestMapping(value = "/print{id}", method = RequestMethod.GET)
	public String print(Long id, Model model) {
		return "stock/invoicePrint";
	}

	private Map<Commande, PricingResource> buildMap(List<Commande> commandeList) {
		Map<Commande, PricingResource> commandMap = new HashMap<>();
		for (Commande c : commandeList) {
			commandMap.put(c, calculeSommeCommande(c));
		}
		return commandMap;
	}

	private PricingResource calculeSommeCommande(Commande commande) {
		PricingResource pricingResource = new PricingResource(BigDecimal.ZERO, BigDecimal.ZERO);
		for (CommandeProduit cp : commande.getCommandeProduits()) {
			pricingResource.setPrixTotal(pricingResource.getPrixTotal()
					.add(cp.getProduit().getPrixVente().multiply(BigDecimal.valueOf(cp.getQte()))));
			pricingResource.setGainTotal(pricingResource.getGainTotal()
					.add((cp.getProduit().getPrixVente().subtract(cp.getProduit().getPrixAchat()))
							.multiply(BigDecimal.valueOf(cp.getQte()))));
		}
		return pricingResource;
	}
}
