package utils;

import models.Chef;
import models.Customer;
import models.Resto;
import models.Waiter;

public class Validate {
	
	public static Boolean isValidRestoName(String name) {
		if(name.length() < 3 || name.length() > 15) {
			return false;
		}
		return true;
	}
	
	public static Boolean isUniqueName(Resto resto, String name) {
		for(Customer c : resto.getCustomers()) {
			if(c.getName().equals(name)) {
				return false;
			}
		}
		
		for(Waiter w : resto.getWaiters()) {
			if(w.getName().equals(name)) {
				return false;
			}
		}
		
		for(Chef c : resto.getChefs()) {
			if(c.getName().equals(name)) {
				return false;
			}
		}
		
		return true;
	}
}
