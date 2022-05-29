package state.chef;

import mediator.Person;
import models.Chef;
import models.Customer;
import models.Waiter;
import state.State;
import state.waiter.BringOrderWaiter;

public class DoneChef implements State {
	
	private Customer c;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		Waiter waiter = (Waiter) invoker;
		String waiterState = waiter.getState().getType();
		Customer customer = ((BringOrderWaiter) waiter.getState()).getCust();
		
		((Chef) p).signalChange(waiter);
		
		if(waiterState.equals("bring order")) {
			((Chef) p).setState(new CookChef(customer));
		} else if(waiterState.equals("idle")) {
			((Chef) p).setState(new IdleChef());
		}
	}

	public DoneChef(Customer c) {
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
		return "done";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "done <" + c.getName() + ">";
	}

}
