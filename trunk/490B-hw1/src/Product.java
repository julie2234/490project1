/**
 * @author Julie Impola, Paula Fiddi
 * 
 */
public class Product {
	private String name;
	private double price;
	private int timesSold;
	private Ingredient[] ingredients;
	
	public Product() {
		this("", 0.0, 0, null);
	}
	
	public Product(String name, double price, int timesSold, Ingredient[] ingredients) {
		this.name = name; 
		this.price = price;
		this.timesSold = timesSold;
		this.ingredients = ingredients;
	}
	
	public String name() {
		return name;
	}
	
	public double price() {
		return price;
	}
	
	public int timesSold() {
		return timesSold;
	}
	
	public Ingredient[] ingredients() {
		return ingredients;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setTimesSold(int timesSold) {
		this.timesSold = timesSold;
	}
	
	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}
}
