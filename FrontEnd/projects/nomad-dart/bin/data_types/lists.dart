void main() {
  var giveMeFive = true;

  var numbers = <int>[
    1,
    2,
    3,
    4,
    if (giveMeFive) 5, // Collection if -> if (giveMeFive) { numbers.add(5);}
  ]; // List<int> numbers. 마지막 원소에 쉼표 작성 시 자동 개행.

  print(numbers.last);
}
