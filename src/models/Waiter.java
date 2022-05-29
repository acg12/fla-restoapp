package models;

import factory.Staff;
import mediator.ChangeMediator;
import mediator.Person;
import state.State;
import state.waiter.IdleWaiter;

public class Waiter extends Person implements Staff {

	private String name;
	private int speed;
	private ChangeMediator cm;
	volatile private State state;
	
	public Waiter(String name, ChangeMediator m) {
		super(m);
		this.name = name;
		this.speed = 1;
		this.cm = m;
		this.state = new IdleWaiter();
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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
