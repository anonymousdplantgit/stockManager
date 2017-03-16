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
import com.soucreation.repository.OperationRepository;
import com.soucreation.repository.ProduitRepository;

@Controller
@RequestMapping(value = "/operations")
public class OperationController {

	private static final Logger logger = LoggerFactory.getLogger(OperationController.class);
	private static final Sort SORTING_DESC = new Sort(Sort.Direction.DESC, "operationId");
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private ProduitRepository produitRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Operation> operationList = null;
		try {
			operationList = operationRepository.findAll(SORTING_DESC);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("operationList", operationList);
		model.addAttribute("produitList", produitRepository.findAll());
		model.addAttribute("operation", new Operation());
		return "operationManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Operation operation) {
		try {
			operation.setDate(new Date());
			Operation savedProduct=operationRepository.saveAndFlush(operation);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("operationList", operationRepository.findAll(SORTING_DESC));
		model.addAttribute("produitList", produitRepository.findAll());
		model.addAttribute("operation", new Operation());
		return "operationManagement";
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
		model.addAttribute("operationList", operationRepository.findAll(SORTING_DESC));
		model.addAttribute("produitList", produitRepository.findAll());
		model.addAttribute("operation", r);
		return "operationManagement";
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id, Model model) {
		try {
			operationRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("operationList", operationRepository.findAll(SORTING_DESC));
		model.addAttribute("produitList", produitRepository.findAll());
		model.addAttribute("operation", new Operation());

		return "operationManagement";
	}
}