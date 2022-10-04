package com.tamingthymeleaf.tamingthymeleaf.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tamingthymeleaf.tamingthymeleaf.demo.model.Contact;

import java.util.Date;

@Controller
@RequestMapping("/public/tabs")
public class Tabs {

	@GetMapping
	public String start(Model model) {
		model.addAttribute("contact", Contact.demoContact());
		model.addAttribute("now", new Date().toInstant());

		return "tabs/tabs";
	}

	@GetMapping("/tab1")
	public String tab1(Model model) {
		return "tabs/tab-content";
	}

	@GetMapping("/tab2")
	public String tab2(Model model) {
		return "tabs/tab-content";
	}

	@GetMapping("/tab3")
	public String tab3(Model model) {
		return "tabs/tab-content";
	}

	@PostMapping("/edit/{id}")
	public String editForm(Contact contact, Model model, @PathVariable String id) {
		model.addAttribute("contact", contact);
		model.addAttribute("id", id);
		return "click-to-edit-form";
	}

	@PostMapping("/commit")
	public String editPost(Contact contact, Model model) {
		model.addAttribute("contact", contact);
		return "click-to-edit-default";
	}

}
