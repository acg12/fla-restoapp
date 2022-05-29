package threads;

import models.Chef;
import models.Resto;

public class ChefThread extends Thread {

	private Resto resto;
	private Chef chef;
	
	public ChefThread(Resto resto, Chef chef) {
		this.resto = resto;
		this.chef = chef;
	}
	
	public void run() {
		synchronized(chef) {
			while(!resto.getState().getType().equals("close")) {
				// idle
				while(!resto.getState().getType().equals("close") && chef.getState().getType().equals("idle")) {
					while(resto.getState().getType().equals("pause")) {
						try {
							wait();
						} catch (Exception e) {}
					}
					
					// tunggu disignal
				}
				
				// cooking
				while(!resto.getState().getType().equals("close") && chef.getState().getType().equals("cook")) {
					while(resto.getState().getType().equals("pause")) {
						try {
							wait();
						} catch (Exception e) {}
					}
					
					try {
						Thread.sleep((6 - chef.getSpeed()) * 1000);
					} catch (Exception e) {}
					
					chef.signalChange(chef); // jadi done
				}
				
				// done
				while(!resto.getState().getType().equals("close") && chef.getState().getType().equals("done")) {
					while(resto.getState().getType().equals("pause")) {
						try {
							wait();
						} catch (Exception e) {}
					}
					
					// tunggu disignal
				}
			}
		}
	}
}
