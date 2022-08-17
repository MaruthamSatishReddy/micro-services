/**
 * 
 */
package com.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.entity.Customer;

/**
 * @author Satish Reddy
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
