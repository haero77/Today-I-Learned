> [원글: Jeongmin Lee](https://www.linkedin.com/feed/update/urn:li:activity:7451411747497852928?updateEntityUrn=urn%3Ali%3Afs_updateV2%3A%28urn%3Ali%3Aactivity%3A7451411747497852928%2CFEED_DETAIL%2CEMPTY%2CDEFAULT%2Cfalse%29)

## 한눈에 보기

Claude Code의 진짜 병목은 토큰 리밋이다. 아래 10가지 무료 도구로 같은 작업에서 토큰 소모를 60% 이상 줄일 수 있다.

| # | 도구 | 형태 | 핵심 작동 방식 | 추천 상황 | 링크 |
|---|---|---|---|---|---|
| 1 | **RTK** | CLI 프록시 | 터미널 출력을 컨텍스트에 넣기 전에 필터링 | 터미널 출력이 많을 때 ⭐ | [link](https://lnkd.in/gstbVEKb) |
| 2 | **Context Mode** | Claude Code 플러그인 | Playwright·GitHub·로그 raw 출력을 SQLite에 샌드박싱, 요약만 주입 | MCP 서버가 많을 때 ⭐ | [link](https://lnkd.in/gHX7CJqS) |
| 3 | **code-review-graph** | MCP 서버 | Tree-sitter로 코드베이스를 로컬 지식 그래프로 매핑, 필요한 부분만 읽기 | 큰 코드베이스 ⭐ | [link](https://lnkd.in/gQqUpqr2) |
| 4 | **Token Savior** | MCP 서버 | 파일 전체 대신 심볼 단위 탐색, 69개 도구 + 세션 간 메모리 | 큰 코드베이스 ⭐ | [link](https://lnkd.in/gMFTd2du) |
| 5 | **Caveman Claude** | 스킬 | Claude를 원시인 말투로 응답하게 해 출력 토큰 65~75% 절감 | 즉시 리밋 절약 ⭐ | [link](https://lnkd.in/gChQPymJ) |
| 6 | **claude-token-efficient** | CLAUDE.md 설정 | 드롭인 설정 한 장으로 응답을 간결하게 (코드 변경 불필요) | 즉시 리밋 절약 ⭐ | [link](https://lnkd.in/g5VajJAG) |
| 7 | **token-optimizer-mcp** | MCP 서버 | 캐싱 + 압축 + 스마트 도구 인텔리전스로 반복 출력 압축 | 반복 호출 많을 때 | [link](https://lnkd.in/g5zQNeye) |
| 8 | **claude-token-optimizer** | 셋업 프롬프트 모음 | 5분 만에 적용 가능, 문서 토큰 소모 절감 | 빠른 도입 | [link](https://lnkd.in/gwd3MV9Y) |
| 9 | **token-optimizer** | 분석 도구 | 컨텍스트를 갉아먹는 고스트 토큰 탐지, 컴팩션 후 품질 유지 | 토큰 누수 점검 | [link](https://lnkd.in/gfnj3m2Z) |
| 10 | **claude-context** (Zilliz) | 코드 검색 MCP | BM25 + 벡터 하이브리드 검색으로 코드베이스 전체를 컨텍스트화 | 대규모 검색 | [link](https://lnkd.in/gFJGE3_C) |

> 💡 10개를 다 설치할 필요는 없다. 워크플로에 맞게 ⭐ 표시 조합으로 시작하자. 전부 무료, Claude Code·Codex에 즉시 적용 가능.