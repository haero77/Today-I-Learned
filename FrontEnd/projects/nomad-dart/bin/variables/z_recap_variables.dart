void main() {
  // 다트의 변수 선언 방법 2가지 (자료형 명시 또는 명시 X)
  int i = 12;
  var name = 'la'; // 권장 방법은 메서드 내부의 로컬 변수의 경우 var 사용

  // final: 변수 한 번 선언 시 재할당 불가.
  // dynamic: 다른 타입의 값을 계속 할당 할 수 있음. 지양할 것.
  // const: 컴파일 할 때 값을 알고 있는 변수를 생성하는 방법. 즉, const값은 코드를 컴파일 하기 전에 알고 있어야함. (final 은 런타임에 실행됨.)

  /*
    null-safety: 개발자가 null을 참조하지 못하게 하는 것
   */
  String? nico = 'nico';
  nico = null;
  // nico.isNotEmpty; 컴파일 에러(null-safety 때문)

  /*
    late: 값 초기화 전에 변수 선언을 가능케 한다. 초기화 전 변수 사용 시 컴파일 에러 발생.
   */
}
