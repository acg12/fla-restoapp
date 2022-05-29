package threads;

import models.Chef;
import models.Customer;
import models.Resto;
import models.Waiter;
import state.waiter.BringOrderWaiter;
import state.waiter.ServingFoodWaiter;
import state.waiter.TakeOrderWaiter;
import state.waiter.WaitCookWaiter;

public class WaiterThread extends Thread {

	private Resto resto;
	private Waiter waiter;
	
	public WaiterThread(Resto resto, Waiter waiter) {
		this.resto = resto;
		this.waiter = waiter;
	}
	
	public void run() {
		synchronized(waiter) {
			while(!resto.getState().getType().equals("close")) {
				// idle
				while(!resto.getState().getType().equals("close") && waiter.getState().getType().equals("idle")) {
					while(resto.getState().getType().equals("pause")) {
						try {
							wait();
						} catch (Exception e) {}
					}
					
					// tunggu disignal
				}
				
				// take order
				while(!resto.getState().getType().equals("close") && waiter.getState().getType().equals("take order")) {
					while(resto.getState().getType().equals("pause")) {
						try {
							wait();
						} catch (Exception e) {}
					}
					
					try {
						Thread.sleep((6 - waiter.getSpeed()) * 1000);
					} catch (Exception e) {}
					
					Customer cust = ((TakeOrderWaiter) waiter.getState()).getC();
					waiter.signalChange(cust); // jadi wait
					waiter.signalChange(waiter); // jadi wait cook
				}
				
				// wait cook
				while(!resto.getState().getType().equals("close") && waiter.getState().getType().equals("wait cook")) {
					while(resto.getState().getType().equals("pause")) {
						try {
							wait();
						} catch (Exception e) {}
					}
					
					for(Chef chef : resto.getChefs()) {
						if(chef.getState().getType().equals("idle") || chef.getState().getType().equals("done")) {
							chef.signalChange(waiter); // jadi bring order
							break;
						}
					}
				}
				
				// bring order
				while(!resto.getState().getType().equals("close") && waiter.getState().getType().equals("bring order")) {
					while(resto.getState().getType().equals("pause")) {
						try {
							wait();
						} catch (Exception e) {}
					}
					
					try {
						Thread.sleep(1000);
					} catch (Exception e) {}
					
					
					Chef chef = ((BringOrderWaiter) waiter.getState()).getChef();
					Customer cust = ((BringOrderWaiter) waiter.getState()).getCust();
					
					waiter.signalChange(cust); // cust jdi waiting chef
					waiter.signalChange(chef); // chef cook, waiter jadi idle/serving berdasarkan ini
				}
				
				// serving
				while(!resto.getState().getType().equals("close") && waiter.getState().getType().equals("serving")) {
					while(resto.getState().getType().equals("pause")) {
						try {
							wait();
						} catch (Exception e) {}
					}
					
					Customer cust = ((ServingFoodWaiter) waiter.getState()).getC();
					waiter.signalChange(cust); // jadi wait waiter 
					
					try {
						Thread.sleep(1000);
					} catch (Exception e) {}
					
					waiter.signalChange(cust); // cust jdi eat
					waiter.signalChange(waiter); // waiter jdi idle
				}
			}
		}
	}
}
