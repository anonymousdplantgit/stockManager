package com.soucreation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.Produit;
import com.soucreation.repository.CategorieRepository;
import com.soucreation.repository.ProduitRepository;

@Controller
@RequestMapping(value = "/produits")
public class ProduitController {

	private static final Logger logger = LoggerFactory.getLogger(ProduitController.class);
	private static final Sort SORTING_DESC = new Sort(Sort.Direction.DESC, "produitId");
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Produit> produitList = null;
		try {
			produitList = produitRepository.findAll(SORTING_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("produitList", produitList);
		model.addAttribute("categorieList", categorieRepository.findAll());
		model.addAttribute("produit", new Produit());
		return "produitManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Produit produit) {
		try {
			Produit savedProduct=produitRepository.saveAndFlush(produit);
//			savedProduct.getCategorie().getProduits().add(savedProduct);
//			categorieRepository.save(savedProduct.getCategorie());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("produitList", produitRepository.findAll(SORTING_DESC));
		model.addAttribute("categorieList", categorieRepository.findAll());
		model.addAttribute("produit", new Produit());
		return "produitManagement";
	}

	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id) {
		Produit r = null;
		try {
			r = produitRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("produitList", produitRepository.findAll(SORTING_DESC));
		model.addAttribute("categorieList", categorieRepository.findAll());
		model.addAttribute("produit", r);
		return "produitManagement";
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id, Model model) {
		try {
			produitRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("produitList", produitRepository.findAll(SORTING_DESC));
		model.addAttribute("categorieList", categorieRepository.findAll());
		model.addAttribute("produit", new Produit());

		return "produitManagement";
	}
}