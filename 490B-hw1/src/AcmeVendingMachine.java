import java.*;
import java.io.*;
import java.util.*;
import java.text.*;


class AcmeVendingMachine {
	private static final String[] INGREDIENTS = { "sugar", "milk", "coffee", "chocolate", "boullion" };
	private static final int NUM_INGREDIENTS = 5;
	private static final int NUM_RECIPES = 6;

	static Recipe[] recipeArray = new Recipe[NUM_RECIPES];
	private static Ingredient[] ingredientsInventory;
	static int i = 0;
	static double money = 0;
	static double extra = 0;
	static double difference = 0;
	static int item = 0;
	static int totalDrinksSold = 0;
	

	public AcmeVendingMachine() {
		ingredientsInventory = new Ingredient[NUM_INGREDIENTS];
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			ingredientsInventory[i] = new Ingredient(INGREDIENTS[i], 0);
		}
	}

	public static class Recipe {
		
		String name;
		double price;
		private static Ingredient[] ingredients;
		int timesSold;
		
		
		public Recipe () {
			name = "";
			price = 0.0;
			ingredients = null;
			timesSold = 0;
		}
		
		public Recipe (String name, double price, Ingredient[] ingredients)        {
			this.name = name;
			this.price = price;
			this.ingredients = ingredients;		
			timesSold = 0;
		}
	
	}
	

	public static void  AddInventory () {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ObjectInputStream input = null;

		String line = "";
		
		System.out.println("");
		System.out.println("");
				
		try{

		System.out.println("Enter units of " + ingredientsInventory[0].name() + ": ");
		try{
		if((line = br.readLine()) == null){
			System.out.println("Please enter an amount next time, going back to Main Menu");
			mainMenu();} 
			else {
				ingredientsInventory[0].setAmount(ingredientsInventory[0].amount() + Integer.parseInt(line));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}
		

		System.out.println("Enter units of " + ingredientsInventory[1].name() + ": ");
		try{
		if((line = br.readLine()) == null){} 
			else {
				ingredientsInventory[1].setAmount(ingredientsInventory[1].amount() + Integer.parseInt(line));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}
		

		System.out.println("Enter units of " + ingredientsInventory[2].name() + ": ");
		try{
		if((line = br.readLine()) == null){} 
			else {
				ingredientsInventory[2].setAmount(ingredientsInventory[2].amount() + Integer.parseInt(line));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}
		

		System.out.println("Enter units of " + ingredientsInventory[3].name() + ": ");
		try{
		if((line = br.readLine()) == null){} 
			else {
				ingredientsInventory[3].setAmount(ingredientsInventory[3].amount() + Integer.parseInt(line));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}

		System.out.println("Enter units of " + ingredientsInventory[4].name() + ": ");
		try{
		if((line = br.readLine()) == null){} 
			else {
				ingredientsInventory[4].setAmount(ingredientsInventory[4].amount() + Integer.parseInt(line));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}
		
		} catch (IOException e) {}
		System.out.println();
		System.out.println("Inventory added, thank you");
		mainMenu();
	}
	

	public static void CheckInventory () {
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			System.out.println(ingredientsInventory[i].name() + ": " + ingredientsInventory[i].amount());
		}
		System.out.println();
		mainMenu();
	} 
	

	public static int  checkRecipes() {
		for(int i = 0; i < NUM_RECIPES; i++){ 
		if(recipeArray[i].name == "") {
				return i;
			}
		}
	    return 13;
	}//end checkRecipes
				
	//adds a recipe
	public static void AddRecipe () throws NumberFormatException {
		int empty = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		InputStreamReader in = new InputStreamReader(System.in);
		
		//check to see if there is space in array.  returns 13 if space is unavailable
		empty = checkRecipes();
		if(empty == 13) {
			System.out.println("Already " + NUM_RECIPES + " recipes made, no more!");
			System.out.println("");
			mainMenu();
		}
		else { i = empty;}         
			
		//Variables to hold input
		String rec_name = "";
		String temp = "";		//holds name
		int numtemp = 0;		//error checking for integer input
		double rec_price = 0.0;
		Ingredient[] rec_ingredients = new Ingredient[NUM_INGREDIENTS];
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			rec_ingredients[i] = new Ingredient(INGREDIENTS[i], 0);
		}
		try{
			//get name
			System.out.println("");
			System.out.println("Enter name of recipe:");
			
			if((temp = br.readLine()) == null){
				System.out.println("Please enter a name next time, going back to Main Menu");
				mainMenu();} 
			else {
				rec_name = temp;
			}
			

			System.out.println("Enter price of recipe, in decimal format (0.00):");
			System.out.print("$  ");
			try{
			if((temp = br.readLine()) == null){} 
			else {
				rec_price = Double.parseDouble(temp);
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}
				
				
			//get sugar
			System.out.println("Enter units of " + ingredientsInventory[0].name() + ":");
			try{
			if((temp = br.readLine()) == null){} 
			else {
				rec_ingredients[0].setAmount(Integer.parseInt(temp));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}
			
			
			//get milk
			System.out.println("Enter units of " + ingredientsInventory[1].name() + ":");
			try{
			if((temp = br.readLine()) == null){} 
			else {
				rec_ingredients[1].setAmount(Integer.parseInt(temp));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}
			//get coffee
			System.out.println("Enter units of " + ingredientsInventory[2].name() + ":");
			try{
			if((temp = br.readLine()) == null){} 
			else {
				rec_ingredients[2].setAmount(Integer.parseInt(temp));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}

			System.out.println("Enter units of " + ingredientsInventory[3].name() + ":");
			try{
			if((temp = br.readLine()) == null){} 
			else {
				rec_ingredients[3].setAmount(Integer.parseInt(temp));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}

			System.out.println("Enter units of " + ingredientsInventory[4].name() + ":");
			try{
			if((temp = br.readLine()) == null){} 
			else {
				rec_ingredients[4].setAmount(Integer.parseInt(temp));
			}
			} catch (NumberFormatException n) {
				System.out.println("Please enter an amount next time, going back to Main Menu");
				mainMenu();
			}

			System.out.println("Name: " + rec_name + "\n  Price: " + rec_price);
			for (int i = 0; i < NUM_INGREDIENTS; i++) {
				System.out.println(rec_ingredients[i].name() + ": " + rec_ingredients[i].amount());
			}
			System.out.println();
		}catch (IOException e) {}
		
		recipeArray[i] = new Recipe(rec_name, rec_price, rec_ingredients);
		i++;
		
		mainMenu();
	}//end AddRecipe
	

	public static void DeleteRecipe () throws NumberFormatException  {
		String num = "";
		int num_select = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Select which recipe to delete: ");
		for(int n = 0; n < NUM_RECIPES; n++){
			if(recipeArray[n].name != ""){
				System.out.println(" " + n + ":  " + recipeArray[n].name);
			}
		}
		System.out.println(" " + NUM_RECIPES + ":  Cancel");
		try{
		try{ 
			System.out.println("");
			if((num = br.readLine()) == null){
				System.out.println("whoops");
				mainMenu();
			} else {
			    num_select = Integer.parseInt(num);
			}
		} catch (NumberFormatException e) {
			   System.out.println("Incorrect input, return to main menu.");
			   mainMenu();
		  }
		} catch (IOException n){}
		
		if(num_select == NUM_RECIPES){
			mainMenu();
		}
		if(num_select > (NUM_RECIPES - 1)){
			System.out.println("Sorry, please choose again.");
			DeleteRecipe();
		}
		

		if(recipeArray[num_select].name == ""){
			System.out.println("");
			System.out.println("There's no recipe to delete.");
			System.out.println("");
			mainMenu();
		}
		recipeArray[num_select] = new Recipe();
		System.out.println("");
		mainMenu();
		
		
	}
	public static void BuyItem() {
		String line = "";
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please select item to buy: ");
		for(int n = 0; n < NUM_RECIPES; n++){
			if(recipeArray[n].name != "") {
			System.out.println(" " + n + ":  " + recipeArray[n].name + "  $  "
							   + recipeArray[n].price);
			}
			else 
				System.out.println(" " + n + ":" + "  No recipe.");
		}
		
		try{ 
		try{
			System.out.println("");
			line = br.readLine();
			item = Integer.parseInt(line);
			if(item > (NUM_RECIPES - 1) || item < 0){
				System.out.println("Incorrect input, returning to main menu");
				mainMenu();
			}
						
					
		System.out.println(recipeArray[item].name + "  $"  + recipeArray[item].price);
		System.out.println("Please enter amount of money deposited: ");
		System.out.print("$  ");
		line = br.readLine();
		money = Double.parseDouble(line);
		} catch (NumberFormatException n) {
			System.out.println("Incorect input, returning to main menu."); 
								mainMenu();
		}
		} catch (IOException e) {}
		
		checkItem();
						      
		
		
	}//end BuyItem
		
	public static void checkItem () {//
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = "";
		
		try{
		try{
			
			if(recipeArray[item].ingredients[0].amount() <= ingredientsInventory[0].amount() && 
			   recipeArray[item].ingredients[1].amount() <= ingredientsInventory[1].amount() &&
			   recipeArray[item].ingredients[2].amount() <= ingredientsInventory[2].amount() &&
			   recipeArray[item].ingredients[3].amount() <= ingredientsInventory[3].amount() &&
			   recipeArray[item].ingredients[4].amount() <= ingredientsInventory[4].amount()){//
				//gets money
				difference = money - recipeArray[item].price;
				
			}	
			else {  System.out.println("Insufficient inventory.");  			
					System.out.println("Change returned: " + money);
					mainMenu();
			}
			if(difference == 0){//
				System.out.println(recipeArray[item].name + "   dispensed.");
				System.out.println("");
				recipeArray[item].timesSold += 1;
				totalDrinksSold = totalDrinksSold + 1;
			}// end ==0	
			if(difference > 0){//
				System.out.println("Change returned:  $" + difference);
				System.out.println(recipeArray[item].name + " dispensed.");
				System.out.println("");
				totalDrinksSold = totalDrinksSold + 1;
				recipeArray[item].timesSold += 1;
			}//end >0
			if(difference < 0){//(1)
				difference = 0 - difference;
				System.out.println("Insufficient money deposited. Please deposit " + difference);
				difference = 0 - difference;
				System.out.println("Please enter amount:  ");
				System.out.print("$  ");
				num = br.readLine();
				extra = Double.parseDouble(num);
				money += extra;
				difference = money - recipeArray[item].price;
				while( difference <= 0 ) {
					if(difference == 0){//
						System.out.println(recipeArray[item].name + " dispensed.");
						System.out.println("");
						totalDrinksSold = totalDrinksSold + 1;
						recipeArray[item].timesSold += 1;
						break;
					}// end ==0	
					
					if(difference < 0) {//(2)
						System.out.println("Change returned:  " + money);
						System.out.println("Sorry, but you only get two chances."+
										   "\nReturning to Main Menu");
						System.out.println("");
						mainMenu();
					}//end <0(2)
				}//end <0(1)
				
				if(difference > 0){//
						//difference = 0 - difference;
						System.out.println("Change returned:  $" + difference);
						System.out.println(recipeArray[item].name + " dispensed.");
						System.out.println("");
						totalDrinksSold = totalDrinksSold + 1;
						recipeArray[item].timesSold += 1;
						
					}//end >0
			}				
		}catch(NumberFormatException n) {
			System.out.println("Incorrect input, returning to main menu.");
			mainMenu();
		}
		}catch(IOException e) {}
		
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			ingredientsInventory[i].setAmount(ingredientsInventory[i].amount() - recipeArray[item].ingredients[i].amount());
		}
		
		System.out.println("Thank You.");
		mainMenu();
	}//end checkItem
	
	//displays the customer analysis
	public static void CustomerAnalysis() {
		double percentSold = 0.0;
		System.out.println("total drinks sold: " + totalDrinksSold);
		
		for(int i = 0; i < NUM_RECIPES; i++){
			if(recipeArray[i].name != ""){
				if(totalDrinksSold > 0 ) {
					percentSold = (((double)recipeArray[i].timesSold/(double)totalDrinksSold)*100);
					System.out.println("Item name:  " + recipeArray[i].name + "  Quantity Sold:  " + recipeArray[i].timesSold
								   +  "  % Sold:  " + percentSold );
					recipeArray[i].timesSold = 0;
				}
				else {
					System.out.println("No " + recipeArray[i].name + " have been bought at this time, returning to main menu.");
				}					
			}
		}
		totalDrinksSold = 0;
		
		mainMenu();
	}
	
	//displays the main menu
	public static void mainMenu () {
		String temp = "";
		int selection = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("");
		System.out.println("Please enter an action...");
		System.out.println("1 = Add Recipe");
		System.out.println("2 = Delete Recipe");
		System.out.println("3 = Add Inventory");
		System.out.println("4 = Check Inventory");
		System.out.println("5 = Buy Item");
		System.out.println("6 = Customer Analysis");
		System.out.println("7 = Exit");
		System.out.println("");
		System.out.print("=> ");
		
		try{
		try {
			temp = br.readLine();
			selection = Integer.parseInt(temp);
		
		if(selection == 1) {
			AddRecipe();
		}		
		if(selection == 2) {
			DeleteRecipe();
		}
		if(selection == 3) {
			AddInventory();
		}
		if(selection == 4) {
			CheckInventory();
		}
		if(selection == 5) {
			BuyItem();
		}
		if(selection == 6) {
			CustomerAnalysis();
		}
		if(selection == 7) {
			System.exit(0);
		}
		else {
			System.out.println("");
			System.out.println("Not a valid selection, please choose again.");
			System.out.println("");
			mainMenu();
		}
		}catch (NumberFormatException n){
			mainMenu();
		}
		} catch (IOException e)
		{}
	}//end mainMenu
		
	
	//the main function; it sets up the array to hold recipes.
	public static void main (String[] arg) {
		AcmeVendingMachine vendingMachine = new AcmeVendingMachine();
		for(int i =0; i<NUM_RECIPES; i++){
			recipeArray[i] = new Recipe();
		}
		
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
	
	
	
	
	
}