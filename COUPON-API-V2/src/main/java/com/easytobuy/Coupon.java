package com.easytobuy;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "Coupon")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Coupon {
@Id
private String couponId;
private String couponCode;
private String couponDescription;
private BigDecimal couponPrice;

}
