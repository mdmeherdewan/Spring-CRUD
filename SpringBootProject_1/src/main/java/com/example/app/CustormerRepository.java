package com.example.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
//public interface CustormerRepository extends JpaRepository<Customer, Long> {
public class CustormerRepository {
	
	public CustormerRepository() {
		super();
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<Customer> customerList = new ArrayList<Customer>();
	
	public  List<Customer> findAll(){
		String sql = "SELECT * FROM T_customer order by id";
		List <Customer> customer = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
		return customer;
	}

	public List<Customer> findById(Integer id) {
		String sql = "SELECT * FROM T_customer where id = "+id;
		List <Customer> customer = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
		return customer;
	}
	
	public Integer updateCustomer(Customer cus) {
		String sql = "update T_customer set name = \'"+cus.getName()+"\', age ="+cus.getAge()+", email = \'"+cus.getEmail()+"\' where id = "+cus.getId();
		
		int customer = jdbcTemplate.update(sql);

		return customer;
	}
	
	public Integer deleteSigleCustomer(Integer id) {
		String sql = "delete T_customer where id = "+id;
		
		int customer = jdbcTemplate.update(sql);

		return customer;
	}
}
