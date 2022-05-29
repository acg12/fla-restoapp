package state.resto;

import mediator.Person;
import models.Resto;
import state.RestoState;
import state.State;

public class ActiveResto implements RestoState {

	@Override
	public void change(Resto invoker) {
		// TODO Auto-generated method stub
		invoker.setState(new PauseResto());
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "active";
	}

}
