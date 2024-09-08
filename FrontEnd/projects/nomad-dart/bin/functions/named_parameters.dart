String sayHello({
  String name = '익명', // default value
  required int age,
  required String country // default value X
}) {
  return 'Hello $name, you are $age, and you come from $country';
}

void main() {
  /// named argument. order is not important at all.
  print(sayHello(age: 13, country: 'Republic of Korea', name: 'name'));
  print(sayHello(
      age: 13,
      country: 'Republic of Korea'
  ));
  print(sayHello(age: 13, country: '24'));

}
