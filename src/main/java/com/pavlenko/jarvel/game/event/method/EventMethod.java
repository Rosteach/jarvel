package com.pavlenko.jarvel.game.event.method;

import java.lang.reflect.Method;

/**
 * {@code EventMethod} class causes to hold method for event callback execution
 * and Object class where this method is located to invoke it on demand
 * 
 * @author Rostislav R.A.
 */
public class EventMethod {
	private Object invoker;
	private Method method;

	public EventMethod() {
	}

	public EventMethod(Object invoker, Method method) {
		this.invoker = invoker;
		this.method = method;
	}

	public Object getInvoker() {
		return invoker;
	}

	public void setInovoker(Object invoker) {
		this.invoker = invoker;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
}
