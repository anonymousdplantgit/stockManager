package com.soucreation.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.Operation;
import com.soucreation.model.Produit;
import com.soucreation.repository.OperationRepository;
import com.soucreation.repository.ProduitRepository;

@Controller
@RequestMapping(value = "/produitOperations")
public class ProduitOperationController {

	private static final Logger logger = LoggerFactory.getLogger(ProduitOperationController.class);
	private static final Sort SORTING_DESC = new Sort(Sort.Direction.DESC, "operationId");
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private ProduitRepository produitRepository;

	@RequestMapping(value = "/produit{id}", method = RequestMethod.GET)
	public String home(Model model,Long id) {
		List<Operation> operationList = null;
		Produit produit = null;
		try {
			produit =produitRepository.findOne(id);
			operationList = operationRepository.findByProduit(produit);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		Operation operation= new Operation();
		operation.setProduit(produit);
		model.addAttribute("operationList", operationList);
		model.addAttribute("operation", operation);
		return "produitOperationManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Operation operation) {
		Operation savedOperation = null;
		try {
			operation.setDate(new Date());
			savedOperation=operationRepository.saveAndFlush(operation);
			operation= new Operation();
			operation.setProduit(savedOperation.getProduit());
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("operationList", operationRepository.findByProduit(savedOperation.getProduit()));
		model.addAttribute("operation", operation);
		
		return "produitOperationManagement";
	}

	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id) {
		Operation r = null;
		try {
			r = operationRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("operationList", operationRepository.findByProduit(r.getProduit()));
		model.addAttribute("operation", r);
		return "produitOperationManagement";
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id, Model model) {
		Operation o = null;
		Produit p = null;
		try {
			p = operationRepository.findOne(id).getProduit();
			operationRepository.delete(id);
			o= new Operation();
			o.setProduit(p);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("operationList", operationRepository.findByProduit(o.getProduit()));
		model.addAttribute("operation", o);
		

		return "produitOperationManagement";
	}
}