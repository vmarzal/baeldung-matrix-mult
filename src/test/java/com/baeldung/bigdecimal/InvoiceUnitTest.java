package com.baeldung.bigdecimal;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InvoiceUnitTest {

	private static Validator validator;
	
	@BeforeAll
	public static void setupValidatorInstance() {
		validator = Validation.buildDefaultValidatorFactory()				
				.getValidator();
	}
	
	@Test
	public void whenPriceIntegerDigitLessThanThreeWithDecimalValue_thenShouldGiveConstraintViolations() {
        Invoice invoice = new Invoice(new BigDecimal(10.21), "Book purchased");
		
		Set<ConstraintViolation<Invoice>> violations = validator.validate(invoice);
		
		assertThat(violations.size()).isEqualTo(1);
		violations.forEach(action -> assertThat(action.getMessage())
				.isEqualTo("valor numérico fuera de los límites (se esperaba <3 dígitos>.<2 dígitos)"));
		
	}
	
	@Test
	public void whenPriceIntegerDigitLessThanThreeWithIntegerValue_thenShouldNotGiveConstraintViolations() {
	    Invoice invoice = new Invoice(new BigDecimal(10), "Book purchased");
	  
	    Set<ConstraintViolation<Invoice>> violations = validator.validate(invoice);
	  
	    assertThat(violations.size()).isEqualTo(0);
	}
	
	@Test
	public void whenPriceIntegerDigitGreaterThanThree_thenShouldGiveConstraintViolations() {
	    Invoice invoice = new Invoice(new BigDecimal(1021.21), "Book purchased");
	  
	    Set<ConstraintViolation<Invoice>> violations = validator.validate(invoice);
	  
	    assertThat(violations.size()).isEqualTo(1);
	    violations.forEach(action -> assertThat(action.getMessage())
	      .isEqualTo("valor numérico fuera de los límites (se esperaba <3 dígitos>.<2 dígitos)"));
	}
		
	@Test
	public void whenPriceIsZero_thenShouldGiveConstraintViolations() {
	    Invoice invoice = new Invoice(new BigDecimal(000.00), "Book purchased");
	  
	    Set<ConstraintViolation<Invoice>> violations = validator.validate(invoice);
	  
	    assertThat(violations.size()).isEqualTo(1);
	    violations.forEach(action -> assertThat(action.getMessage())
	      .isEqualTo("tiene que ser mayor 0.0"));
	}
	
	@Test
	public void whenPriceIsGreaterThanZero_thenShouldNotGiveConstraintViolations() {
	    Invoice invoice = new Invoice(new BigDecimal(100.50), "Book purchased");
	  
	    Set<ConstraintViolation<Invoice>> violations = validator.validate(invoice);
	  
	    assertThat(violations.size()).isEqualTo(0);
	}
	
}

