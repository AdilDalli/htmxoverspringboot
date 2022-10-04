package com.tamingthymeleaf.tamingthymeleaf.demo.model;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "FName is mandatory")
	private String firstName;
	@NotBlank(message = "LName is mandatory")
	private String lastName;
	@NotBlank(message = "Email is mandatory")
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	static public Contact demoContact() {
		Contact contact = new Contact();
		contact.firstName = "Bob";
		contact.lastName = "Smith";
		contact.email = "bsmith@example.com";
		return contact;
	}

	private static final Faker faker = new Faker();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	static public List<Contact> randomContacts(int count) {

		List<Contact> result = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			Contact contact = new Contact();
			contact.id = faker.hashCode();
			contact.firstName = faker.name().firstName();
			contact.lastName = faker.name().lastName();
			contact.email = faker.internet().safeEmailAddress();
			result.add(contact);
		}

		return result;
	}

}
