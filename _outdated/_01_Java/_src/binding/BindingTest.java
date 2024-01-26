package _01_Java._src.binding;

public class BindingTest {

	public static void main(String[] args) {

		SuperClass superClass = new SuperClass();
		superClass.staticMethod();
		superClass.instanceMethod();

		SuperClass subClass = new SubClass();
		subClass.staticMethod();
		subClass.instanceMethod();
	}
}

class SuperClass {

	static void staticMethod() {
		System.out.println("SuperClass::staticMethod");
	}

	void instanceMethod() {
		System.out.println("SuperClass::instanceMethod");
	}
}

class SubClass extends SuperClass {

	static void staticMethod() {
		System.out.println("SubClass::staticMethod");
	}

	@Override
	void instanceMethod() {
		System.out.println("SubClass::instanceMethod");
	}
}

/*
	// 예상 결과
	SuperClass::staticMethod
	SuperClass::instanceMethod
	SubClass::staticMethod
	SubClass::instanceMethod

	// 실제 결과
	SuperClass::staticMethod
	SuperClass::instanceMethod
	SuperClass::staticMethod
	SubClass::instanceMethod
 */