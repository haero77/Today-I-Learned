/*
  null safety? -> 개발자가 null값을 참조할 수 없게 만드는 것.
  dart의 null safety
  - 어떤 변수, 혹은 데이터가 null이 될 수 있음을 '명시'하는 것을 의미.
  -> null safety 사용 시 dart에서 어떤 데이터가 null일 때 참조하지 않도록 한다. (즉 개발자가 실수로 null을 참조하지 않도록 만든다.)
 */
void main() {
  String? nico = 'nico';
  nico = null;
  nico?.isNotEmpty; // if(nico != null) {nico?.isNotEmpty;} 와 동일.

  String name = 'name';
  // name = null; // 기본적으로 모든 변수가 non-nullable 이기 때문에 컴파일 에러 발생(=null safety 동작). 'String?' 처럼 사용해야 null을 할당할 수 있다.
}
