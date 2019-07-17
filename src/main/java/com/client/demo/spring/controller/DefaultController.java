package com.client.demo.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;




@Controller
public class DefaultController extends BaseController{

	@GetMapping("/")
	public String productForm(Locale locale, Model model) {
		return "redirect:menu";
	}

	@GetMapping("/menu")
	public String menu(Locale locale, Model model) {
		return "menu";
	}
}
