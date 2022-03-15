package com.ofss;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="wrongUser", method=RequestMethod.GET)
	public String wrongUserPage()
	{
		System.out.println("wrongUserPage method is called");
		return "wrongUser"; // wrongUser.jsp
	}
	
	@RequestMapping(value="public", method=RequestMethod.GET)
	public String publicPage()
	{
		System.out.println("returning public view");
		return "public";

	}

	@RequestMapping(value="private", method=RequestMethod.GET)
	public String privatePage(Principal principal, ModelMap map)
	{
		String msg="Welcome "+principal.getName()+" you are an authenticated user, your role(s) is/are: ";
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails=(UserDetails) auth.getPrincipal();
//		userDetails.getAuthorities(); // this method will return me a list of roles that the logged in user belongs to
		
		for (GrantedAuthority o:userDetails.getAuthorities())
		{
			System.out.println("The role/authority "+o.getAuthority());
			msg=msg+" "+o.getAuthority();
		}
		
		
		
		
		System.out.println("returning private view");
		map.addAttribute("msg",msg);
		return "private";
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String just()
	{
		return "home";
	}
}
