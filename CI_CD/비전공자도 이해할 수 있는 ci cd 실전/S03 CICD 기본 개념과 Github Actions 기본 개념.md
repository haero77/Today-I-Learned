# GitHub Actions를 활용한 전체적인 CI/CD 흐름

## GitHub Actions?

> Github Actions를 로직을 실행시킬 수 있는 일종의 컴퓨터라고 생각하면 된다. CI/CD 과정에서 Github Actions는 “빌드, 테스트, 배포”에 대한 로직을 실행시키는 역할을 하게 된다.

## CI/CD 전체 흐름

![img.png](img.png)

1. 코드 작성 후 Commit
2. Github에 Push
3. Push를 감지해서 Github Actions에 작성한 로직이 실행
    1. 빌드(Build)
    2. 테스트(Test)
    3. 서버로 배포(Deploy)
4. 서버에서 배포된 최신 코드로 서버를 재실행

# (실습) Github Actions 기본 문법 정리
