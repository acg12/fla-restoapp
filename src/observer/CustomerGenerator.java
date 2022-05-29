package observer;

import java.util.Random;

import models.Customer;
import models.Resto;
import threads.CustomerThread;
import threads.ToleranceThread;
import utils.Utils;

public class CustomerGenerator extends Thread {

	private Resto resto;
	private Random rand = new Random();
	
	public CustomerGenerator(Resto resto) {
		this.resto = resto;
	}
	
	public void run() {
		while(!resto.getState().getType().equals("close")) {
			while(resto.getState().getType().equals("pause")) {}
			
			if(resto.getCustomers().size() < resto.getnChairs()) {
				int chance = rand.nextInt(4);
				
				if(chance == 1) {
					Customer cust = new Customer(Utils.generateName(resto), resto.getCm());
					
					resto.getCustomers().add(cust);
					CustomerThread ct = new CustomerThread(resto, cust);
					ToleranceThread tt = new ToleranceThread(resto, cust);
					ct.start();
					tt.start();
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
