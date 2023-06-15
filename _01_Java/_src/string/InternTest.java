package _01_Java._src.string;

public class InternTest {

	public static void main(String[] args) {

		String str1 = "aaa";

		// String.intern(): String Pool에 리터럴이 존재하면 참조를 리턴. 없으면 Pool에 리터럴 생성하고 리턴
		String str2 = new String("aaa").intern();

		String str3 = new String("aaa");

		System.out.println(str1 == str2); // true
		System.out.println(str2 == str3); // false
	}
}
