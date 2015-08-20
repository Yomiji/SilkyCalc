package com.kodesport.calculator;

import static se.jbee.inject.Dependency.dependency;

import javax.servlet.annotation.WebServlet;

import com.kodesport.calculator.bindings.CalculatorBindings;
import com.kodesport.calculator.presenter.CalculatorPresenter;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

import se.jbee.inject.Injector;
import se.jbee.inject.bootstrap.Bootstrap;

@SuppressWarnings("serial")
@Theme("calculator")
public class CalculatorUI extends UI {

	Navigator nav;
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = CalculatorUI.class, widgetset = "com.kodesport.calculator.widgetset.CalculatorWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		//resolve dependencies using Silk
		Injector injector = Bootstrap.injector( CalculatorBindings.BasePresenterBinding.class );
		Injector intInjector = Bootstrap.injector( CalculatorBindings.IntPresenterBinding.class );
		
		CalculatorPresenter presenter = injector
				.resolve(dependency(CalculatorPresenter.class));
		CalculatorPresenter intPresenter = intInjector
				.resolve(dependency(CalculatorPresenter.class));
		
		//create new navigator
		nav = new Navigator(this, this);
		nav.addView("", presenter.getView());
		nav.addView("IntCalc", intPresenter.getView());
		
		//quickly add buttons to each presenter
		Button changeButton = new Button("Int Type");
		changeButton.addClickListener(event -> {
			nav.navigateTo("IntCalc");
		});
		presenter.getView().addComponent(changeButton);
		
		Button changeButton2 = new Button("Dbl Type");
		changeButton2.addClickListener(event -> {
			nav.navigateTo("");
		});
		intPresenter.getView().addComponent(changeButton2);
	}

}