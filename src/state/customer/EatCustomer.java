package state.customer;

import mediator.Person;
import models.Chef;
import state.State;

/**
 * @author Angel
 *
 */
public class EatCustomer implements State {
	
	private Chef chef;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		System.out.println("Couldn't remove customer");
	}

	public EatCustomer(Chef chef) {
		this.chef = chef;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "eat";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "eat";
	}

	public Chef getChef() {
		return chef;
	}

}
