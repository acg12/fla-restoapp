package state.customer;

import mediator.Person;
import models.Customer;
import models.Waiter;
import state.State;

public class OrderWaiterCustomer implements State {

	private Waiter w;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		((Customer) p).setState(new WaitCustomer());
	}

	public OrderWaiterCustomer(Waiter w) {
		this.w = w;
	}

	public Waiter getW() {
		return w;
	}

	public void setW(Waiter w) {
		this.w = w;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "order waiter";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "order <" + w.getName() + ">";
	}
}
