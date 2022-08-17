/**
 * 
 */
package com.easytobuy;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Satish Reddy
 *
 */
@Entity
@Table(name = "Rating")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Rating {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private int ratingId;
 @Column(name = "ratingComments")
 private String ratingComments;
 @Column(name = "ratingVal")
 private BigDecimal ratingVal;
}
