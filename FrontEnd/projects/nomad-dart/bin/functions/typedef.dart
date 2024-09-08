/// typedef: 자료형에 alias를 붙일 수 있게 해준다.]
typedef ListOfInts = List<int>;

ListOfInts reverseListOfNumbers(ListOfInts list) {
  var reversed = list.reversed; // Iterable<int>
  return reversed.toList();
}

void main() {
  print(reverseListOfNumbers([1, 2, 3, 4]));
}
