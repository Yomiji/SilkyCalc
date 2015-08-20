package com.kodesport.calculator.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;

public interface CalculatorView extends ComponentContainer, View{

	/**
	 * sets the value of the view using a string
	 * @param value a string value
	 */
	void set_value(String value);

	/**
	 * a numerical representation of the view
	 * @return a {@link java.lang.Double Double} value, can be null
	 */
	Double get_valueDouble();

	/**
	 * a text representation of the view
	 * @return a {@link java.lang.String String} value, should not be null
	 */
	String get_valueString();

	/**
	 * assign the given {@link com.vaadin.ui.Button.ClickListener ClickListener} 
	 * to all buttons in the view
	 * @param clickListener handles button clicks
	 */
	void setButtonClickListener(ClickListener clickListener);
	
	/**
	 * get the current error thrown by the view
	 * @return an error string
	 */
	default String getError() {
		return "(E)";
	}
	/**
	 * get the maximum number of digits allowed by the calculator view
	 * @return max digits
	 */
	default int getMaxDigits() {
		return 30;
	}
	
	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	default void enter(ViewChangeEvent event) {
		
	}
	
}