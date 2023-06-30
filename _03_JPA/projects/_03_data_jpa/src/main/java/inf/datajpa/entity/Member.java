package inf.datajpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	protected Member() {
	}

	public Member(String username) {
		this.username = username;
	}

}
