package com.kodesport.calculator.bindings;

import com.kodesport.calculator.model.CalculatorIntModelImpl;
import com.kodesport.calculator.model.CalculatorModel;
import com.kodesport.calculator.model.CalculatorModelImpl;
import com.kodesport.calculator.presenter.CalculatorPresenter;
import com.kodesport.calculator.view.CalculatorView;
import com.kodesport.calculator.view.CalculatorViewImpl;

import se.jbee.inject.bind.BinderModule;

public class CalculatorBindings {

	public static class BasePresenterBinding extends BinderModule {

		@Override
		protected void declare() {
			construct(CalculatorPresenter.class);
			// Bind interface instances
			injectingInto(CalculatorPresenter.class)
				.bind(CalculatorModel.class).to(new CalculatorModelImpl());
			injectingInto(CalculatorPresenter.class)
				.bind(CalculatorView.class).to(new CalculatorViewImpl());
		}
		
	}
	
	public static class IntPresenterBinding extends BinderModule {

		@Override
		protected void declare() {
			bind( CalculatorPresenter.class).toConstructor();
			
			injectingInto(CalculatorPresenter.class)
				.bind(CalculatorModel.class).to(new CalculatorIntModelImpl());
			injectingInto(CalculatorPresenter.class)
				.bind(CalculatorView.class).to(new CalculatorViewImpl());
		}
		
	}

}
