package com.example.app;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication  
@CrossOrigin(origins = "*")
public class SpringBootProject1Application extends SpringBootServletInitializer implements CommandLineRunner  {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProject1Application.class, args); 
	}
	 

	  @Override 
	  public void run(String... args) throws Exception { 
		  String sql = "SELECT * FROM T_customer"; 
		  List <Customer> customer = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
	  
		  customer.forEach(System.out::println); 
	  }

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) { return
	 * builder.sources(SpringBootProject1Application.class); }
	 */
	  
	  
	/*
	 * @RequestMapping(value = "/hello") public String hello() { String m =
	 * "hello spring"; return m; }
	 */

}
