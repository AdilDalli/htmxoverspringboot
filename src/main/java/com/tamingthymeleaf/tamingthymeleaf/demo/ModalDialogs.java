package com.tamingthymeleaf.tamingthymeleaf.demo;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tamingthymeleaf.tamingthymeleaf.demo.model.Contact;

@Controller
@RequestMapping("/public/modal-dialogs")
public class ModalDialogs {

	@GetMapping
	public String start(Model model) {

		return "modal/modal-view";
	}

	@GetMapping("/modal")
	public String showModal() {
		// contact.setEmail("");
//		contact.setFirstName("fake first name");
//		contact.setLastName("fake last name");
		return "modal/modal";
	}

	@GetMapping("/modal-2")
	public String showModal2() {
		// contact.setEmail("");
//		contact.setFirstName("fake first name");
//		contact.setLastName("fake last name");
		return "modal/modal2";
	}
}
