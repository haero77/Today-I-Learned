void main() {
  late final String name; // late는 값 초기화 없이 변수 선언 가능하게 만든다.
  // do something, go to api

  // print(name); // 컴파일 에러 발생. 값 초기화 전이므로 사용 불가능함. 👉 data fetching 시 유용하다!!

  name = 'nico';
  
  print(name);

  /*
    어떤 변수의 값을 미리 알 수 없는 상황에서 late는 유용하게 사용 가능하다.
    예를 들어, API 호출 후 값을 할당할 때, late로 변수를 선언해놓고 API 응답을 변수에 할당하는 식으로 사용 가능하다.
   */
}
