package com.example.app;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import org.springframework.data.repository.CrudRepository;

/*public interface CusRepInterface extends JpaRepository<Customer, Long> {

}*/

public interface CusRepInterface extends CrudRepository<Customer, Long> {

}
