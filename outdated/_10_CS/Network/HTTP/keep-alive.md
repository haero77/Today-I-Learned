# HTTP Keep-Alive

- HTTP는 Connectionless. 연결을 매번 끊고 생성
  - 네트워크 비용이 많이 든다. `3-way-handshaking` 등.
- HTTP 1.1 부터 **이미 연결되어있는 TCP 연결을 재사용하는 `Keep-Alive` 기능을 default로 제공.** 
  - `handshaking` 과정이 생략되므로 성능 향상 기대.

<br>

### 장점

- 커넥션을 맺고 끝는 과정이 생략되므로 성능 향상

### 단점 

- 트래픽의 많은 서버의 경우에 문제가 될 수 있다. 
- keep-alive가 있는 모든 커넥션을 유지해야하기 때문에, 프로세스 수가 기하급수적으로 늘어난다.
- 결국 `MaxClient` 값 초과
- 메모리를 많이 사용하게 되므로, 성능 저하의 원인이 된다.

<br>


# keep-alive가 stateless를 위배하는가?

- No
- 이전 요청에 사용했던 TCP Connection을 재사용했다고 해서, 각 Request가 서로에 대한 정보를 아는 것(=`statless`)은 아님.

<br>

# keep-alive가 connectionless를 위배하는가?

- No
- TCP Connection 하나를 유지하는 것이 `keep-alive` 
- 하나의 TCP Connection에서, 여러 번의 HTTP Request/Response 가 일어날 수 있으므로 Connectionless를 위배하는 것이 아님.

> [위키피디아](https://ko.wikipedia.org/wiki/HTTP_%EC%A7%80%EC%86%8D%EC%A0%81_%EC%97%B0%EA%B2%B0_%EC%83%81%ED%83%9C)

<br>

### Ref. 

- [HTTP Keep Alive 알아보기](https://goodgid.github.io/HTTP-Keep-Alive/)
- [HTTP is statel-less,so what does it mean by keep-alive?](https://stackoverflow.com/questions/6060959/http-is-statel-less-so-what-does-it-mean-by-keep-alive)
- [HTTP 1.1 Keep-Alive 기능에 대해](https://b.pungjoo.com/entry/HTTP-11-Keep-Alive-%EA%B8%B0%EB%8A%A5%EC%97%90-%EB%8C%80%ED%95%B4)