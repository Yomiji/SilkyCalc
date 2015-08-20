package com.kodesport.calculator.model;

public interface CalculatorModel {
	/**
	 * Perform the given operation
	 * @param operation
	 */
	void performOperation(String operation, double num);

	/**
	 * gets a value of this calculator as a double
	 * @return value
	 */
	Double getValue();

	/**
	 * sets the value of this calculator
	 * @param num value
	 */
	void setValue(Double num);
	
	/**
	 * gets the error code waiting in the model, by default its DivideByZero
	 * @return an error code to be displayed by a calculator
	 */
	default String getError(){
		return "(E) DivideByZero";
	}

}