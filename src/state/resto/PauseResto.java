package state.resto;

import models.Resto;
import state.RestoState;

public class PauseResto implements RestoState {
	
	@Override
	public void change(Resto invoker) {
		invoker.setState(new ActiveResto());
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "pause";
	}
}
