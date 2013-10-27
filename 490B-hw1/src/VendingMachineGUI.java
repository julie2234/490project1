import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class VendingMachineGUI {
	private static VendingMachine machine;
	
	public static void main(String[] args) {
		machine = new VendingMachine();
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("                     ACME VENDING MACHINE            ");
		System.out.println("                          version 1.2                ");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		mainMenu();
	}
	
	public static void mainMenu() {
		System.out.println("");
		System.out.println("Please enter an action...");
		System.out.println("1 = Add Product");
		System.out.println("2 = Delete Product");
		System.out.println("3 = Add Inventory");
		System.out.println("4 = Check Inventory");
		System.out.println("5 = Buy Item");
		System.out.println("6 = Customer Analysis");
		System.out.println("7 = Exit");
		System.out.println("");
		System.out.print("=> ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			try {
				int selection = Integer.parseInt(br.readLine());
				switch (selection) {
				case 1 : addProductPrompt();
						 break;
				case 2 : deleteProductPrompt();
						 break;
				case 3 : increaseIngredientsInventoryPrompt();
						 break;
				case 4 : checkIngredientsInventoryPrompt();
						 break;
				case 5 : buyProductPrompt();
						 break;
				case 6 : statisticsPrompt();
						 break;
				case 7 : System.exit(0);
						 break;
				default: System.out.println("\nNot a valid selection, please choose again.\n");
						 mainMenu();
				}
			} catch (NumberFormatException n) {
				mainMenu();
			}
		} catch (IOException e) {
		}
	}
	
	public static void addProductPrompt() {
		if (!machine.canAddAnotherProduct()) {
			System.out.println("Already " + VendingMachine.NUM_PRODUCTS + " products, no more!");
			System.out.println("");
		} else {
			Product p = new Product();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String temp;
			try {
				System.out.println("\nEnter product name:");
				if ((temp = br.readLine()) == null) {
					System.out.println("Please enter a name next time, going back to Main Menu");
					mainMenu();
				} else {
					p.setName(temp);
				}
				System.out.println("Enter price of product, in decimal format (0.00):");
				System.out.print("$  ");
				try {
					if ((temp = br.readLine()) == null) {
					} else {
						p.setPrice(Double.parseDouble(temp));
					}
				} catch (NumberFormatException n) {
					System.out.println("Please enter an amount next time, going back to Main Menu");
					mainMenu();
				}
				p.setIngredients(addIngredients());
				machine.addProduct(p);
				System.out.println("name: " + p.name() + "\nprice: " + p.price());
				for (int j = 0; j < VendingMachine.NUM_INGREDIENTS; j++) {
					System.out.println(p.ingredients()[j].name() + ": " + p.ingredients()[j].amount());
				}
			} catch (IOException e) {
			}
		}
		mainMenu();
	}
	
	public static void deleteProductPrompt() {
		System.out.println("Select which product to delete: ");
		for(int i = 0; i < VendingMachine.NUM_PRODUCTS; i++){
			if(machine.getProducts()[i] != null){
				System.out.println(" " + i + ":  " + machine.getProducts()[i].name());
			}
		}
		System.out.println(" " + VendingMachine.NUM_PRODUCTS + ":  Cancel\n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		int selection = VendingMachine.NUM_PRODUCTS;
		try{
		try{ 
			if((temp = br.readLine()) == null){
				System.out.println("\nNo input, returning to main menu.\n");
				mainMenu();
			} else {
			    selection = Integer.parseInt(temp);
			}
		} catch (NumberFormatException e) {
			   System.out.println("\nIncorrect input, return to main menu.\n");
			   mainMenu();
		  }
		} catch (IOException n){}
		
		if(selection == VendingMachine.NUM_PRODUCTS){
			mainMenu();
		}
		if(selection > VendingMachine.NUM_PRODUCTS){
			System.out.println("\nNo product with that number, please choose again.\n");
			deleteProductPrompt();
		}
		if(machine.getProducts()[selection] == null){
			System.out.println("\nThere's no product to delete.\n");
			mainMenu();
		}
		machine.deleteProduct(selection);
		System.out.println("\nProduct deleted.\n");
		mainMenu();
	}
	
	public static void increaseIngredientsInventoryPrompt() {
		machine.increaseIngredientsInventory(addIngredients());
		System.out.println("Inventory added, thank you");
		mainMenu();
	}
	
	private static Ingredient[] addIngredients() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		Ingredient[] ingredients = new Ingredient[VendingMachine.NUM_INGREDIENTS];
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			System.out.println("Enter units of " + VendingMachine.INGREDIENTS[i] + ":");
			try {
				try {
					if ((temp = br.readLine()) == null) {
					} else {
						ingredients[i] = new Ingredient(VendingMachine.INGREDIENTS[i], Integer.parseInt(temp));
					}
				} catch (IOException e) {
				}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}
		}
		return ingredients;
	}

	public static void checkIngredientsInventoryPrompt() {
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			System.out.println(machine.getIngredientsInventory()[i].name() + ": " + machine.getIngredientsInventory()[i].amount());
		}
		System.out.println();
		mainMenu();
	}

	public static void buyProductPrompt() {
		String line = "";
		int selection = 0;
		double money = 0.00;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select item to buy: ");
		for(int n = 0; n < 6; n++){
			if(machine.getProducts()[n] != null) {
			System.out.println(" " + n + ":  " + machine.getProducts()[n].name() + "  $  " + machine.getProducts()[n].price());
			}
			else 
				System.out.println(" " + n + ":" + "  No product.");
		}
		try{ 
		try{
			System.out.println("");
			line = br.readLine();
			selection = Integer.parseInt(line);
			if(selection > 5 || selection < 0){
				System.out.println("Incorrect input, returning to main menu");
				mainMenu();
			}	
		System.out.println(machine.getProducts()[selection].name() + "  $"  + machine.getProducts()[selection].price());
		System.out.println("Please enter amount of money deposited: ");
		System.out.print("$  ");
		line = br.readLine();
		money = Double.parseDouble(line);
		} catch (NumberFormatException n) {
			System.out.println("Incorect input, returning to main menu."); 
								mainMenu();
		}
		} catch (IOException e) {}
		if (!machine.haveIngredientsInInventory(machine.getProducts()[selection].ingredients())) {
			System.out.println("Insufficient inventory.");
		} else {
			paymentPrompt(machine.getProducts()[selection], money);
		}
		mainMenu();
	}



	public static void paymentPrompt(Product p, double money) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = "";
		double difference = 0.00;
		try{
		try{
			difference = money - p.price();
			if(difference >= 0) {
				correctPayment(difference, p);
			} else if(difference < 0) {
				System.out.println("Insufficient money deposited. Please deposit " + (difference * (-1)));
				System.out.println("Please enter amount:  ");
				System.out.print("$  ");
				num = br.readLine();
				double extra = Double.parseDouble(num);
				money += extra;
				difference = money - p.price();
				if (difference < 0) {
					System.out.println("Change returned:  " + money);
					System.out.println("Sorry, but you only get two chances." + "\nReturning to Main Menu");
				} else if (difference >= 0) {
					correctPayment(difference, p);
				}
			}				
		}catch(NumberFormatException n) {
			System.out.println("Incorrect input, returning to main menu.");
		}
		}catch(IOException e) {}
	}
	
	private static void correctPayment(double difference, Product p) {
		System.out.println("Change returned:  $" + difference);
		System.out.println(p.name() + "   dispensed.");
		System.out.println("");
		System.out.println("Thank You.");
		machine.sellProduct(p);		
	}

	public static void statisticsPrompt() {
		double percentSold = 0.0;
		System.out.println("total products sold: " + machine.getTotalProductsSold());
		for(int i = 0; i < VendingMachine.NUM_PRODUCTS; i++) {
			if(machine.getProducts()[i] != null){
				if(machine.getTotalProductsSold() > 0) {
					percentSold = (((double)machine.getProducts()[i].timesSold()/(double)machine.getTotalProductsSold())*100);
					System.out.println("Item name:  " + machine.getProducts()[i].name() + "  Quantity Sold:  " + machine.getProducts()[i].timesSold()
								   +  "  % Sold:  " + percentSold );
					machine.getProducts()[i].setTimesSold(0);
				}
				else {
					System.out.println("No " + machine.getProducts()[i].name() + " have been bought at this time, returning to main menu.");
				}					
			}
		}
		machine.setTotalProductsSold(0);
		mainMenu();
	}
}
