package state.waiter;

import mediator.Person;
import models.Customer;
import models.Waiter;
import state.State;

public class ServingFoodWaiter implements State {
	
	private Customer c;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		((Waiter) p).setState(new IdleWaiter());
	}

	public ServingFoodWaiter(Customer c) {
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
		return "serving";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "serving food <" + c.getName() + ">";
	}
}
