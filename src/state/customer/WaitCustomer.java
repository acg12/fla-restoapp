package state.customer;

import mediator.Person;
import models.Customer;
import models.Waiter;
import state.State;
import state.waiter.BringOrderWaiter;

public class WaitCustomer implements State {

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		Waiter w = (Waiter) invoker;
		BringOrderWaiter bowState = (BringOrderWaiter) w.getState();
		((Customer) p).setState(new WaitChefCustomer(bowState.getChef()));
		System.out.println(((Customer) p).getState());
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "wait";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "wait food";
	}
	
}
