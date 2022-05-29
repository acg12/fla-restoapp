package state;

import models.Resto;

public interface RestoState {

	public void change(Resto resto);
	public String getType();
}
