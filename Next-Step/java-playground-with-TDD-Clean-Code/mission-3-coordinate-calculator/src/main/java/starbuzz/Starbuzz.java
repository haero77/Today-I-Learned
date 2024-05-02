package starbuzz;

public class Starbuzz {

	public static void main(String[] args) {
		final Coffee coffee = new Coffee();
		coffee.boilWater();
		coffee.pourInCup();

		final Tea tea = new Tea();
		tea.boilWater();
		tea.pourInCup();
	}

}
