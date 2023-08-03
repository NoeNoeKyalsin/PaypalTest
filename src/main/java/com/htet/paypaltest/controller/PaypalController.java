package com.htet.paypaltest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.htet.paypaltest.model.Order;
import com.htet.paypaltest.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaypalController {

	@Autowired
	PaypalService paypalService;
	
	@Value("${host}")
	private String host;
	
	public static final String CANCEL_URL="pay/cancel";
	public static final String SUCCESS_URL="pay/success";
	
	@GetMapping("/")
	public final ModelAndView examInstructions(final HttpServletRequest request){
		System.out.println("checkout landing");
		return new ModelAndView("home");
		
	}
	
	@RequestMapping(value="/pay", method = { RequestMethod.GET, RequestMethod.POST })
	public String payment(@ModelAttribute("order")Order order)
	{
		try {
			Payment payment= paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(), order.getIntent(), order.getDescription(), host+CANCEL_URL, host+SUCCESS_URL);
			for(Links link:payment.getLinks())
			{
				if(link.getRel().equals("approval_url"))
				{
					System.out.println("include approval_url");
					System.out.println("redirect:"+link.getHref());
					return "redirect:"+link.getHref();
				}
			}
		
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@GetMapping(value=CANCEL_URL)
	public final String cancelPay(final HttpServletRequest request){
		return "cancel";
		
	}
	
	@GetMapping(value=SUCCESS_URL)
	public final String successPay(@RequestParam("paymentId") final String paymentId, @RequestParam(value="PayerID") final String PayerID){
		System.out.println("inside success pay");
		try {
			Payment payment= paypalService.executePayment(paymentId,PayerID);
			System.out.println("payment:"+payment);
			if(payment.getState().equals("approved") || payment.getState().equalsIgnoreCase("VERIFIED"))
			{
				return "success";
			}
		
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
		
	}
	
}
