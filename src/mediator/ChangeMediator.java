package mediator;

import models.Chef;
import models.Customer;
import models.Waiter;

public class ChangeMediator implements Mediator {

	@Override
	public void signalChange(Person from, Person to) {
		// TODO Auto-generated method stub
		to.receiveChange(from);
	}

}
