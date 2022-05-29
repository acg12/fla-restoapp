package menus;

import java.util.Scanner;

import factory.StaffGenerator;
import models.Chef;
import models.Resto;
import models.Waiter;
import observer.CustomerGenerator;
import singleton.ReadWriteHighscore;
import singleton.RunGame;
import threads.CustomerThread;
import utils.Validate;

public class Menu {
	
	private final int MAX_CHAIRS = 13;
	private final int MAX_STAFF = 7;
	private final int MAX_SPEED_SKILL = 5;

	public void MainMenu() {
		Scanner scan = new Scanner(System.in);
		Resto resto = null;
		ReadWriteHighscore rwh = ReadWriteHighscore.getInstance();
		rwh.readScores();
		
		int menu = 0;
		do {
			System.out.println("DA RESTO");
			System.out.println("===============");
			System.out.println("1. Play New Restaurant");
			System.out.println("2. High Score");
			System.out.println("3. Exit");
			do {
				System.out.print(">> ");
				try {
					menu = scan.nextInt(); scan.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
					menu = -1;
				}
			} while(menu < 1 || menu > 3);
			
			switch(menu) {
			case 1:
				resto = playGame();
				rwh.saveTempData(resto);
				break;
			case 2:
				rwh.printScores(resto);
				break;
			}
		} while(menu != 3);
		
		// save score to file
		rwh.printScores(resto);
		rwh.saveScore();
	}
	
	public Resto playGame() {
		Resto newResto = newGameMenu();
		
		RunGame game = RunGame.getInstance(newResto);
		
		Thread ingame = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				game.inGame();
			}
			
		});
		
		Thread inpause = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				game.inPause();
			}
			
		});
		
