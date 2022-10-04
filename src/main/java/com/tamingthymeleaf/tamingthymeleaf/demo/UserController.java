package com.tamingthymeleaf.tamingthymeleaf.demo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public/user-crud-view")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String start(Model model) {
		Iterable<User> users = userRepository.findAll();
		model.addAttribute("now", new Date().toInstant());
		model.addAttribute("users", users);
		return "user-crud-view";
	}

	@GetMapping("/signup")
	public String showSignUpForm(User user) {
		user.setEmail("email@domain.com");
		user.setName("fake name");
		return "add-user";
	}

	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}

		userRepository.save(user);
		return "redirect:/public/user-crud-view";
		// return "user-crud-view";
	}

//	@GetMapping("/user-crud-view")
//	public String showUserList(Model model) {
//		model.addAttribute("users", userRepository.findAll());
//		return "user-crud-view";
//	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		model.addAttribute("user", user);
		return "update-user";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}

		userRepository.save(user);
		return "redirect:/public/user-crud-view";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		return "redirect:/public/user-crud-view";
	}

	// additional CRUD methods
}