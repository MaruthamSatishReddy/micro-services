package com.easytobuy;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Coupon {

private String couponId;
private String couponCode;
private String couponDescription;
private BigDecimal couponPrice;

}
