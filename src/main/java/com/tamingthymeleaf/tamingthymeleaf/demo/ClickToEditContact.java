package com.tamingthymeleaf.tamingthymeleaf.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tamingthymeleaf.tamingthymeleaf.demo.dao.ContactRepository;
import com.tamingthymeleaf.tamingthymeleaf.demo.model.Contact;

import java.util.Date;

@Controller
@RequestMapping("/public/click-to-edit-contact")
public class ClickToEditContact {

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping
	public String start(Model model, @SortDefault.SortDefaults({ @SortDefault("lastName"),
			@SortDefault("lastName") }) Pageable pageable) {

		model.addAttribute("now", new Date().toInstant());
		model.addAttribute("contacts", contactRepository.findAll(pageable));
		return "contact-editor/contact-list";
	}

	@GetMapping("/new-contact")
	public String showSignUpForm(Contact contact) {
		// contact.setEmail("");
//		contact.setFirstName("fake first name");
//		contact.setLastName("fake last name");
		return "contact-editor/contact-form";
	}

	@PostMapping("/find-contact")
	public String findContact(@RequestParam("search") String search, Model model, @SortDefault.SortDefaults({ @SortDefault("lastName"),
			@SortDefault("lastName") }) Pageable pageable) {

		if (search == null || search.isBlank()) {
			model.addAttribute("contacts", contactRepository.findAll(pageable));
		} else {
			model.addAttribute("contacts", contactRepository.findAllByText(search, pageable));
		}

		return "contact-editor/contact-list::search-results";
	}

	@PostMapping("/edit/{id}")
	public String editForm(Contact contact, Model model, @PathVariable String id) {
		model.addAttribute("contact", contactRepository.findById(contact.getId()).get());
		model.addAttribute("id", id);
		return "contact-editor/contact-form";
	}

	@PostMapping("/edit-modal/{id}")
	public String editFormModal(Contact contact, Model model, @PathVariable String id) {
		model.addAttribute("contact", contactRepository.findById(contact.getId()).get());
		model.addAttribute("id", id);
		return "contact-editor/contact-form-modal";
	}

	@PostMapping("/view/{id}")
	public String viewForm(Contact contact, Model model, @PathVariable String id) {
		model.addAttribute("contact", contact);
		model.addAttribute("id", id);
		return "contact-editor/contact-view";
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteContact(@PathVariable("id") String id, Model model) {

		try {
			contactRepository.deleteById(Long.valueOf(id).longValue());
			return "";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	// @PostMapping("/commit")
	@RequestMapping(value = "/commit", method = RequestMethod.POST, params = "submit")
	public String commit(Contact contact, Model model, @SortDefault.SortDefaults({ @SortDefault("lastName"),
			@SortDefault("lastName") }) Pageable pageable) {
		contactRepository.save(contact);
		Iterable<Contact> contacts = contactRepository.findAll(pageable);
		model.addAttribute("now", new Date().toInstant());
		model.addAttribute("contacts", contacts);
		return "contact-editor/contact-list::contact-search-add-list";
	}

	@RequestMapping(value = "/commit", method = RequestMethod.POST, params = "cancel")
	public String cancel(Model model, @SortDefault.SortDefaults({ @SortDefault("lastName"),@SortDefault("firstName") }) Pageable pageable) {
		Iterable<Contact> contacts = contactRepository.findAll(pageable);
		model.addAttribute("now", new Date().toInstant());
		model.addAttribute("contacts", contacts);
		return "contact-editor/contact-list::contact-search-add-list";
	}
}
