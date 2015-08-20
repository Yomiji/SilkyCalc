package com.kodesport.calculator.presenter;

import java.io.Serializable;

import com.kodesport.calculator.model.CalculatorModel;
import com.kodesport.calculator.view.CalculatorView;
import com.vaadin.ui.Button.ClickEvent;

public class CalculatorPresenter implements Serializable {
	CalculatorModel model;
	CalculatorView view;

	transient String currentOperation;
	transient double storedValue = 0D;

	public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
		this.model = model;
		this.view = view;
		defineClickListener();
	}

	public CalculatorView getView() {
		return view;
	}

	private void defineClickListener() {
		view.setButtonClickListener(event -> buttonPress(event));
	}

	private void buttonPress(ClickEvent event) {
		String fullButtonText = event.getButton().getCaption();
		char singleCharOnButton = fullButtonText.charAt(0);

		if (singleCharOnButton >= '0' && singleCharOnButton <= '9')
			addButtonTextToView(singleCharOnButton);
		else
			switch (singleCharOnButton) {
			case '=':
				if (currentOperation != "")
					if (assignViewValueToModel(false))
						performOperation(currentOperation);
				break;
			case '.':
				if (!view.get_valueString().contains("."))
					addButtonTextToView(singleCharOnButton);
				break;
			case 'C':
				currentOperation = "";
				modifyStoredValue(0D);
				model.setValue(0D);
				break;
			default:
				if (assignViewValueToModel(true))
					currentOperation = fullButtonText;
			}
	}

	private void performOperation(String operation) {
		// perform the operation
		model.performOperation(operation, storedValue);
		// get the error if present
		if (model.getValue() == null)
			view.set_value(model.getError());
		else
			view.set_value(model.getValue().toString());
		currentOperation = "";
	}

	private boolean assignViewValueToModel(boolean iShouldStoreTheValue) {
		Double viewValue = view.get_valueDouble();
		if (viewValue != null) {
			model.setValue(viewValue);
			if (iShouldStoreTheValue)
				modifyStoredValue(viewValue);
			return true;
		}
		view.set_value(view.getError());
		return false;
	}

	private void modifyStoredValue(double storedValue) {
		this.storedValue = storedValue;
		view.set_value("0");
	}

	private void addButtonTextToView(char buttonText) {
		String currentViewStringValue = view.get_valueString();
		// remove all unwanted
		currentViewStringValue = currentViewStringValue.replaceAll("[^0-9.]+", "");
		// prevent leading 0
		if (currentViewStringValue.length() == 1 && currentViewStringValue.charAt(0) == '0') {
			currentViewStringValue = currentViewStringValue.substring(1);
		}
		currentViewStringValue += buttonText;
		// limit the size of the calculator view
		if (currentViewStringValue.length() >= view.getMaxDigits())
			currentViewStringValue = currentViewStringValue.substring(0, view.getMaxDigits());
		// add the digit
		view.set_value(currentViewStringValue);
	}

	private static final long serialVersionUID = 2376710175250315102L;
}
