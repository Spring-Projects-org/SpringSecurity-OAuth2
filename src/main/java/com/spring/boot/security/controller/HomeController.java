package com.spring.boot.security.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
	
	
	@RequestMapping(value="/")
	public String defaultHome()
	{
		
		return "redirect:home";
		
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(Principal principal,Model model) throws SQLException
	{
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
        Map<String, String> details = new LinkedHashMap<>();
        details = (Map<String, String>) authentication.getDetails();
        
        model.addAttribute("username", details.get("name"));
        model.addAttribute("email", details.get("email"));
        model.addAttribute("picture", details.get("picture"));
        //Map<String, String> map = new LinkedHashMap<>();
        //map.put("email", details.get("email"));
		
        return "home";
		
	}
	
	
	@RequestMapping(value="/logout-success")
	public String logoutPage()
	{
		
		return "logout";
		
		
	}
	
	@RequestMapping("user")
	@ResponseBody
	public Principal user (Principal principal)
	{
		
		return principal;
		
		
	}

}
