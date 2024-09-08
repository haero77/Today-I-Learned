/// collection if, for -> UI의 게임체인저
/// 예를 들어 유저 로그인 여부에 따라 버튼 노출 여부를 결정해야하는 경우 collection if 를 사용할 수 있음
void main() {
  var oldFriends = ['nico', 'lynn'];
  var newFriends = [
    'lewis',
    'ralph',
    'darren',
    for (var friend in oldFriends) "🍀 $friend"
  ];

  print(newFriends);
}
