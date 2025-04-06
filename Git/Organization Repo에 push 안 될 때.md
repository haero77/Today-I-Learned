> [[TIL] 230620 <트러블슈팅> remote: Permission to ... denied to 사용자. unable to access '...': The requested URL returned error: 403](https://enjoydev.tistory.com/entry/TIL-230620-%ED%8A%B8%EB%9F%AC%EB%B8%94%EC%8A%88%ED%8C%85-remote-Permission-to-denied-to-%EC%82%AC%EC%9A%A9%EC%9E%90-unable-to-access-The-requested-URL-returned-error-403)

## 문제 상황

- organization repo에 push를 하려는데, 아래와 같은 에러가 발생.

```
git push origin main 
remote: Permission to devclub-official/goforawalk_server.git denied to haero77.
fatal: unable to access 'https://github.com/devclub-official/goforawalk_server.git/': The requested URL returned error: 403
```

## 해결

mac `키체인 접근`에서 기존 github 계정 삭제, remote url 재설정 등 일반적으로 알려진 내용들 전부 시도해봤으나 실패.

아래 내용으로 해결 성공

1. 액세스 토큰 신규 발급 (Classic 토큰)
2. `git remote set-url orgin {깃헙 토큰}@github.com/{유저네임}/{organization}/{repoName}` 형태로 변경
   - **유저네임이 오가니제이션 앞에 오는 것이 포인트!!**

예시 
```
 git remote set-url orgin https://{githubToken}@github.com/haero77/devclub-official/goforawalk_server.git        
```

3. 설정하고 나서 다시 `git push origin main` 시도 및 토큰 재입력하면서 push 정상적으로 되는 것 확인 완료.

```
git push origin main                                                                                                                                                                                                         ─╯

git: 'credential-manager' is not a git command. See 'git --help'.
Username for 'https://github.com': haero77
Password for 'https://haero77@github.com': 
git: 'credential-manager' is not a git command. See 'git --help'.
Enumerating objects: 5, done.
Counting objects: 100% (5/5), done.
Delta compression using up to 8 threads
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 264 bytes | 264.00 KiB/s, done.
Total 3 (delta 1), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To https://github.com/devclub-official/goforawalk_server.git
   a84d70b..4e140e2  main -> main
```
