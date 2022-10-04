package com.tamingthymeleaf.tamingthymeleaf.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.github.javafaker.Faker;

@Entity
@Table(name = "user_account")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Email is mandatory")
	private String email;

	private Date dateOfBirth;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name);
	}

	static public User demoUser() {
		User contact = new User();
		contact.name = "Bob";
		contact.id = 1234567890;
		contact.email = "bsmith@example.com";
		return contact;
	}

	private static final Faker faker = new Faker();

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	static public List<User> randomUsers(int count) {

		List<User> result = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			User contact = new User();
			contact.name = faker.name().firstName();
			contact.id = faker.name().hashCode();
			contact.email = faker.internet().safeEmailAddress();
			result.add(contact);
		}

		return result;
	}

}