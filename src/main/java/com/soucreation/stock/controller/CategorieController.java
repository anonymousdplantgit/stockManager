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

import com.soucreation.stock.model.Categorie;
import com.soucreation.stock.repository.CategorieRepository;

@Controller
@RequestMapping(value = "/categories")
public class CategorieController {

	private static final Logger logger = LoggerFactory.getLogger(CategorieController.class);
	private static final Sort SORTING_DESC = new Sort(Sort.Direction.DESC, "categorieId");
	
	@Autowired
	private CategorieRepository categorieRepository;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Categorie> categorieList = null;
		try {
			categorieList = categorieRepository.findAll(SORTING_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("categorieList", categorieList);
		model.addAttribute("categorie", new Categorie());
		return "stock/categorieManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Categorie categorie) {
		try {
			categorieRepository.save(categorie);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("categorieList", categorieRepository.findAll(SORTING_DESC));
		model.addAttribute("categorie", new Categorie());
		return "stock/categorieManagement";
	}

	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id) {
		Categorie r = null;
		try {
			r = categorieRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("categorieList", categorieRepository.findAll(SORTING_DESC));
		model.addAttribute("categorie", r);
		return "stock/categorieManagement";
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id, Model model) {
		try {
			categorieRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("categorieList", categorieRepository.findAll(SORTING_DESC));
		model.addAttribute("categorie", new Categorie());

		return "stock/categorieManagement";
	}
}