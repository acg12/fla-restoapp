package models;

import java.util.ArrayList;

import mediator.ChangeMediator;
import state.RestoState;
import state.State;
import state.resto.ActiveResto;

public class Resto {

	private String name;
	private int nChairs;
	private int nWaiters;
	private int nChefs;
	volatile private int money;
	volatile private int score;
	private ChangeMediator cm;
	
	private volatile ArrayList<Waiter> waiters;
	private volatile ArrayList<Chef> chefs;
	private volatile ArrayList<Customer> customers;
	
	volatile RestoState state;

	public Resto(String name) {
		this.name = name;
		this.nChairs = 4;
		this.nWaiters = 2;
		this.nChefs = 2;
		this.money = 1300;
		this.score = 0;
		this.cm = new ChangeMediator();
		this.state = new ActiveResto();
		
		this.waiters = new ArrayList<>();
		this.chefs = new ArrayList<>();
		this.customers = new ArrayList<>();
	}
	
	// SETTER GETTERS

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getnChairs() {
		return nChairs;
	}

	public void setnChairs(int nChairs) {
		this.nChairs = nChairs;
	}

	public int getnWaiters() {
		return nWaiters;
	}

	public void setnWaiters(int nWaiters) {
		this.nWaiters = nWaiters;
	}

	public int getnChefs() {
		return nChefs;
	}

	public void setnChefs(int nChefs) {
		this.nChefs = nChefs;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public ChangeMediator getCm() {
		return cm;
	}

	public void setCm(ChangeMediator cm) {
		this.cm = cm;
	}

	public ArrayList<Waiter> getWaiters() {
		return waiters;
	}

	public ArrayList<Chef> getChefs() {
		return chefs;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public RestoState getState() {
		return state;
	}

	public void setState(RestoState state) {
		this.state = state;
	}
}
