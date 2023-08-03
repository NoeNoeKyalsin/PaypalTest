package com.htet.paypaltest.general;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.*;

@Configuration
public class PaypalConfig {
	
	@Value("${paypal.mode}")
	private String mode;
	@Value("${paypal.secret}")
	private String secret;
	@Value("${paypal.clientId}")
	private String clientId;	
	
	@Bean
	public Map<String, String> paypalSdkConfig()
	{
		Map<String, String> configMap= new HashMap<>();
		configMap.put("mode", mode);
		configMap.put("clientId", clientId);
		configMap.put("secret", secret);
		
		return configMap;
		
	}
	
	@Bean
	public OAuthTokenCredential oAuthTokenCredential()
	{
		return new OAuthTokenCredential(clientId,secret,paypalSdkConfig());
	}
	
	@Bean
	public APIContext apiContext() throws PayPalRESTException
	{
		APIContext context= new APIContext(oAuthTokenCredential().getAccessToken());
		context.setConfigurationMap(paypalSdkConfig());
		return context;
	}

}
