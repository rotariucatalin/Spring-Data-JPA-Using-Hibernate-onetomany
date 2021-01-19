package com.example.onetomany;

import com.example.onetomany.models.Customer;
import com.example.onetomany.models.PhoneNumber;
import com.example.onetomany.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class OnetomanyApplicationTests {

	@Autowired
	CustomerRepository customerRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createCustomer() {

		Customer customer = new Customer();
		customer.setName("Customer 1");

		PhoneNumber phoneNumber = new PhoneNumber();

		phoneNumber.setNumber("111");
		phoneNumber.setType("mobile");

		PhoneNumber phoneNumber2 = new PhoneNumber();

		phoneNumber2.setNumber("222");
		phoneNumber2.setType("fix");


		customer.addPhoneNumber(phoneNumber);
		customer.addPhoneNumber(phoneNumber2);

		customerRepository.save(customer);

	}

	@Test
	@Transactional
	public void readOneToMany() {

		Customer customer = customerRepository.findById(5).get();

		System.out.println("Customer name =====> " + customer.getName());

		Set<PhoneNumber> phoneNumbers = customer.getPhoneNumberList();

		phoneNumbers.forEach(phoneNumber -> System.out.println("Phone number is " + phoneNumber.getNumber()));
	}

	@Test
	@Transactional
	public void updateOneToMany() {

		Customer customer = customerRepository.findById(5).get();

		customer.setName("Customer 100");

		Set<PhoneNumber> phoneNumbers = customer.getPhoneNumberList();

		phoneNumbers.forEach(phoneNumber -> phoneNumber.setType("cell"));

		customerRepository.save(customer);

	}

	@Test
	@Transactional
	public void deleteOneToMany() {

		customerRepository.deleteById(5);

	}

}
