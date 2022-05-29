package state.customer;

import mediator.Person;
import models.Customer;
import models.Waiter;
import state.State;

public class OrderCustomer implements State {

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		((Customer) p).setState(new OrderWaiterCustomer((Waiter) invoker));
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "order";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "order";
	}

}
