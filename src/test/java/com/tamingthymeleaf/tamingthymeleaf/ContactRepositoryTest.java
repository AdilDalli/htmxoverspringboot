package com.tamingthymeleaf.tamingthymeleaf;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.tamingthymeleaf.tamingthymeleaf.demo.dao.ContactRepository;

@DataJpaTest
public class ContactRepositoryTest {
	
	@Autowired
	private ContactRepository contactRepository;

	
	 @Test
	void testFindAllPageable() {
//		 Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
//		 assertThat(contactRepository.findAll(PageRequest.of(0, 100, sort)))
//		 .hasSize(100)
//		 .extracting(user -> user.getLastName());
//		 
//		 assertThat(contactRepository.findAll(PageRequest.of(1, 25, sort)))
//		 .hasSize(25)
//		 .extracting(user -> user.getLastName());
//		 
//		 assertThat(contactRepository.findAll(PageRequest.of(2, 5, sort))).hasSizeLessThan(6);
		
	}
}
