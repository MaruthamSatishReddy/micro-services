/**
 * 
 */
package com.easytobuy;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SatishReddy
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {
	
	private Long categoryId;
	
	private String categoryName;
	
	private Date createdDate;
	
	private Date updatedDate;
	
}
