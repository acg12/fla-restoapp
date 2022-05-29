package models;

import mediator.ChangeMediator;
import mediator.Person;
import state.State;
import state.customer.OrderCustomer;

public class Customer extends Person {

	private String name;
	private int tolerance;
	private ChangeMediator cm;
	volatile private State state;
	
	public Customer(String name, ChangeMediator m) {
		super(m);
		this.name = name;
		this.tolerance = 15;
		this.cm = m;
		this.state = new OrderCustomer();
	}
	
	public void change(Person invoker) {
		this.state.change(this, invoker);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ChangeMediator getCm() {
		return cm;
	}
}
