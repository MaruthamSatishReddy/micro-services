/**
 * 
 */
package com.microservices.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satish Reddy
 *
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;
	@Column(name = "order_tracking_id")
	private String orderTrackingId;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "total_price")
	private BigDecimal totalPrice;
	@Column(name="status")
	private String status;
	@Column(name = "order_created_date")
	@CreationTimestamp
	private Date orderDate;
	@UpdateTimestamp
	@Column(name = "order_lastupdated_date")
	private Date lastUpdate;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
	private Set<OrderItem> orderItems=new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id",referencedColumnName = "address_id")
	private Address billingAddress;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id",referencedColumnName = "address_id")
	private Address ShippingAddress;
	
	public void add(OrderItem item) {
		if(item!=null) {
			if(orderItems == null) {
				orderItems = new HashSet<>();
			}
			orderItems.add(item);
			item.setOrder(this);
		}
	}
	

}
