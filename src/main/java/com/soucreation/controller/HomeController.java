package com.soucreation.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.repository.CategorieRepository;
import com.soucreation.repository.OperationRepository;
import com.soucreation.repository.ProduitRepository;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	CategorieRepository catRepo;
	@Autowired
	ProduitRepository prodRepo;
	@Autowired
	OperationRepository opRepo;

	@RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
	public String home(Model model){
		model.addAttribute("categories", catRepo.findAll().size());
		model.addAttribute("produits", prodRepo.findAll().size());
		model.addAttribute("operations", opRepo.findAll().size());
		return "home";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model, Locale locale) {
		return "error";
	}

}
