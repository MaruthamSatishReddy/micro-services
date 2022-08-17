/**
 * 
 */
package com.microservices.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

/**
 * @author Satish Reddy
 *
 */
@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private Set<Order> orders=new HashSet<>();
	@OneToOne
	@JoinColumn(name="billing_address_id",referencedColumnName ="address_id")
	private Address billingAddress;
	@OneToOne
	@JoinColumn(name="shipping_address_id",referencedColumnName ="address_id")
	private Address shippingAddress;
	
	public void add(Order order) {
		if(order!=null) {
			if(orders==null) {
				orders= new HashSet<>();
			}
			orders.add(order);
			order.setCustomer(this);
		}
	}

}
