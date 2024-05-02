package starbuzz;

public abstract class CaffeineBeverage {

	void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}

	/**
	 * Coffee의 brewCoffeeGrinds()와 Tea의 steepTeaBag() 메소드의 역할:
	 * 뜨거운 물을 사용해 커피나 차를 우려낸다 => brew() 메서드로 추상화
	 */
	abstract void brew();

	/**
	 * Coffee의 addSugarAndMilk()와 Tea의 addLemon() 메소드의 역할:
	 * 커피나 차에 첨가물을 추가한다. => addCondiments() 메소드로 추상화
	 */
	abstract void addCondiments();

	protected void boilWater() {
		System.out.println("물을 끓인다.");
	}

	protected void pourInCup() {
		System.out.println("컵에 붓는다.");
	}

}
