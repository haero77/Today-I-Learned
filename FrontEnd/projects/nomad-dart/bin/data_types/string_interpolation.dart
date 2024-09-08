/// String Interpolation: 텍스트에 변수를 추가하는 방법 - '$'
void main() {
  var name = 'nico';
  var age = 10;

  var greetingMsg = 'Hello everyone, my name is $name. and I\'m ${age + 2}';

  print(greetingMsg);
}
