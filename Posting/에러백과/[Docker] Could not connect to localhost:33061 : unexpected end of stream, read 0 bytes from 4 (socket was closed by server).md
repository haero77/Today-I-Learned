# Could not connect to localhost:33061 : unexpected end of stream, read 0 bytes from 4 (socket was closed by server)

## 상황

- 기존에 3306 포트를 사용 중이어서, 33061 포트를 사용하는 컨테이너를 띄운 상태에서,
  - DataGrip 을 이용하여 해당 DB에 접근하려고 했을 때 문제 발생


## 원인 

<img width="328" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/6b71698c-bb33-4f05-b698-ea55995c23cd">

- 33061 포트를 사용해서 내부적으로 33061 포트로 연결했으므로 DB 포트인 3306 에는 연결이 도달하지 못한다.
- 포트 설정을 다시하든가, 아니면 DB

<br>

## 해결 방법 1 - DB Port 변경

- [docker에 올린 mariaDB와 dbeaver 연결 중 socket was closed by server 오류 해결하기](https://sg-moomin.tistory.com/entry/%EC%84%B8%EB%AC%B4%EB%AF%BC%EC%9D%98-%EC%BD%94%EB%94%A9%EC%9D%BC%EA%B8%B0-docker%EC%97%90-%EC%98%AC%EB%A6%B0-mariaDB%EC%99%80-dbeaver-%EC%97%B0%EA%B2%B0-%EC%A4%91-socket-was-closed-by-server-%EC%98%A4%EB%A5%98-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0)


### DB 접속 후 global port 확인

<img width="942" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/5ae1e7eb-02fb-4482-a07a-84eebebf0bcf">

- `show global variables like 'port';`

## 해결 방법 2 - 포트 설정을 다시하기

- 어차피 컨테이너 접속한 상태에서 3306 포트가 겹치지는 않는다.
- 33061 -> 3306 으로 매핑되도록 컨테이너 설정을 다시한다. 

<img width="237" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/60ed0ede-0dfb-4906-932d-eb469a82ca24">
