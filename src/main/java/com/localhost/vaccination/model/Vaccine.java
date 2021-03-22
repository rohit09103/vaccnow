/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Rohit
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
@ApiModel(description = "Model to represent a vaccine.")
public class Vaccine implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3908467921113815968L;
	private Integer vaccineId;
	private String name;
	private Integer quantity;
	
	/**
	 * Default constructor for vaccine model.
	 */
	public Vaccine() {
		// Auto-generated constructor stub
	}

}
