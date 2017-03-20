package com.soucreation.stock.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.stock.model.Fournisseur;
import com.soucreation.stock.repository.FournisseurRepository;

@Controller
@RequestMapping(value = "/fournisseurs")
public class FournissseurController {

	private static final Logger logger = LoggerFactory.getLogger(FournissseurController.class);
	private static final Sort SORTING_DESC = new Sort(Sort.Direction.DESC, "fournisseurId");
	
	@Autowired
	private FournisseurRepository fournisseurRepository;
	


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Fournisseur> fournisseurList = null;
		try {
			fournisseurList = fournisseurRepository.findAll(SORTING_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("fournisseurList", fournisseurList);
		model.addAttribute("fournisseur", new Fournisseur());
		return "stock/fournisseurManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Fournisseur fournisseur) {
		try {
			fournisseurRepository.save(fournisseur);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("fournisseurList", fournisseurRepository.findAll(SORTING_DESC));
		model.addAttribute("fournisseur", new Fournisseur());
		return "stock/fournisseurManagement";
	}

	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id) {
		Fournisseur r = null;
		try {
			r = fournisseurRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("fournisseurList", fournisseurRepository.findAll(SORTING_DESC));
		model.addAttribute("fournisseur", r);
		return "stock/fournisseurManagement";
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id, Model model) {
		try {
			fournisseurRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("fournisseurList", fournisseurRepository.findAll(SORTING_DESC));
		model.addAttribute("fournisseur", new Fournisseur());

		return "stock/fournisseurManagement";
	}
}