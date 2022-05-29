package state.customer;

import mediator.Person;
import models.Chef;
import models.Customer;
import models.Waiter;
import state.State;

public class WaitWaiterCustomer implements State {
	
	private Waiter w;
	private Chef c;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		((Customer) p).setState(new EatCustomer(c));
	}

	public WaitWaiterCustomer(Waiter w, Chef c) {
		this.w = w;
		this.c = c;
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
		return "wait waiter";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "wait food <" + w.getName() + ">";
	}

}
