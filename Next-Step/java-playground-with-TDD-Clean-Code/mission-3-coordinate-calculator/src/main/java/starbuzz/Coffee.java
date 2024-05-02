package starbuzz;

public class Coffee extends CaffeineBeverage {

	@Override
	void brew() {
		System.out.println("필터를 활용해 커피를 내린다.");
	}

	@Override
	void addCondiments() {
		System.out.println("설탕과 우유를 추가한다.");
	}

}
