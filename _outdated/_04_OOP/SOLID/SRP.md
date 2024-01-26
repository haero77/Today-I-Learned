# SRP(Single Responsibility Principle): 단일 책임 원칙

> _"어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다." - 로버트 C. 마틴_

- **_"한 클래스는 하나의 책임만 가져야 한다."_** 를 의미하는 규칙
    - 여기서 책임은 하나의 기능이라고 보면 된다.
- 하나의 책임이라는 것은 모호하다.
    - 책임은 클 수도 있고, 작을 수도 있다.
    - 책임은 문맥과 상황에 따라 다르다.
- 책임의 기준은 `변경`이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것이다.
- 예) UI 변경, 객체의 생성과 사용을 분리

## 단일 책임 원칙을 지키지 않았을 때의 문제점

```java
public class DataViewer {
    public void display() {
        String data = loadHtml();
        updateGui(data);
    }
    
    public String loadHtml() {
        HttpClient client = new HttpClient();
        client.connect(url);
        return client.getResponse();
    }
    
    private void updateGui(String data) {
        GuiData guiModel = parseDataToGuiData(data);
        tableUI.changeData(guiModel);
    }
    
    private GuiData parseDataToGuiData(String data) {
       ...// 파싱 처리 코드
    }
    ...// 기타 필드 등 다른 코드
}
```

- 위 코드는 HTTP 프로토콜을 이용해 데이터를 읽어와 화면에 보여주는 기능을 한다. 
- HTTP 클라이언트만 사용한다면 상관이 없지만 소켓 프로그래밍 등으로 읽어오는 데이터가 `String -> byte[]` 로 변경되면 코드가 연쇄적으로 변경되어야한다. 

```java
public class DataViewer {
    public void display() {
        byte[] data = loadHtml();
        updateGui(data);
    }
    
    public byte[] loadHtml() { // 리턴타입 변경
        SocketClient client = new SocketClient();
        client.connect(server, port);
        return client.read;
    }
    
    private void updateGui(byte[] data) { // 파라미터 타입 변경
        GuiData guiModel = parseDataToGuiData(data);
        tableUI.changeData(guiModel);
    }
    
    private GuiData parseDataToGuiData(byte[] data) { // 파라미터 타입 변경
       ...// 파싱 처리 코드
    }
    ...// 기타 필드 등 다른 코드
}
```

- 데이터를 제공하는 서버만 달라졌는데, 연쇄적으로 코드가 수정되었다.
- 책임의 개수가 많아질수록 한 책임의 기능 변화가 다른 책임에 주는 영향이 비례해서 증가하기 때문
- 코드를 절차 지향적으로 변하게 하여 유지 보수를 엉망으로 만든다.

👉 GUI를 보여주는 책임을 담당하는 객체와 데이터를 읽는 책임을 담당하는 객체, 그리고 데이터 자체를 추상화한 객체 3가지를 이용하여 책임을 분리해야한다. (**한 클래스가 하나의 책임을 갖도록 분리한다.**)

- SRP를 지키지 않으면 **_클래스를 재사용하기 어려워진다._**
- `DataViewer` 클래스는 데이터를 읽어오기 위한 클래스이고, HTTP 연동을 위해 HttpClient 패키지를 사용하고, 화면에 데이터를 보여주기 위해 GuiComp라는 패키지를 사용한다고 가정해보자

## 책임은 무엇일까

### 정리 

SRP를 지키지 않으면 

1. 
2. 클래스 재사용을 어렵게 한다.

이를 해결하기 위해서, 

<br>
<br>

**※ Reference**

- https://steady-coding.tistory.com/370