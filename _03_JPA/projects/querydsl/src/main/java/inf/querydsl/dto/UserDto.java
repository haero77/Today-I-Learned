package inf.querydsl.dto;

import lombok.ToString;

@ToString
public class UserDto {

	private String name;
	private int age;

	public UserDto() {
	}

	public UserDto(String name, int age) {
		this.name = name;
		this.age = age;
	}

}
