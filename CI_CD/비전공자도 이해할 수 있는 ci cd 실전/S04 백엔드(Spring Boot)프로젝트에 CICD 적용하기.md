<!-- TOC -->
* [방법 1 - 개인 프로젝트에서 많이 쓰는 CI/CD 구축 방법 (Github Actions)](#방법-1---개인-프로젝트에서-많이-쓰는-cicd-구축-방법-github-actions)
  * [개요](#개요)
    * [전체 흐름](#전체-흐름)
    * [장점](#장점)
    * [단점](#단점)
    * [이 방법은 언제 주로 쓰는 지?](#이-방법은-언제-주로-쓰는-지)
  * [(실습) 개인 프로젝트에서 많이 쓰는 CI/CD 구축 방법](#실습-개인-프로젝트에서-많이-쓰는-cicd-구축-방법)
    * [t2.micro가 부족하다면 t2 micro swap 검색](#t2micro가-부족하다면-t2-micro-swap-검색)
    * [git clone 진행](#git-clone-진행-)
    * [git clone 실패](#git-clone-실패-)
    * [프로젝트 빌드 & 실행](#프로젝트-빌드--실행)
    * [git pull 할 때마다 토큰 입력 안 하도록 수정](#git-pull-할-때마다-토큰-입력-안-하도록-수정)
  * [(실습) .gitignore에 추가된 application.yml을 CI/CD로 관리하기](#실습-gitignore에-추가된-applicationyml을-cicd로-관리하기)
* [방법 2 - 일반 프로젝트에서 많이 쓰는 CI/CD 구축 방법 (Github Actions, SCP)](#방법-2---일반-프로젝트에서-많이-쓰는-cicd-구축-방법-github-actions-scp)
  * [(실습) 일반 프로젝트에서 많이 쓰는 CI/CD 구축 방법](#실습-일반-프로젝트에서-많이-쓰는-cicd-구축-방법)
* [방법 3 - 확장성을 고려한 프로젝트에서 많이 쓰는 CI/CD 구축 방법 (Code Deploy)](#방법-3---확장성을-고려한-프로젝트에서-많이-쓰는-cicd-구축-방법-code-deploy)
  * [Code Deploy 세팅 / IAM 설정](#code-deploy-세팅--iam-설정)
  * [참고 Code Deploy 로그 확인 방법](#참고-code-deploy-로그-확인-방법)
  * [(실습) 확장성을 고려한 프로젝트에서 많이 쓰는 CI/CD 구축 방법](#실습-확장성을-고려한-프로젝트에서-많이-쓰는-cicd-구축-방법)
<!-- TOC -->

# 방법 1 - 개인 프로젝트에서 많이 쓰는 CI/CD 구축 방법 (Github Actions)

## 개요

### 전체 흐름

![img_3.png](img_3.png)

- GitHub 에서 커밋, 푸시
- GitHub Actions가 감지해서, AWS EC2에 SSH 원격 접속 진행. 
  - EC2에 들어가서 Git Pull 진행. 코드를 갈아끼움

### 장점

- git pull을 활용해서 변경된 부분의 프로젝트 코드에 대해서만 업데이트 하기 때문에 CI/CD 속도가 빠르다.
  - git pull 속도가 빠르므로 속도가 빠르다. 
  - 대부분의 CI/CD 방식들은 전체 프로젝트를 통째로 갈아끼우는 방식을 사용한다.
- CI/CD 툴로 Github Actions만 사용하기 때문에 인프라 구조가 복잡하지 않고 간단하다.

### 단점

- 빌드 작업을 EC2에서 직접 진행하기 때문에 운영하고 있는 서버의 성능에 영향을 미칠 수 있다.
  - 프로젝트 빌드 작업은 생각보다 컴퓨터 메모리와 CPU 자원을 많이 잡아 먹는다.
- Github 계정 정보가 해당 EC2에 저장되기 때문에 개인 프로젝트 또는 믿을만한 사람들과 같이 진행하는 토이 프로젝트에서만 사용해야 한다.

### 이 방법은 언제 주로 쓰는 지?

- 주로 개인 프로젝트에서 CI/CD를 심플하고 빠르게 적용시키고 싶을 때 적용한다.

## (실습) 개인 프로젝트에서 많이 쓰는 CI/CD 구축 방법

### t2.micro가 부족하다면 t2 micro swap 검색

- ec2가 멈추는 현상 개선 가능

> https://velog.io/@pgmjun/AWS-당신의-EC2가-계속해서-죽는다면feat.-Swap-Memory

### git clone 진행 

> git clone https://{나의 닉네임}:{토큰}@github.com/{레포 소유닉네임}/{레포명}.git

- 문제는 git clone 시 not found 에러 발생

> [[github] remote repository 공유가 안될 때 (remote: Repository not found)](https://o-yeon.tistory.com/90)

- 위 경우 내 레포에서는 `manage access` UI가 보이지 않음

### git clone 실패 

![img_5.png](img_5.png)

- 토큰 제대로 입력했지만 repository not found 발생

![img_4.png](img_4.png)

- fine grained token 생성 후 해당토큰에 권한 부여 ✅
- 다시 시도하면 다음과 같이 받아진다.

![img_6.png](img_6.png)

### 프로젝트 빌드 & 실행

> nohup java -jar cicd-example-0.0.1-SNAPSHOT.jar &

- 이 명령어는 'cicd-example-0.0.1-SNAPSHOT.jar'라는 Java 애플리케이션을 백그라운드에서 실행하며, 터미널 세션이 종료되어도 계속 실행되도록 합니다.

![img_7.png](img_7.png)

### 실행 실패

> sudo lsof -i:8080
> 
> sudo lsof -i:80

- 위 명령어를 실행해보니까 실행 중인 프로세스가 없다.

```text
Caused by: java.net.BindException: Permission denied
	at java.base/sun.nio.ch.Net.bind0(Native Method) ~[na:na]
	at java.base/sun.nio.ch.Net.bind(Net.java:555) ~[na:na]
	at java.base/sun.nio.ch.ServerSocketChannelImpl.netBind(ServerSocketChannelImpl.java:337) ~[na:na]
	at java.base/sun.nio.ch.ServerSocketChannelImpl.bind(ServerSocketChannelImpl.java:294) ~[na:na]
	at org.apache.tomcat.util.net.NioEndpoint.initServerSocket(NioEndpoint.java:239) ~[tomcat-embed-core-10.1.28.jar!/:na]
	at org.apache.tomcat.util.net.NioEndpoint.bind(NioEndpoint.java:194) ~[tomcat-embed-core-10.1.28.jar!/:na]
	at org.apache.tomcat.util.net.AbstractEndpoint.bindWithCleanup(AbstractEndpoint.java:1304) ~[tomcat-embed-core-10.1.28.jar!/:na]
	at org.apache.tomcat.util.net.AbstractEndpoint.start(AbstractEndpoint.java:1390) ~[tomcat-embed-core-10.1.28.jar!/:na]
	at org.apache.coyote.AbstractProtocol.start(AbstractProtocol.java:643) ~[tomcat-embed-core-10.1.28.jar!/:na]
	at org.apache.catalina.connector.Connector.startInternal(Connector.java:1058) ~[tomcat-embed-core-10.1.28.jar!/:na]
	... 26 common frames omitted
```

- 로그 확인해보니 위 에러 발생

> 이 오류는 애플리케이션이 지정된 포트에 바인딩하려고 할 때 권한이 거부되었음을 나타냅니다. 이는 주로 두 가지 이유로 발생합니다:
> 
> 권한 부족:
> 
> 애플리케이션이 1024 미만의 특권 포트(privileged port)를 사용하려고 하지만, root 권한이 없는 경우 발생할 수 있습니다.
> 해결 방법: 1024 이상의 포트를 사용하거나, 필요한 경우 root 권한으로 애플리케이션을 실행하세요.
> 
> 
> 포트가 이미 사용 중:
> 
> 다른 프로세스가 이미 해당 포트를 사용하고 있을 수 있습니다.
해결 방법: 사용 중인 포트를 확인하고, 필요하다면 애플리케이션의 포트 설정을 변경하세요.

- 포트를 8080으로 변경 후 재실행하니 실행된다. ✅

![img_8.png](img_8.png)


### 서버 접속 확인 - 접속 불가

![img_9.png](img_9.png)

- 서버에 접속 불가능 👉 인바운드 규칙 편집해본다


![img_11.png](img_11.png)

![img_12.png](img_12.png)

- 프로세스 실행 중 확인 ✅

![img_13.png](img_13.png)

![img_14.png](img_14.png)

- 서버 접속 확인 ✅



### git pull 할 때마다 토큰 입력 안 하도록 수정




## (실습) .gitignore에 추가된 application.yml을 CI/CD로 관리하기

# 방법 2 - 일반 프로젝트에서 많이 쓰는 CI/CD 구축 방법 (Github Actions, SCP)

## (실습) 일반 프로젝트에서 많이 쓰는 CI/CD 구축 방법

# 방법 3 - 확장성을 고려한 프로젝트에서 많이 쓰는 CI/CD 구축 방법 (Code Deploy)

## Code Deploy 세팅 / IAM 설정

## 참고 Code Deploy 로그 확인 방법

## (실습) 확장성을 고려한 프로젝트에서 많이 쓰는 CI/CD 구축 방법
