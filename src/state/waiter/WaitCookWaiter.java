package state.waiter;

import mediator.Person;
import models.Chef;
import models.Customer;
import models.Waiter;
import state.State;
import state.chef.DoneChef;

public class WaitCookWaiter implements State {
	
	private Customer c;
	
	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		((Waiter) p).setState(new BringOrderWaiter((Chef) invoker, c));
	}

	public WaitCookWaiter(Customer c) {
		this.c = c;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "wait cook";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "wait cook";
	}
}
