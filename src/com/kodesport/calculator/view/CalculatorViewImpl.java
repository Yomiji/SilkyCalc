package com.kodesport.calculator.view;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class CalculatorViewImpl extends CalculatorDesign implements CalculatorView {

	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = -7944228883379342893L;
	
	public CalculatorViewImpl() {
		super();
	}
	/* (non-Javadoc)
	 * @see com.kodesport.calculator.view.CalculatorView#set_value(java.lang.String)
	 */
	@Override
	public void set_value(String value) {
		this._value.setValue(value);
	}
	
	/* (non-Javadoc)
	 * @see com.kodesport.calculator.view.CalculatorView#get_value()
	 */
	@Override
	public Double get_valueDouble() {
		Double result = Double.valueOf(0D);
		try {
			result = Double.parseDouble(this._value.getValue());
		} catch (NumberFormatException e) {
			result = null;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.kodesport.calculator.view.CalculatorView#get_valueString()
	 */
	@Override
	public String get_valueString() {
		return this._value.getValue();
	}
	
	/* (non-Javadoc)
	 * @see com.kodesport.calculator.view.CalculatorView#setButtonClickListener(com.vaadin.ui.Button.ClickListener)
	 */
	@Override
	public void setButtonClickListener(ClickListener clickListener) {
		getButtons()
		.filter(button -> button != null)
		.forEach(button -> {
			button.addClickListener(clickListener);
		});
	}
	
	/**
	 * Get a stream of all valid {@link com.vaadin.ui.Button Buttons} in this control
	 * @return non-null {@link com.vaadin.ui.Button Button} stream
	 */
	private Stream<Button> getButtons() {
		List<Button> buttons = new Vector<>();
		Iterator<Component> buttonIter = _buttonGrid.iterator();
		buttonIter.forEachRemaining(component -> {
			if(component instanceof Button)
				buttons.add((Button)component);
		});;
		return buttons.stream();
	}
}
