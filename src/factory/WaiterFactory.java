package factory;

import mediator.ChangeMediator;
import models.Waiter;

public class WaiterFactory extends StaffFactory {

	@Override
	public Waiter recruitStaff(String name, ChangeMediator m) {
		// TODO Auto-generated method stub
		return new Waiter(name, m);
	}

}
