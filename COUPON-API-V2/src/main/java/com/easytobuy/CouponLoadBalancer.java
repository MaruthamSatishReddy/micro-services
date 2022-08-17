/*
 * package com.easytobuy;
 * 
 * import org.springframework.cloud.client.loadbalancer.LoadBalanced; import
 * org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient; import
 * org.springframework.context.annotation.Bean;
 * 
 * import feign.Feign;
 * 
 * @LoadBalancerClient(value="coupon-service") public class CouponLoadBalancer {
 * 
 * @Bean
 * 
 * @LoadBalanced public Feign.Builder feignBuilder(){ return Feign.builder(); }
 * }
 */