package sample.cafekiosk.unit;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;

class CafekioskTest {

	@Test
	void add_manual_test() {
		Cafekiosk cafekiosk = new Cafekiosk();
		cafekiosk.add(new Americano());

		// 콘솔에 찍어서 확인하는 것은 최종 확인자가 '사람'인 '수동'테스트
		// 뭘 검증하는지, 어떤게 맞는 상황인지 틀린상황인지 알 수 없는 테스트
		// 검증하는 것이 아니라 무조건 통과하는 테스트
		System.out.println(">>> 담긴 음료수 : " + cafekiosk.getBeverages().size());
		System.out.println(">>> 담긴 음료 : " + cafekiosk.getBeverages().get(0).getName());
	}

	@Test
	void add() {
		Cafekiosk cafekiosk = new Cafekiosk();
		cafekiosk.add(new Americano());

		assertThat(cafekiosk.getBeverages()).hasSize(1);
		assertThat(cafekiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}

	@Test
	void remove() {
		Cafekiosk cafekiosk = new Cafekiosk();
		Americano americano = new Americano();

		cafekiosk.add(americano);
		assertThat(cafekiosk.getBeverages()).hasSize(1);

		cafekiosk.remove(americano);
		assertThat(cafekiosk.getBeverages()).isEmpty();
	}

	@Test
	void clear() {
		Cafekiosk cafekiosk = new Cafekiosk();
		Americano americano = new Americano();
		Latte latte = new Latte();

		cafekiosk.add(americano);
		cafekiosk.add(latte);
		assertThat(cafekiosk.getBeverages()).hasSize(2);

		cafekiosk.clear();
		assertThat(cafekiosk.getBeverages()).isEmpty();
	}
}