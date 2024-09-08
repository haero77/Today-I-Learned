/*
  ⭐️ dart에서 const 키워드는 compile time constant를 생성한다!! ⭐️
  +) const 로 선언된 변수는 당연히 값 재할당 불가능.
 */
void main() {

  // API Key 예시
  const apiKey = '1234567890'; // 미리 알고 있어야하므로 const를 사용. 앱스토어에 올리기 전에 이미 알고 있는 값을 다룰 때 사용.

  const maxAllowedPrice = 120;

  // final apiResponseValue = fetchApi(); // API 호출 결과는 런타임에 알 수 있으므로 final을 사용.
}
