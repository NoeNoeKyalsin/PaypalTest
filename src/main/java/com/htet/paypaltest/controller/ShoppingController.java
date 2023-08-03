package com.htet.paypaltest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("shopping")
public class ShoppingController {
	
	@RequestMapping(value = "checkout",  method = { RequestMethod.GET, RequestMethod.POST })
	public final ModelAndView examInstructions(final HttpServletRequest request){
		System.out.print("checkout landing");
		return new ModelAndView("checkout");
		
	}
	
	@RequestMapping(value = "authorize_payment",  method = { RequestMethod.GET, RequestMethod.POST })
	public final String authorizePayment(final HttpServletRequest request){
		System.out.print("authorize_payment landing");
		return "forward:/authorize_payment";
	}

}
