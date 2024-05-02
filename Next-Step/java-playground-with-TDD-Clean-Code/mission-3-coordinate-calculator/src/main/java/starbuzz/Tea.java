package starbuzz;

public class Tea extends CaffeineBeverage {

	@Override
	void brew() {
		System.out.println("티백을 담근다.");
	}

	@Override
	void addCondiments() {
		System.out.println("레몬을 추가한다.");
	}

}