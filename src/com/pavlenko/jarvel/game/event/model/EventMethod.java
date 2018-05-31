package com.pavlenko.jarvel.game.event.model;

import java.lang.reflect.Method;

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
