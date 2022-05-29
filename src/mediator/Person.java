package mediator;

import models.Chef;
import models.Customer;
import models.Waiter;

public abstract class Person {
	
	private ChangeMediator mediator;
	
	public Person(ChangeMediator m) {
		this.mediator = m;
	}
	
	public void signalChange(Person to) {
		mediator.signalChange(this, to);
	}
	
	public void receiveChange(Person from) {
		if(this instanceof Waiter) {
			((Waiter)this).change(from);
		} else if(this instanceof Chef) {
			((Chef)this).change(from);
		} else {
			((Customer)this).change(from);
		}
	}
}
