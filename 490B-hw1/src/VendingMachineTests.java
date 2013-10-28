import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Julie Impola, Paula Fiddi
 * 
 * Tests for the VendingMachine class.
 */
public class VendingMachineTests {
	private static Product[] testProducts = new Product[7];
	private static VendingMachine machine;

	/**
	 * Sets up some products to use for testing.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		machine = new VendingMachine();
		Random rand = new Random();
		String[] productNames = {"mocha", "tea", "soda", "pop", "juice", "americano", "latte"};
		double[] productPrices = {3.25, 2.00, 4.99, 1.63, 0.50, 3.00, 1.00};
		int[] productTimesSold = {1, 10, 0, 3, 7, 9, 4};
		for (int j = 0; j < testProducts.length; j++) {
			Ingredient[] ingredients = new Ingredient[VendingMachine.NUM_INGREDIENTS];
			for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
				ingredients[i] = new Ingredient(VendingMachine.INGREDIENTS[i], rand.nextInt(5));
			}
			testProducts[j] = new Product(productNames[j], productPrices[j], productTimesSold[j], ingredients);
		}
	}

	/**
	 * Test to make sure the VendingMachine object gets constructed and initialized correctly.
	 */
	@Test
	public void testVendingMachine() {
		assertEquals(machine.getProducts().length, VendingMachine.NUM_PRODUCTS);
		assertEquals(machine.getIngredientsInventory()[0].name(), "sugar");
		assertEquals(machine.getIngredientsInventory()[0].amount(), 0);
	}

	/**
	 * Test adds the maximum number of products. Then it tries to add another product, and makes
	 * sure the product array isn't modified.
	 */
	@Test
	public void testAddOneTooManyProducts() {
		for (int i = 0; i < VendingMachine.NUM_PRODUCTS; i++) {
			machine.addProduct(testProducts[i]);
			assertEquals(testProducts[i], machine.getProducts()[i]);
		}
		machine.addProduct(testProducts[6]);
		for (int i = 0; i < VendingMachine.NUM_PRODUCTS; i++) {
			assertEquals(testProducts[i], machine.getProducts()[i]);
		}
	}

	/**
	 * Test adds a product, deletes it, then verifies that product spot is null.
	 */
	@Test
	public void testDeleteProduct() {
		machine.addProduct(testProducts[0]);
		assertEquals(testProducts[0], machine.getProducts()[0]);
		machine.deleteProduct(0);
		assertEquals(null, machine.getProducts()[0]);
	}
	
	/**
	 * Test fills the product array, deletes a product in a middle spot,
	 * adds a new product, verifies it got added to the empty spot and didn't
	 * overwrite another product.
	 */
	@Test
	public void testDeleteAndAddInMiddleOfProductArray() {
		for (int i = 0; i < VendingMachine.NUM_PRODUCTS; i++) {
			machine.addProduct(testProducts[i]);
		}
		machine.deleteProduct(3);
		assertEquals(null, machine.getProducts()[3]);
		machine.addProduct(testProducts[6]);
		for (int i = 0; i < VendingMachine.NUM_PRODUCTS; i++) {
			if (i == 3) {
				assertEquals(testProducts[6], machine.getProducts()[i
				                                                    ]);
			} else {
				assertEquals(testProducts[i], machine.getProducts()[i]);
			}
		}
	}

	/**
	 * Test adds a product, adds enough ingredients to the inventory to buy it,
	 * buys the product, verifies the ingredients inventory is decreased correctly,
	 * verifies the product times sold in incremented and total products sold is
	 * incremented.
	 */
	@Test
	public void testBuyProduct() {
		Ingredient[] ingredientsForProduct = new Ingredient[VendingMachine.NUM_INGREDIENTS];
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			ingredientsForProduct[i] = new Ingredient(VendingMachine.INGREDIENTS[i], 2);
		}
		Product p = new Product("mocha", 3.00, 5, ingredientsForProduct);
		machine.addProduct(p);
		addSomeIngredientsToInventory(5);
		machine.sellProduct(p);
		Ingredient[] ingredientsInInventory = machine.getIngredientsInventory();
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			assertEquals(ingredientsInInventory[i].amount(), 3);
		}
		assertEquals(machine.getProducts()[0].timesSold(), 6);
		assertEquals(machine.getTotalProductsSold(), 1);
	}
	
	/**
	 * Test adds a product, adds less ingredients than the product needs to the inventory,
	 * and tries to buy the product. Verifies the ingredients inventory isn't decreased,
	 * verifies the product times sold and total products sold isn't incremented.
	 */
	@Test
	public void testBuyProductNotEnoughInventory() {
		Ingredient[] ingredientsForProduct = new Ingredient[VendingMachine.NUM_INGREDIENTS];
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			ingredientsForProduct[i] = new Ingredient(VendingMachine.INGREDIENTS[i], 2);
		}
		Product p = new Product("mocha", 3.00, 5, ingredientsForProduct);
		machine.addProduct(p);
		addSomeIngredientsToInventory(1);
		machine.sellProduct(p);
		Ingredient[] ingredientsInInventory = machine.getIngredientsInventory();
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			assertEquals(ingredientsInInventory[i].amount(), 1);
		}
		assertEquals(machine.getProducts()[0].timesSold(), 5);
		assertEquals(machine.getTotalProductsSold(), 0);
	}

	/**
	 * Test checks that the initial inventory of each ingredient is zero.
	 * Then it adds some inventory, verifies the correct amount is added.
	 * Then it adds some more, and verifies again.
	 */
	@Test
	public void testIncreaseIngredientsInventory() {
		Ingredient[] ingredientsInInventory = machine.getIngredientsInventory();
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			assertEquals(ingredientsInInventory[i].amount(), 0);
		}
		addSomeIngredientsToInventory(9);
		ingredientsInInventory = machine.getIngredientsInventory();
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			assertEquals(ingredientsInInventory[i].amount(), 9);
		}
		addSomeIngredientsToInventory(2);
		ingredientsInInventory = machine.getIngredientsInventory();
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			assertEquals(ingredientsInInventory[i].amount(), 11);
		}
	}
	
	private void addSomeIngredientsToInventory(int amountOfEach) {
		Ingredient[] ingredientsForInventory = new Ingredient[VendingMachine.NUM_INGREDIENTS];
		for (int i = 0; i < VendingMachine.NUM_INGREDIENTS; i++) {
			ingredientsForInventory[i] = new Ingredient(VendingMachine.INGREDIENTS[i], amountOfEach);
		}
		machine.increaseIngredientsInventory(ingredientsForInventory);
	}

}
