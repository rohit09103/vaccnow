/**
 * 
 */
package com.localhost.vaccination.enums;

/**
 * @author rohitsharma08
 *
 */
public enum PaymentMethodType {
	
	CASH("cash"),CREDIT("credit"),FAWRY("fawry");

	private String name;
	private PaymentMethodType(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