//		generate objects
		CustomerGenerator cg = new CustomerGenerator(newResto);
		StaffGenerator.generateChefs(newResto, newResto.getnChefs());
		StaffGenerator.generateWaiters(newResto, newResto.getnWaiters());
		
		game.startInput();
		ingame.start();
		inpause.start();
		cg.start();
		
		try {
			ingame.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newResto;
	}
	
	public Resto newGameMenu() {
		Scanner scan = new Scanner(System.in);
		String name = "";
		do {
			System.out.println("Input new resto name: ");
			name = scan.nextLine();
		} while(!Validate.isValidRestoName(name));
		
		return new Resto(name);
	}
	
	public Boolean pauseMenu(Resto resto) {
		Scanner scan = new Scanner(System.in);
		int menu = 0;
		do {
			System.out.println();
			System.out.println("------------- PAUSE MENU -------------");
			System.out.println("Status: ");
			System.out.println("===============");
			System.out.println("Money: Rp. " + resto.getMoney());
			System.out.println("Score: " + resto.getScore() + " Points");
			System.out.println("Size : " + resto.getnChairs() + " seats");
			System.out.println();
			System.out.println("1. Continue Business");
			System.out.println("2. Upgrade Restaurant");
			System.out.println("3. Close Business");
			
			do {
				System.out.print("Input [1..3]: ");
				try {
					menu = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
					menu = -1;
				}
			} while(menu < 1 || menu > 3);
			
			switch(menu) {
			case 2:
				upgradeMenu(resto);
				break;
			}
		} while(menu != 1 && menu != 3);
		
		if(menu == 1) {
			return true; // tentuin game nya masih lanjut atau gak
		} else if(menu == 3) {
			return false;
		} else {
			return true;
		}
	}
	
	public void upgradeMenu(Resto resto) {
		Scanner scan = new Scanner(System.in);
		int menu = 0;
		
		int hargaUpsize = 100 * resto.getnChairs();
		do {
			System.out.println();
			System.out.println("Status: ");
			System.out.println("===============");
			System.out.println("Money: Rp. " + resto.getMoney());
			System.out.println("Score: " + resto.getScore() + " Points");
			System.out.println("Size : " + resto.getnChairs() + " seats");
			System.out.println();
			
			System.out.println("1. Increase Restaurant's Seat <Rp. " + hargaUpsize + ">");
			System.out.println("2. Hire New Employee");
			System.out.println("3. Upgrade Waiter <Rp. 150>");
			System.out.println("4. Upgrade Cook <Rp. 150>");
			System.out.println("5. Back to Pause Menu");
			
			do {
				System.out.print("Input [1..5]: ");
				try {
					menu = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
					menu = -1;
				}
			} while(menu < 1 || menu > 5);
			
			switch(menu) {
			case 1:
				if(resto.getnChairs() == MAX_CHAIRS) {
					System.out.println("Max number of seats reached! (" + resto.getnChairs() + ")");
				} else if(resto.getMoney() >= hargaUpsize) {
					resto.setnChairs(resto.getnChairs() + 1);
					resto.setMoney(resto.getMoney() - hargaUpsize);
				} else {
					System.out.println("Not enough money to upgrade!");
				}
				break;
			case 2:
				hireEmployee(resto);
				break;
			case 3:
				upgradeWaiter(resto);
				break;
			case 4:
				upgradeChef(resto);
				break;
			}
		} while(menu != 5);
	}

	private void upgradeChef(Resto resto) {
		Scanner scan = new Scanner(System.in);
		// TODO Auto-generated method stub
		int num = 0;
		int hargaUpgrade = 150;
		do {
			System.out.println("UPGRADE CHEF <Rp. 150>");
			System.out.println("---------------------------------");
			System.out.println("| No. | Initial | Speed | Skill |");
			System.out.println("---------------------------------");
			
			for(int i = 0; i < resto.getnChefs(); i++) {
				Chef temp = resto.getChefs().get(i);
				System.out.printf("| %-3d | %-7s | %-5d | %-5d |\n", (i+1), temp.getName(), temp.getSpeed(), temp.getSkill());
			}

			System.out.println("---------------------------------");
			
			do {
				System.out.print("Input employee's number to upgrade [0 to exit]: ");
				try {
					num = scan.nextInt(); scan.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
					num = -1;
				}
			} while(num < 0 || num > (resto.getnChefs() + 1));
			
			if(num != 0) {
				Chef temp = resto.getChefs().get(num-1);
				if(resto.getMoney() < hargaUpgrade) {
					System.out.println("Not enough money to upgrade!");
				} else {
					String whatUpgrade = "";
					do {
						System.out.print("What to upgrade? [Speed | Skill]: ");
						whatUpgrade = scan.nextLine();
					} while(!whatUpgrade.equals("Speed") && !whatUpgrade.equals("Skill"));
				
					if(whatUpgrade.equals("Speed")) {
						if(temp.getSpeed() == MAX_SPEED_SKILL) {
							System.out.println("Max speed level reached!");
						} else if(resto.getMoney() >= hargaUpgrade) {
							temp.setSpeed(temp.getSpeed() + 1);
							resto.setMoney(resto.getMoney() - hargaUpgrade);
						} else {
							System.out.println("Not enough money to upgrade!");
						}
					} else if(whatUpgrade.equals("Skill")) {
						if(temp.getSkill() == MAX_SPEED_SKILL) {
							System.out.println("Max skill level reached!");
						} else if(resto.getMoney() >= hargaUpgrade) {
							temp.setSkill(temp.getSkill() + 1);
							resto.setMoney(resto.getMoney() - hargaUpgrade);
						} else {
							System.out.println("Not enough money to upgrade!");
						}
					}
				}
			}
		} while(num != 0);
	}

	private void upgradeWaiter(Resto resto) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int num = 0;
		int hargaUpgrade = 150;
		do {
			System.out.println("UPGRADE WAITER <Rp. 150>");
			System.out.println("-------------------------");
			System.out.println("| No. | Initial | Speed |");
			System.out.println("-------------------------");
			
			for(int i = 0; i < resto.getnWaiters(); i++) {
				Waiter temp = resto.getWaiters().get(i);
				System.out.printf("| %-3d | %-7s | %-5d |\n", (i+1), temp.getName(), temp.getSpeed());
			}
			
			System.out.println("-------------------------");
			
			do {
				System.out.print("Input employee's number to upgrade [0 to exit]: ");
				try {
					num = scan.nextInt(); scan.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
					num = -1;
				}
			} while(num < 0 || num > resto.getnWaiters() + 1);
			
			if(num != 0) {
				Waiter temp = resto.getWaiters().get(num-1);
				if(temp.getSpeed() == MAX_SPEED_SKILL) {
					System.out.println("Max speed level reached!");
				} else if(resto.getMoney() >= hargaUpgrade) {
					temp.setSpeed(temp.getSpeed() + 1);
					resto.setMoney(resto.getMoney() - hargaUpgrade);
				} else {
					System.out.println("Not enough money to upgrade!");
				}
			}
		} while(num != 0);
	}

	private void hireEmployee(Resto resto) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int menu = 0;
		
		int hargaWaiter = 150 * resto.getnWaiters();
		int hargaChef = 200 * resto.getnChefs();
		
		do {
			System.out.println();
			System.out.println("Status: ");
			System.out.println("===============");
			System.out.println("Money: Rp. " + resto.getMoney());
			System.out.println("Score: " + resto.getScore() + " Points");
			System.out.println("Size : " + resto.getnChairs() + " seats");
			System.out.println();
			
			System.out.println("HIRE NEW EMPLOYEE");
			System.out.println("1. Hire New Waiter <Rp. " + hargaWaiter + ">");
			System.out.println("2. Hire New Cook <Rp. " + hargaChef + ">");
			System.out.println("3. Back to Upgrade Menu");
			
			do {
				System.out.print("Input [1..3]: ");
				try {
					menu = scan.nextInt(); scan.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
					menu = -1;
				}
			} while(menu < 1 || menu > 3);
			
			switch(menu) {
			case 1:
				if(resto.getnWaiters() == MAX_STAFF) {
					System.out.println("Max number of waiters reached! (" + resto.getnChairs() + ")");
				} else if(resto.getMoney() >= hargaWaiter) {
					resto.setnWaiters(resto.getnWaiters() + 1);
					resto.setMoney(resto.getMoney() - hargaWaiter);
					StaffGenerator.generateWaiters(resto, 1);
				} else {
					System.out.println("Not enough money to upgrade!");
				}
				break;
				
			case 2:
				if(resto.getnChefs() == MAX_STAFF) {
					System.out.println("Max number of chefs reached! (" + resto.getnChairs() + ")");
				} else if(resto.getMoney() >= hargaChef) {
					resto.setnChefs(resto.getnChefs() + 1);
					resto.setMoney(resto.getMoney() - hargaChef);
					StaffGenerator.generateChefs(resto, 1);
				} else {
					System.out.println("Not enough money to upgrade!");
				}
				break;
			}
		} while(menu != 3);
	}
}
