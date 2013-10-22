import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VendingMachine {
	private static final String[] INGREDIENTS = { "sugar", "milk", "coffee", "chocolate", "boullion" };
	private static final int NUM_INGREDIENTS = 5;
	private static final int NUM_PRODUCTS = 6;

	private static Product[] products;
	private static Ingredient[] ingredientsInventory;
	private static int currentNumOfProducts;
	private static int totalProductsSold;

	public VendingMachine() {
		products = new Product[NUM_PRODUCTS];
		currentNumOfProducts = 0;
		ingredientsInventory = new Ingredient[NUM_INGREDIENTS];
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			ingredientsInventory[i] = new Ingredient(INGREDIENTS[i], 0);
		}
	}

	public static void main(String[] args) {
		VendingMachine machine = new VendingMachine();
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
				case 1 : addProduct();
						 break;
				case 2 : deleteProduct();
						 break;
				case 3 : increaseIngredientsInventory();
						 break;
				case 4 : checkIngredientsInventory();
						 break;
				case 5 : buyProduct();
						 break;
				case 6 : statistics();
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

	public static void addProduct() {
		if (currentNumOfProducts == NUM_PRODUCTS) {
			System.out.println("Already " + NUM_PRODUCTS + " products, no more!");
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
				products[currentNumOfProducts] = p;
				currentNumOfProducts++;
				System.out.println("name: " + p.name() + "\nprice: " + p.price());
				for (int j = 0; j < NUM_INGREDIENTS; j++) {
					System.out.println(p.ingredients()[j].name() + ": " + p.ingredients()[j].amount());
				}
			} catch (IOException e) {
			}
		}
		mainMenu();
	}
	
	private static Ingredient[] addIngredients() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		Ingredient[] ingredients = new Ingredient[NUM_INGREDIENTS];
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			System.out.println("Enter units of " + INGREDIENTS[i] + ":");
			try {
				try {
					if ((temp = br.readLine()) == null) {
					} else {
						ingredients[i] = new Ingredient(INGREDIENTS[i], Integer.parseInt(temp));
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

	public static void deleteProduct() {
		System.out.println("Select which product to delete: ");
		for(int i = 0; i < NUM_PRODUCTS; i++){
			if(products[i] != null){
				System.out.println(" " + i + ":  " + products[i].name());
			}
		}
		System.out.println(" " + NUM_PRODUCTS + ":  Cancel\n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		int selection = NUM_PRODUCTS;
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
		
		if(selection == NUM_PRODUCTS){
			mainMenu();
		}
		if(selection > NUM_PRODUCTS){
			System.out.println("\nNo product with that number, please choose again.\n");
			deleteProduct();
		}
		if(products[selection] == null){
			System.out.println("\nThere's no product to delete.\n");
			mainMenu();
		}
		products[selection] = null;
		System.out.println("\nProduct deleted.\n");
		mainMenu();
	}

	public static void buyProduct() {
		String line = "";
		int selection = 0;
		double money = 0.00;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select item to buy: ");
		for(int n = 0; n < 6; n++){
			if(products[n] != null) {
			System.out.println(" " + n + ":  " + products[n].name() + "  $  " + products[n].price());
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
		System.out.println(products[selection].name() + "  $"  + products[selection].price());
		System.out.println("Please enter amount of money deposited: ");
		System.out.print("$  ");
		line = br.readLine();
		money = Double.parseDouble(line);
		} catch (NumberFormatException n) {
			System.out.println("Incorect input, returning to main menu."); 
								mainMenu();
		}
		} catch (IOException e) {}
		decreaseIngredientsInventory(products[selection], money);
	}

	public static void increaseIngredientsInventory() {
		Ingredient[] ingredientAmountsToAdd = addIngredients();
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			ingredientsInventory[i].setAmount(ingredientsInventory[i].amount() + ingredientAmountsToAdd[i].amount());
		}
		System.out.println("Inventory added, thank you");
		mainMenu();
	}

	public static void decreaseIngredientsInventory(Product p, double money) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = "";
		double difference = 0.00;
		try{
		try{
			for (int i = 0; i < NUM_INGREDIENTS; i++) {
				if (p.ingredients()[i].amount() > ingredientsInventory[i].amount()) {
					System.out.println("Insufficient inventory.");
					mainMenu();
				}
			}
			difference = money - p.price();
			if(difference >= 0) {
				correctPayment(difference, p);
			}
			if(difference < 0) {
				System.out.println("Insufficient money deposited. Please deposit " + difference);
				System.out.println("Please enter amount:  ");
				System.out.print("$  ");
				num = br.readLine();
				double extra = Double.parseDouble(num);
				money += extra;
				difference = money - p.price();
				while( difference < 0 ) {
					System.out.println("Change returned:  " + money);
					System.out.println("Sorry, but you only get two chances."+
							"\nReturning to Main Menu");
					System.out.println("");
					mainMenu();
				}
				if (difference >= 0) {
					correctPayment(difference, p);
				}
			}				
		}catch(NumberFormatException n) {
			System.out.println("Incorrect input, returning to main menu.");
			mainMenu();
		}
		}catch(IOException e) {}
		
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			ingredientsInventory[i].setAmount(ingredientsInventory[i].amount() - p.ingredients()[i].amount());
		}		
		System.out.println("Thank You.");
		mainMenu();
	}
	
	private static void correctPayment(double difference, Product p) {
		System.out.println("Change returned:  $" + difference);
		System.out.println(p.name() + "   dispensed.");
		System.out.println("");
		p.setTimesSold(p.timesSold() + 1);
		totalProductsSold++;
	}

	public static void checkIngredientsInventory() {
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			System.out.println(ingredientsInventory[i].name() + ": " + ingredientsInventory[i].amount());
		}
		System.out.println();
		mainMenu();
	}

	public static void statistics() {
		double percentSold = 0.0;
		System.out.println("total products sold: " + totalProductsSold);
		for(int i = 0; i < NUM_PRODUCTS; i++) {
			if(products[i] != null){
				if(totalProductsSold > 0) {
					percentSold = (((double)products[i].timesSold()/(double)totalProductsSold)*100);
					System.out.println("Item name:  " + products[i].name() + "  Quantity Sold:  " + products[i].timesSold()
								   +  "  % Sold:  " + percentSold );
					products[i].setTimesSold(0);
				}
				else {
					System.out.println("No " + products[i].name() + " have been bought at this time, returning to main menu.");
				}					
			}
		}
		totalProductsSold = 0;
		mainMenu();
	}
}
