package com.enotes.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.enotes.entity.UserDtls;
import com.enotes.repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode; 
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup()
	{
		return "signup";
	}
	
	@GetMapping("/about")
	public String about()
	{
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact()
	{
		return "contact";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute UserDtls user,Model m, HttpSession session)
	{
		
		user.setPassword(user.getPassword());
		user.setRole("ROLE_USER");
		
		UserDtls u=userRepo.save(user);
		
		if(u!=null) {
			session.setAttribute("msg", "Registration Successfully");
		}else {
			session.setAttribute("msg", "Something wrong on server");
		}
		
		return "redirect:/signup";
	}

}
