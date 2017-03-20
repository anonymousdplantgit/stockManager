package com.soucreation.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;

import com.soucreation.Messages;

@Controller
public class GlobalController {
	
	@Autowired
	Messages messages;
	
	private MessageSourceAccessor accessor;
	
	final static String PRODUCT_CONSTRAINT ="product.constraint.exception";
	final static String AUTHER ="other.exception";
	final static String CATEGORY_CONSTRAINT ="category.constraint.exception";
	final static String DELETE ="message.success.delete";
	final static String UPDATE ="message.success.update";
	final static String SAVE ="message.success.save";
	final static String VALIDATE ="message.success.validateorder";
	final static String NOT_VALIDATE ="message.success.notvalidateorder";
	final static String DELIVER ="message.success.deliverorder";
	

}
