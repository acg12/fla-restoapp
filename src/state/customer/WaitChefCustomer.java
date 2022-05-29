package state.customer;

import mediator.Person;
import models.Chef;
import models.Customer;
import models.Waiter;
import state.State;
import state.chef.CookChef;
import state.waiter.ServingFoodWaiter;

public class WaitChefCustomer implements State {
	
	private Chef c;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		((Customer) p).setState(new WaitWaiterCustomer((Waiter) invoker, c));
	}

	public WaitChefCustomer(Chef c) {
		this.c = c;
	}

	public Chef getC() {
		return c;
	}

	public void setC(Chef c) {
		this.c = c;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "wait chef";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "wait food <" + c.getName() + ">";
	}

}
