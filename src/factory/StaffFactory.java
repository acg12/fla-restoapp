package factory;

import mediator.ChangeMediator;

public abstract class StaffFactory {

	public abstract Staff recruitStaff(String name, ChangeMediator m);
}
