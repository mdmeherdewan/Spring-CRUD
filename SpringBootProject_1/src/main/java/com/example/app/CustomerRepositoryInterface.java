package com.example.app;
import org.springframework.data.repository.CrudRepository;

import com.example.app.Customer;

//public interface CustomerRepositoryInterface extends JpaRepository<Customer, Long> {
public interface CustomerRepositoryInterface extends CrudRepository<Customer, Long> {

}
 