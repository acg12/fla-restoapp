package state.waiter;

import mediator.Person;
import models.Chef;
import models.Customer;
import models.Waiter;
import state.State;
import state.chef.DoneChef;
import state.chef.IdleChef;

public class BringOrderWaiter implements State {
	
	private Chef chef;
	private Customer cust;

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		if(((Chef) invoker).getState() instanceof IdleChef) {
			((Waiter) p).setState(new IdleWaiter());
		} else if(((Chef) invoker).getState() instanceof DoneChef) {
			DoneChef dcState = (DoneChef) ((Chef) invoker).getState();
			((Waiter) p).setState(new ServingFoodWaiter(dcState.getC()));
		}
	}

	public BringOrderWaiter(Chef chef, Customer cust) {
		this.chef = chef;
		this.cust = cust;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "bring order";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "bring order <" + chef.getName() + ">";
	}

}
