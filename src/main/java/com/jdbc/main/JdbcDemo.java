package com.jdbc.main;

import java.util.List;

import com.jdbc.model.UserDetails;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdbc.dao.JdbcDaoImpl;
import com.jdbc.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		JdbcDaoImpl jdbcDaoImpl = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);

		UserDetails userDetails = jdbcDaoImpl.getUserDetails(1);
		System.out.println("ID=> " + userDetails.getId());
		System.out.println("NAME=> " + userDetails.getName());

		UserDetails userDetails1 = new UserDetails(2, "Ritesh");
		jdbcDaoImpl.insert(userDetails1);

		System.out.println("Count=> " + jdbcDaoImpl.getUserCount());

		List<UserDetails> c = jdbcDaoImpl.getUserDetaiilsObject();
		System.out.println("  ID        NAME");
		
		c.forEach(
				name -> System.out.println("  " + name.getId() + "        " + name.getName()));
	}

}
