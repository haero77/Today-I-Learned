package sample.cafekiosk.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sample.cafekiosk.unit.beverage.Americano;

class CafekioskTest {

	@Test
	void add() {
		Cafekiosk cafekiosk = new Cafekiosk();
		cafekiosk.add(new Americano());

		// 콘솔에 찍어서 확인하는 것은 최종 확인자가 '사람'인 '수동'테스트
		// 뭘 검증하는지, 어떤게 맞는 상황인지 틀린상황인지 알 수 없는 테스트
		// 검증하는 것이 아니라 무조건 통과하는 테스트
		System.out.println(">>> 담긴 음료수 : " + cafekiosk.getBeverages().size());
		System.out.println(">>> 담긴 음료 : " + cafekiosk.getBeverages().get(0).getName());
	}
}