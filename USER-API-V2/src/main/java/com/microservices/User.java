/**
 * 
 */
package com.microservices;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Satish Reddy
 *
 */
@Document(value = "User")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
 @Id
 private String userId;
 private String userName;
 private String useEamil;
 private String userPassword;

 }
