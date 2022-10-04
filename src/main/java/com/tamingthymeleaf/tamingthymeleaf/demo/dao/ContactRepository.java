package com.tamingthymeleaf.tamingthymeleaf.demo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tamingthymeleaf.tamingthymeleaf.demo.model.Contact;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
	@Query("SELECT c FROM Contact c "
			+ "WHERE LOWER(c.firstName) like %:search% "
			+ "OR LOWER(c.lastName) like %:search% ")
	List<Contact> findAllByText(@Param("search") String search, Pageable pageable);
}