/**
 * @author Julie Impola, Paula Fiddi
 * 
 * Vending machine that holds products and an inventory of ingredients.
 */
public class VendingMachine {
	public static final String[] INGREDIENTS = { "sugar", "milk", "coffee", "chocolate", "boullion" };
	public static final int NUM_INGREDIENTS = 5;
	public static final int NUM_PRODUCTS = 6;

	private Product[] products;
	private Ingredient[] ingredientsInventory;
	private int currentNumOfProducts;
	private int totalProductsSold;

	public VendingMachine() {
		products = new Product[NUM_PRODUCTS];
		currentNumOfProducts = 0;
		ingredientsInventory = new Ingredient[NUM_INGREDIENTS];
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			ingredientsInventory[i] = new Ingredient(INGREDIENTS[i], 0);
		}
	}
	
	public void addProduct(Product p) {
		if (canAddAnotherProduct()) {
			for (int i = 0; i < NUM_PRODUCTS; i++) {
				if (products[i] == null) {
					products[i] = p;
					break;
				}
			}
			currentNumOfProducts++;
		}
	}
	
	public void deleteProduct(int selection) {
		if (selection >= 0 && selection < NUM_PRODUCTS) {
			if (products[selection] != null) {
				products[selection] = null;
				currentNumOfProducts--;
			}
		}
	}
	
	public void increaseIngredientsInventory(Ingredient[] ingredientAmountsToAdd) {
		if (ingredientAmountsToAdd.length == NUM_INGREDIENTS) {
			for (int i = 0; i < NUM_INGREDIENTS; i++) {
				ingredientsInventory[i].setAmount(ingredientsInventory[i].amount() + ingredientAmountsToAdd[i].amount());
			}
		}
	}
	
	public void sellProduct(Product p) {
		if (haveIngredientsInInventory(p.ingredients())) {
			for (int i = 0; i < NUM_INGREDIENTS; i++) {
				ingredientsInventory[i].setAmount(ingredientsInventory[i].amount() - p.ingredients()[i].amount());
			}
			p.setTimesSold(p.timesSold() + 1);
			totalProductsSold++;
		}
	}
	
	public boolean haveIngredientsInInventory(Ingredient[] ingredients) {
		boolean enoughInventory = true;
		for (int i = 0; i < NUM_INGREDIENTS; i++) {
			if (ingredients[i].amount() > ingredientsInventory[i].amount()) {
				enoughInventory = false;
			}
		}
		return enoughInventory;
	}
	
	public boolean canAddAnotherProduct() {
		return currentNumOfProducts < NUM_PRODUCTS;
	}
	
	public Product[] getProducts() {
		return products;
	}
	
	public Ingredient[] getIngredientsInventory() {
		return ingredientsInventory;
	}
	
	public int getCurrentNumOfProducts() {
		return currentNumOfProducts;
	}
	
	public int getTotalProductsSold() {
		return totalProductsSold;
	}
	
	public void setCurrentNumOfProducts(int num) {
		currentNumOfProducts = num;
	}

	public void setTotalProductsSold(int num) {
		totalProductsSold = num;
	}
}
