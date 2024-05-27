package hellojpa;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @Embedded
    private Period workPeriod; // 근무 기간

    @Embedded
    private Address homeAddress; // 집 주소

}
