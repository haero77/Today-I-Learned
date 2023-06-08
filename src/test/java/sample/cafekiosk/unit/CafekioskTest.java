package sample.cafekiosk.unit;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

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

	/**
	 * 테스트 케이스 세분화
	 * - 해피 케이스
	 * - 예외 케이스
	 */
	@Test
	void addSeveralBeverages() {
		Cafekiosk cafekiosk = new Cafekiosk();
		Americano americano = new Americano();

		cafekiosk.add(americano, 2); // 현재 자료구조에서는 같은 음료에 대해 같은 인스턴스를 사용.

		assertThat(cafekiosk.getBeverages().get(0)).isEqualTo(americano);
		assertThat(cafekiosk.getBeverages().get(1)).isEqualTo(americano);
	}

	/**
	 * 경계값 테스트
	 */
	@Test
	void addZeroBeverages() {
		Cafekiosk cafekiosk = new Cafekiosk();
		Americano americano = new Americano();

		assertThatThrownBy(() -> cafekiosk.add(americano, 0))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
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

	@Test
	void createOrder() {
		Cafekiosk cafekiosk = new Cafekiosk();
		Americano americano = new Americano();
		cafekiosk.add(americano);

		Order order = cafekiosk.createOrder();

		assertThat(order.getBeverages()).hasSize(1);
		assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}

	@Test
	void createOrderWithCurrentTime() {
		Cafekiosk cafekiosk = new Cafekiosk();
		Americano americano = new Americano();
		cafekiosk.add(americano);

		Order order = cafekiosk.createOrder(LocalDateTime.of(2023, 6, 8, 10, 0));

		assertThat(order.getBeverages()).hasSize(1);
		assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}

	@Test
	void createOrderOutsideOpenTime() {
		Cafekiosk cafekiosk = new Cafekiosk();
		Americano americano = new Americano();
		cafekiosk.add(americano);

		assertThatThrownBy(() -> cafekiosk.createOrder(LocalDateTime.of(2023, 6, 8, 9, 59)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
	}
}