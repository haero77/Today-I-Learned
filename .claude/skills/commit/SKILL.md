---
name: commit
description: Use when committing changes to the Today-I-Learned repository — accepts file paths or directories as arguments, infers category from path, generates Korean commit message in [카테고리] 설명 format, and adds Claude Code as co-author.
---

# Commit

## Overview

파일 경로 또는 디렉토리를 입력받아 커밋 메시지를 생성하고 Claude Code를 Co-author로 추가하여 커밋한다.

## 커밋 메시지 형식

```
[카테고리] 설명

Co-Authored-By: Claude Sonnet 4.6 <noreply@anthropic.com>
```

## 카테고리 매핑

| 경로 | 카테고리 |
|------|----------|
| `AI/` | AI |
| `BackEnd/` | BackEnd |
| `ComputerScience/DB/` | DB |
| `ComputerScience/Algorithm/` | algo |
| `ComputerScience/` | CS |
| `FrontEnd/` | FrontEnd |
| `Server/` | Server |
| `Others/Careers/` | 커리어 |
| `Others/Writing/` | 글쓰기 |
| `Others/` | Others |
| `.claude/` | claude |

여러 카테고리에 걸친 경우: 가장 대표적인 카테고리 하나를 선택한다.

## 워크플로우

1. 인자로 전달된 파일/디렉토리 확인 (없으면 현재 staged/unstaged 변경사항 확인)
2. 경로에서 카테고리 결정
3. 변경 내용을 읽고 학습 주제 파악
4. 한국어로 간결한 커밋 메시지 작성
5. `git add <경로>` 실행
6. Co-author 포함하여 커밋

## 커밋 명령어

```bash
git add <file-or-directory>
git commit -m "$(cat <<'EOF'
[카테고리] 설명

Co-Authored-By: Claude Sonnet 4.6 <noreply@anthropic.com>
EOF
)"
```

## 메시지 작성 원칙

- **내용 중심**: 파일명이 아니라 학습한 내용/주제로 설명
- **간결하게**: 한 줄, 50자 이내
- **한국어 우선**: 기술 용어(JPA, N+1 등)는 영어 유지
- **시리즈물**: `설계 2편 - 주제명` 형식처럼 컨텍스트 포함

## 예시

```
[AI] Claude Code 실리콘밸리 엔지니어 가이드 정리
[DB] 데이터 변경 이력 설계 - 이력 테이블 패턴
[글쓰기] 끔찍한 초고 작성하기, 퇴고하기
[algo] P04_Most_Common_Word
[BackEnd] JPA N+1 문제 해결 방법
[claude] 커밋 스킬 추가
```
