package factory;

import mediator.ChangeMediator;
import models.Chef;

public class ChefFactory extends StaffFactory {

	@Override
	public Chef recruitStaff(String name, ChangeMediator m) {
		// TODO Auto-generated method stub
		return new Chef(name, m);
	}

}
