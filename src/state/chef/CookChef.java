package state.chef;

import mediator.Person;
import models.Chef;
import models.Customer;
import state.State;

public class CookChef implements State {
	
	private Customer c;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		((Chef) p).setState(new DoneChef(c));
	}

	public CookChef(Customer c) {
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
		return "cook";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "cook <" + c.getName() + ">";
	}

}
