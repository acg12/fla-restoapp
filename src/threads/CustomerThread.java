package threads;

import models.Chef;
import models.Customer;
import models.Resto;
import models.Waiter;
import state.customer.EatCustomer;
import state.customer.OrderWaiterCustomer;

public class CustomerThread extends Thread {

	private Resto resto;
	private Customer cust;
	
	public CustomerThread(Resto resto, Customer cust) {
		this.resto = resto;
		this.cust = cust;
	}
	
	public void run() {
		Boolean lost = true;
		// order
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("order")) {
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
			
			for(Waiter w : resto.getWaiters()) {
				if(w.getState().getType().equals("idle")) {
					cust.signalChange(w); // order waiter
					w.signalChange(cust); // take order
					break;
				}
			}
		}
		
		// order waiter
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("order waiter")) {
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
		}
		
		// wait
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("wait")) {
			// tunggu disignal ganti
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
		}
		
		// wait chef
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("wait chef")) {
			// tunggu disignal ganti
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
		}
		
		// wait waiter
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("wait waiter")) {
			// tunggu disignal ganti
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
		}
		
		// eat
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("eat")) {
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
			
			try {
				Thread.sleep(6000);
			} catch (Exception e) {}
			
			Chef chef = ((EatCustomer) cust.getState()).getChef();
			int hasil = chef.getSkill() * 30;
			
			resto.setMoney(resto.getMoney() + hasil);
			resto.setScore(resto.getScore() + hasil);
			lost = false;
			resto.getCustomers().remove(cust);
		}
		
		if(!resto.getState().getType().equals("close") && lost) {
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
			
			resto.setScore(resto.getScore() - 300);
		}
	}
}
