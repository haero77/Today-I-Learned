class Player {
  final String name;
  final int xp;
  String team;
  int age;

  // Named construct parameter
  Player({
    required this.name,
    required this.xp,
    required this.team,
    required this.age
  });

  Player.createRedTeam({
    required String name,
    required int age
  })
      : this.age = age,
        this.name = name,
        this.team = 'red',
        this.xp = 0;

  Player.createBlueTeam({
    required String name,
    required int age
  })
      : this.age = age,
        this.name = name,
        this.team = 'blue',
        this.xp = 0;

  void sayHello(String name) {
    print("Hi my name is ${this.name}");
  }
}

void main() {
  var player = Player(name: 'A', xp: 1000, team: 'red', age: 12);
  player.sayHello('name');

  var player2 = Player(name: 'B', xp: 2000, team: 'blue', age: 12);
  player2.sayHello('name');

  var redPlayer = Player.createRedTeam(name: 'red', age: 10);
  print(redPlayer);

  var bluePlayer = Player.createBlueTeam(name: 'blue', age: 10);
  print(bluePlayer);
}
