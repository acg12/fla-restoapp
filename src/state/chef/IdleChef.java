package state.chef;

import mediator.Person;
import models.Chef;
import models.Customer;
import models.Waiter;
import state.State;
import state.waiter.BringOrderWaiter;
import state.waiter.IdleWaiter;

public class IdleChef implements State {

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		Waiter w = (Waiter)invoker;
		Customer cust = ((BringOrderWaiter) w.getState()).getCust();
		
		((Chef) p).getCm().signalChange(p, w);
		((Chef) p).setState(new CookChef(cust));
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "idle";
	}

	@Override
	public String printState() {
		// TODO Auto-generated method stub
		return "idle";
	}

}
