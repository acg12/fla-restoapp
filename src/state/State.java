package state;

import mediator.Person;

public interface State {
	public void change(Person p, Person invoker);
	public String getType();
	public String printState();
}
