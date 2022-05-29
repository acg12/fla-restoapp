package models;

import factory.Staff;
import mediator.ChangeMediator;
import mediator.Person;
import state.State;
import state.chef.IdleChef;

public class Chef extends Person implements Staff {
	
	private String name;
	private int speed;
	private int skill;
	private ChangeMediator cm;
	volatile private State state;
	
	public Chef(String name, ChangeMediator m) {
		super(m);
		this.name = name;
		this.speed = 1;
		this.skill = 1;
		this.cm = m;
		this.state = new IdleChef();
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

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
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
