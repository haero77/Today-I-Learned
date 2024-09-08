String capitalizeName(String? name) {
  // return name != null ? name.toUpperCase() : 'ANONYMOUS';
  return name?.toUpperCase() ?? 'ANONYMOUS'; // ?? -> 좌항이 null 이 아니면 좌항, null 이면 우항 리턴
}

void main() {
  String? name;
  name ??= 'nico'; // QQ assignment operator. if name is null name = 'nico'

  print(name);
}
