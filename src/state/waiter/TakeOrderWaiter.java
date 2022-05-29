package state.waiter;

import mediator.Person;
import models.Customer;
import models.Waiter;
import state.State;

public class TakeOrderWaiter implements State {
	
	private Customer c;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		((Waiter) p).setState(new WaitCookWaiter(c));
	}

	public TakeOrderWaiter(Customer c) {
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
		return "take order";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "take order <" + c.getName() + ">";
	}

}
