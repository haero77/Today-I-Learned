package starbuzz;

public class Tea extends CaffeineBeverage {

	void prepareRecipe() {
		boilWater();
		steepTeaBag();
		pourInCup();
		addLemon();
	}

	public void steepTeaBag() {
		System.out.println("티백을 담근다.");
	}

	public void addLemon() {
		System.out.println("레몬을 추가한다.");
	}

}