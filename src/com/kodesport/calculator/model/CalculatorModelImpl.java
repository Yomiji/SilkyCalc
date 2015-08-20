package com.kodesport.calculator.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculatorModelImpl implements CalculatorModel, Serializable {
	
	private transient BigDecimal value;
	private final static MathContext context = new MathContext(10, RoundingMode.HALF_UP);

	public CalculatorModelImpl() {
		value = new BigDecimal(0D);
	}

	public void add(double num) {
		value = value.add(BigDecimal.valueOf(num),context);
	}

	public void subtract(double num) {
		value = BigDecimal.valueOf(num).subtract(value,context);
	}

	public void multiply(double num) {
		value = value.multiply(BigDecimal.valueOf(num),context);
	}

	public void divide(double num) {
		if(value.intValue() == 0) {
			value = null;
		} else if(BigDecimal.valueOf(num).intValue() == 0) {
			value = BigDecimal.ZERO;
		} else {
			value = BigDecimal.valueOf(num).divide(value, context);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kodesport.calculator.model.CalculatorModel#getValueDouble()
	 */
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
		return value.toPlainString();
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
			this.value = BigDecimal.valueOf(num);
	}

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
	
	private static final long serialVersionUID = -9200129930028975571L;
}
