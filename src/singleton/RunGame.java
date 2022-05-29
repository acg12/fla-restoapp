package singleton;

import java.util.Scanner;

import facade.InputFacade;
import menus.Menu;
import models.Chef;
import models.Customer;
import models.Resto;
import models.Waiter;
import state.resto.CloseResto;

public class RunGame {
	
	private static RunGame _instance_ = null;
	
	private volatile Resto resto;
	private Thread getInput;
	
	private RunGame(Resto resto) {
		this.resto = resto;
		this.getInput = new Thread(new InputFacade(resto));
	}
	
	public static RunGame getInstance(Resto resto) {
		if(_instance_ == null) {
			synchronized(RunGame.class) {
				if(_instance_ == null) {
					_instance_ = new RunGame(resto);
				} else {
					_instance_.resto = resto;
				}
			}
		} else {
			_instance_.resto = resto;
		}
		return _instance_;
	}
	
//	FUNCTIONS UNTUK RUN GAME
	
	public void inGame() {
		while(!resto.getState().getType().equals("close")) {
			while(resto.getState().getType().equals("pause")) {}
			
			if(!resto.getState().getType().equals("close")) {
				printResto();
				printCustomers();
				printWaiters();
				printChefs();				
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
	
	private void printChefs() {
		// TODO Auto-generated method stub
		System.out.println("Chefs:");
		System.out.println("----------------------------");
		for(Chef c : resto.getChefs()) {
			System.out.println(c.getName() + "," + c.getState().printState());
		}
		System.out.println();
	}

	private void printWaiters() {
		// TODO Auto-generated method stub
		System.out.println("Waiters:");
		System.out.println("----------------------------");
		for(Waiter c : resto.getWaiters()) {
			System.out.println(c.getName() + "," + c.getState().printState());
		}
		System.out.println();
	}

	private void printCustomers() {
		// TODO Auto-generated method stub
		System.out.println("Customers:");
		System.out.println("----------------------------");
		for(Customer c : resto.getCustomers()) {
			System.out.println(c.getName() + " <" + c.getTolerance() + ">," + c.getState().printState());
		}
		System.out.println();
	}

	private void printResto() {
		// TODO Auto-generated method stub
		System.out.println("Restaurant '" + resto.getName() + "' is on Business!");
		System.out.println();
		System.out.println("Status:");
		System.out.println("================");
		System.out.println("Money: Rp. " + resto.getMoney());
		System.out.println("Score: " + resto.getScore() + " Points");
		System.out.println("Size : " + resto.getnChairs() + " seats");
		System.out.println();
	}

	synchronized public void inPause() {
		Menu menu = new Menu();
		Boolean game = true;
		while(!resto.getState().getType().equals("close")) {
			while(resto.getState().getType().equals("active")) {}
			
			game = menu.pauseMenu(this.resto);
			if(!game) {
				break;
			}
			resto.getState().change(resto);
		}
		resto.setState(new CloseResto());
	}
	
	public void startInput() {
		this.getInput.start();
	}

	public Resto getResto() {
		return resto;
	}

	public void setResto(Resto resto) {
		this.resto = resto;
	}
}
