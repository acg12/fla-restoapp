package threads;

import models.Customer;
import models.Resto;

public class ToleranceThread extends Thread {

	private Customer cust;
	private Resto resto;

	public ToleranceThread(Resto resto, Customer cust) {
		this.cust = cust;
		this.resto = resto;
	}
	
	public void run() {
		// order
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("order")) {
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
			
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			cust.setTolerance(cust.getTolerance() - 1);
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
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
			
			try {
				Thread.sleep(4000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			cust.setTolerance(cust.getTolerance() - 1);
		}
		
		// wait chef
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("wait chef")) {
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
			
			try {
				Thread.sleep(4000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			cust.setTolerance(cust.getTolerance() - 1);
		}
		
		// wait waiter
		while(!resto.getState().getType().equals("close") && cust.getTolerance() > 0 && cust.getState().getType().equals("wait waiter")) {
			while(resto.getState().getType().equals("pause")) {
				try {
					wait();
				} catch (Exception e) {}
			}
			
			try {
				Thread.sleep(4000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			cust.setTolerance(cust.getTolerance() - 1);
		}
	}
}
