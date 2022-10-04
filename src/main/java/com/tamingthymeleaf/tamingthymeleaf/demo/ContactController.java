package com.tamingthymeleaf.tamingthymeleaf.demo;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.tamingthymeleaf.tamingthymeleaf.demo.dao.ContactRepository;
import com.tamingthymeleaf.tamingthymeleaf.demo.model.Contact;

@Controller
@RequestMapping("/public/contact-crud-view")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping
	public String start(Model model) {
		Iterable<Contact> contacts = contactRepository.findAll();
		model.addAttribute("now", new Date().toInstant());
		model.addAttribute("contacts", contacts);
		return "contact-crud-view";
	}

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

	@GetMapping("/find-contact")
	@ResponseBody
	public String findContact(Model model) {

		Random random = new Random();

		List<Contact> contacts = Contact.randomContacts(random.nextInt(2, 10));

		model.addAttribute("contacts", contacts);
		return "contact-crud-view::search-results";
	}

	@PostMapping("/delete-contact/{id}")
	@ResponseBody
	public String deleteContact2(@PathVariable("id") String id, Model model) {
		Random random = new Random();

		List<Contact> contacts = Contact.randomContacts(random.nextInt(2, 10));

		model.addAttribute("contacts", contacts);
		return "contact-crud-view::search-results";
	}

	@PostMapping("/edit-contact/{id}")
	@ResponseBody
	public String editContact(@PathVariable("id") String id, Model model) {

		Context context = new Context();
		context.setVariable("contact", Contact.demoContact());
		String htmlRsp = springTemplateEngine.process("contact-form", context);

		return htmlRsp;
	}

	@GetMapping("/add-new-contact")
	@ResponseBody
	public String addNewContact() {

		Context context = new Context();
		context.setVariable("contact", new Contact());
		String htmlRsp = springTemplateEngine.process("contact-form", context);

		return htmlRsp;
	}

	@PostMapping("/save-contact")
	@ResponseBody
	public String addContact2(@Valid Contact contact, BindingResult result, Model model) {
		Random random = new Random();

		List<Contact> contacts = Contact.randomContacts(random.nextInt(2, 10));

		model.addAttribute("contacts", contacts);
		return "contact-crud-view::search-results";
	}

	@PostMapping("/save-contact/{id}")
	public String updateContact_2(@PathVariable("id") long id, @Valid Contact contact, BindingResult result,
			Model model) {
		return "";
	}

	@GetMapping("/new-contact")
	public String showSignUpForm(Contact contact) {
//		contact.setEmail("");
//		contact.setFirstName("fake first name");
//		contact.setLastName("fake last name");
		return "add-contact";
	}

	@Language("html")
	final String loadHtml = """
			 <tr hx-get="/public/infinite-scroll/page/%d"
			     hx-trigger="revealed"
			     hx-swap="afterend">
			     <td>%s</td>
			     <td>%s</td>
			     <td>%s</td>
			 </tr>
			""";

	@Language("html")
	final String emailHtml = """
			<div class="row" hx-target="this" hx-swap="outerHTML">
				<label for="email">Email</label>
				<input type="text"
					th:field="*{email}" id="email" placeholder="Email"

					hx-post="/public/contact-crud-view/validate-email"
					>
					<span th:if="${#fields.hasErrors('email')}" th:errors="*{email}"></span>
			</div>
			   		""";

	@PostMapping("/validate-email")
	@ResponseBody
	public String validateEmail(@Valid Contact contact, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return """
					<span>ERROR!</span>

							}
										""";
		} else {
			return """
					<span ></span>

							}
										""";
		}
	}

	@PostMapping("/add-contact")
	public String addContact(@Valid Contact contact, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-contact";
		}

		contactRepository.save(contact);
		return "redirect:/public/contact-crud-view";
		// return "contact-crud-view";
	}

//	@GetMapping("/contact-crud-view")
//	public String showContactList(Model model) {
//		model.addAttribute("users", contactRepository.findAll());
//		return "contact-crud-view";
//	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));

		model.addAttribute("contact", contact);
		return "update-contact";
	}

	@PostMapping("/update/{id}")
	public String updateContact(@PathVariable("id") long id, @Valid Contact contact, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			contact.setId(id);
			return "update-contact";
		}

		contactRepository.save(contact);
		return "redirect:/public/contact-crud-view";
	}

	@GetMapping("/delete/{id}")
	public String deleteContact(@PathVariable("id") long id, Model model) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
		contactRepository.delete(contact);
		return "redirect:/public/contact-crud-view";
	}

	// additional CRUD methods
}