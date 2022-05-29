package factory;

import models.Chef;
import models.Customer;
import models.Resto;
import models.Waiter;
import threads.ChefThread;
import threads.WaiterThread;
import utils.Utils;

public class StaffGenerator {
	
	public static void generateChefs(Resto resto, int n) {
		ChefFactory cf = new ChefFactory();
		
		for(int i = 0; i < n; i++) {
			Chef chef = cf.recruitStaff(Utils.generateName(resto), resto.getCm());
			resto.getChefs().add(chef);
			
			ChefThread ct = new ChefThread(resto, chef);
			ct.start();
		}
	}
	
	public static void generateWaiters(Resto resto, int n) {
		WaiterFactory wf = new WaiterFactory();
		
		for(int i = 0; i < n; i++) {
			Waiter waiter = wf.recruitStaff(Utils.generateName(resto), resto.getCm());
			resto.getWaiters().add(waiter);
			
			WaiterThread wt = new WaiterThread(resto, waiter);
			wt.start();
		}
	}
}
