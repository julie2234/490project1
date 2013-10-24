
public class Ingredient {
	private String name;
	private int amount;
	
	public Ingredient(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public String name() {
		return name;
	}
	
	public int amount() {
		return amount;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
