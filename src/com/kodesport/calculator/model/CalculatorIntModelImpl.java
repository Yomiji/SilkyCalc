package com.kodesport.calculator.model;

import java.io.Serializable;
import java.math.BigInteger;

public class CalculatorIntModelImpl implements CalculatorModel, Serializable {
	
	private transient BigInteger value;

	public CalculatorIntModelImpl() {
		value = BigInteger.ZERO;
	}

	void add(double num) {
		value = value.add(BigInteger.valueOf((long)num));
	}

	void subtract(double num) {
		value = BigInteger.valueOf((long)num).subtract(value);
	}

	void multiply(double num) {
		value = value.multiply(BigInteger.valueOf((long)num));
	}

	void divide(double num) {
		BigInteger numVal = BigInteger.valueOf((long)num);
		if(value.intValue() == 0) {
			value = null;
		} else if(numVal.intValue() == 0) {
			value = BigInteger.ZERO;
		} else {
			value = numVal.divide(value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kodesport.calculator.model.CalculatorModel#getValueDouble()
	 */
	@Override
	public Double getValue() {
		if(value == null)
			return null;
		else
			return value.doubleValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kodesport.calculator.model.CalculatorModel#setValue(double)
	 */
	@Override
	public void setValue(Double num) {
		if(num == null)
			this.value = null;
		else
			this.value = BigInteger.valueOf(num.longValue());
	}

	/* (non-Javadoc)
	 * @see com.kodesport.calculator.model.CalculatorModel#performOperation(java.lang.String, double)
	 */
	@Override
	public void performOperation(String operation, double num) {
		if (value == null || operation == null || operation.length() != 1)
			return;
		char op = operation.charAt(0);
		switch (op) {
		case '+':
			add(num);
			break;
		case '-':
			subtract(num);
			break;
		case '/':
			divide(num);
			break;
		case '*':
			multiply(num);
			break;
		}
	}
	
	private static final long serialVersionUID = -885013138854011229L;
}
