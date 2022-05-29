package facade;

import java.util.Scanner;

import models.Resto;

public class InputFacade implements Runnable {
	
	private Resto resto;
	
	public InputFacade(Resto resto) {
		this.resto = resto;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!resto.getState().getType().equals("close")) {
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
			
			if(!resto.getState().getType().equals("close")) {
				Scanner scan = new Scanner(System.in); 
				scan.nextLine();				
				
				if(!resto.getState().getType().equals("close")) {				
					resto.getState().change(resto);
				}
			}
		}
	}

}
