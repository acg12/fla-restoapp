package state.waiter;

import mediator.Person;
import models.Chef;
import models.Customer;
import models.Waiter;
import state.State;
import state.chef.DoneChef;

public class IdleWaiter implements State {

	@Override
	synchronized public void change(Person p, Person invoker) {
		// TODO Auto-generated method stub
		if(invoker instanceof Customer) {
			((Waiter) p).setState(new TakeOrderWaiter((Customer) invoker));			
		} else if(invoker instanceof Chef) {
			Chef chef = (Chef) invoker;
			DoneChef dcState = (DoneChef) chef.getState();
			((Waiter) p).setState(new ServingFoodWaiter(dcState.getC()));	
		}
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
