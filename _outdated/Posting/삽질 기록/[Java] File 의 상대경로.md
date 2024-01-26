- Java 에서 File의 상대 경로 기준은 프로젝트 폴더. 

```java
File path = new File(".");
System.out.println(path.getAbsolutePath());
```

---

> https://ohgyun.com/169

발생일: 2009.08.07

문제:
현재 클래스와 같은 패키지(폴더)에 있는 파일을 읽어오려고 한다.
상대 경로로 접근해서 가져오려고 하는데,
new File("./test.txt"); 와 같이 상대 경로로 접근하니 정상적으로 불러지지 않는다.

해결책:
자바의 File 에서 사용되는 상대 경로의 기준은는 일반적으로 우리가 생각하는 것처럼
해당 클래스 파일이 있는 위치가 아니라,
클래스 파일이 포함되어 있는 프로젝트 폴더이다.

예를 들어,
클래스를 하나 생성하고

    File path = new File(".");
    System.out.println(path.getAbsolutePath()); //--> 프로젝트 폴더의 주소가 출력됨

위와 같이 현재 클래스 위치에서 폴더를 생성하여 절대 주소를 출력해보면 프로젝트 폴더의 주소가 출력된다.


현재 클래스와 같은 패키지 또는 폴더 내의 파일을 읽어오려면,
Class 또는 ClassLoader 의 getResource 를 사용하여 아래와 같은 방법으로 사용할 수 있다.
(현재 클래스명이 FileTest 라고 가정한다)

    String path = FileTest.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
    System.out.println(path); //--> 절대 경로가 출력됨
    File fileInSamePackage = new File(path + "test.txt"); // path 폴더 내의 test.txt 를 가리킨다.


위에서 path 는 현재 클래스의 절대 경로를 가져오며,
가져온 경로를 포함하여 같은 폴더 내의 다른 파일에 접근할 수 있다.